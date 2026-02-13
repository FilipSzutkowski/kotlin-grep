package org.example

import java.io.File

/**
 * @throws [IllegalStateException] if the file does not exist, is not a file or is not readable.
 */
class FileLinesSeeker(val searchQuery: String, filePath: String) {
    val gotFile = File(filePath)

    init {
        check(gotFile.exists()) { "'${gotFile.absolutePath}' does not exist" }
        check(gotFile.isFile()) { "'${gotFile.absolutePath}' is not a file" }
        check(gotFile.canRead()) { "'${gotFile.absolutePath}' is not readable" }
    }

    fun printLines() {
        gotFile.useLines {
            println("[$gotFile]:")

            val res = it.filter { str ->
                str.contains(searchQuery, true)
            }

            res.forEach { line ->
                println(line)
            }

        }
    }

}