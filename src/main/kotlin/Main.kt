package org.example

import java.io.File

fun main(args: Array<String>) {
    val query = args.getOrNull(0)
    val fPath = args.getOrNull(1)

    if (query.isNullOrBlank() || fPath.isNullOrBlank()) {
        println("Specify search query and file path")
        return
    }

    val gotFile = File(fPath)

    val fileValidationError = when {
        !gotFile.exists() -> "'${gotFile.absolutePath}' does not exist"
        !gotFile.isFile() -> "'${gotFile.absolutePath}' is not a file"
        !gotFile.canRead() -> "'${gotFile.absolutePath}' is not readable"
        else -> null
    }

    if (fileValidationError != null) {
        println(fileValidationError)
        return
    }

    gotFile.useLines {
        println("[$gotFile]:")

        val res = it.filterIndexed { i, str ->
            str.contains(query, true)
        }

        res.forEach { line ->
            println(line)
        }

    }

}