package org.mdvsc.rapc.ext

fun String.indexOf(str: String, startIndex: Int = 0, ignoreCase: Boolean = false): Int {
    this as java.lang.String
    return if (ignoreCase) (lowercase() as java.lang.String).indexOf(str.lowercase()) else if (startIndex <= 0) indexOf(str) else indexOf(str, startIndex)
}

fun String.lastIndexOf(str: String, startIndex: Int = 0, ignoreCase: Boolean = false): Int {
    var index = startIndex
    var result = -1
    while (index < length) {
        index = indexOf(str, index, ignoreCase)
        if (index < 0) break
        result = index
        index += str.length
    }
    return result
}

fun String.contains(other: String, ignoreCase: Boolean = false) = indexOf(other, ignoreCase = ignoreCase) >= 0
