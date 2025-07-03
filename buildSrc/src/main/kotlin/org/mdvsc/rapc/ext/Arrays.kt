package org.mdvsc.rapc.ext

fun<T> Array<T>.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((T) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun ByteArray.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Byte) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun IntArray.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Int) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun LongArray.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Long) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun ShortArray.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Short) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun FloatArray.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Float) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}


fun DoubleArray.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Double) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}

fun CharArray.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Char) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) buffer.append(transform?.invoke(element) ?: element.toString()) else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}
public fun <T> Array<out T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((T) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun ByteArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Byte) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun ShortArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Short) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun IntArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Int) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun LongArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Long) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun FloatArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Float) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun DoubleArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Double) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun BooleanArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Boolean) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}

fun CharArray.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((Char) -> String)? = null): String {
    return joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()
}
