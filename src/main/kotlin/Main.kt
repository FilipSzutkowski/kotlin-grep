package org.example

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.time.Clock

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

    val startTime = Clock.System.now()

    coroutineScope {
        fPaths.forEach {
            this.launch {
                println()
                println("[$it]: Time since start: ${Clock.System.now().minus(startTime)}")
                try {
                    println("[$it]: ")
                    FileLinesSeeker(query, it)
                        .getLines()
                        .forEach { line ->
                            println("   - $line")
                        }
                } catch (e: Exception) {
                    println(e.message ?: "Unknown error")
                }
            }
        }
    }
}