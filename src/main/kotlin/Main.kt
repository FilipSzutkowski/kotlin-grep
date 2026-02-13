package org.example

fun main(args: Array<String>) {
    val query = args.getOrNull(0)
    val fPath = args.getOrNull(1)

    if (query.isNullOrBlank() || fPath.isNullOrBlank()) {
        println("Specify search query and file path")
        return
    }

    try {
        FileLinesSeeker(query, fPath).printLines()
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }

}