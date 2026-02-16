package org.example

import java.io.File

data class MatchingLine(val lineNumber: Int, val content: String)

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

    fun getLines(): List<MatchingLine> {
        gotFile.useLines { lines ->
            return lines
                .withIndex()
                .filter { str ->
                    str.value.contains(searchQuery, true)
                }
                .map { str ->
                    MatchingLine(str.index + 1, str.value)
                }
                .toList()
        }
    }

}