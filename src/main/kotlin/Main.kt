package org.example

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(args: Array<String>) {
    val query = args.getOrNull(0)
    val fPaths = args.slice(1..args.lastIndex)

    if (query.isNullOrBlank() || fPaths.count() < 1) {
        println("Specify search query and file path")
        return
    }

    println("Searching for '$query' in:")

    fPaths.forEach {
        println("   - $it")
    }

    println("")


    coroutineScope {
        val channels = fPaths.map { filePath ->
            this.produce {
                var resMsg = "[$filePath]:\n"

                try {
                    FileLinesSeeker(query, filePath)
                        .getLines()
                        .forEach { line ->
                            resMsg = "$resMsg  - <Line ${line.lineNumber}> ${line.content}\n"
                        }
                } catch (e: Exception) {
                    resMsg = "$resMsg  ${e.message ?: "Unknown Error"}"
                } finally {
                    send(resMsg)
                }
            }
        }

        for (c in channels) {
            c.consumeEach { msg -> println(msg) }
        }
    }
}