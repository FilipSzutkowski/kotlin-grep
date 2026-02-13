package org.example

fun main(args: Array<String>) {
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

    fPaths.forEach {
        try {
            FileLinesSeeker(query, it).printLines()
        } catch (e: Exception) {
            println("[$it] - Failed:")
            println(e.message ?: "Unknown error")
        }
    }

}