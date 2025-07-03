/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
package org.mdvsc.rapc.ext

internal object EmptyList : AbstractList<Nothing>(), RandomAccess {
    override fun equals(other: Any?): Boolean = other is List<*> && other.isEmpty()
    override fun hashCode(): Int = 1
    override fun isEmpty(): Boolean = true
    override fun get(index: Int): Nothing = throw IndexOutOfBoundsException("Empty list doesn't contain element at index $index.")
    override fun size() = 0
}

/**
 * Returns an empty read-only list.  The returned list is serializable (JVM).
 * @sample samples.collections.Collections.Lists.emptyReadOnlyList
 */
fun <T> emptyList(): List<T> = EmptyList as List<T>

/**
 * Returns a new read-only list of given elements.  The returned list is serializable (JVM).
 * @sample samples.collections.Collections.Lists.readOnlyList
 */
fun <T> listOf(vararg elements: T): List<T> = if (elements.isNotEmpty()) ArrayList(elements) else emptyList()

/**
 * Returns a new read-only list containing only the specified object [element].
 *
 * The returned list is serializable (JVM).
 *
 * @sample samples.collections.Collections.Lists.singletonReadOnlyList
 */
@SinceKotlin("1.9")
fun <T> listOf(element: T): List<T> = Collections.singletonList(element)

/**
 * Returns a new [kotlin.collections.MutableList] with the given elements.
 * @sample samples.collections.Collections.Lists.mutableList
 */
fun <T> mutableListOf(vararg elements: T): List<T> = if (elements.isEmpty()) ArrayList() else ArrayList(elements)

/**
 * Returns a new [ArrayList] with the given elements.
 * @sample samples.collections.Collections.Lists.arrayList
 */
fun <T> arrayListOf(vararg elements: T): ArrayList<T> =
    if (elements.isEmpty()) ArrayList() else ArrayList(elements)

/**
 * Returns a new read-only list either of single given element, if it is not null, or empty list if the element is null. The returned list is serializable (JVM).
 * @sample samples.collections.Collections.Lists.listOfNotNull
 */
fun <T : Any> listOfNotNull(element: T?): List<T> = if (element != null) listOf(element) else emptyList()

/**
 * Returns a new read-only list only of those given elements, that are not null.  The returned list is serializable (JVM).
 * @sample samples.collections.Collections.Lists.listOfNotNull
 */
fun <T : Any> listOfNotNull(vararg elements: T?): List<T> {
    val list = ArrayList<T>()
    for (element in elements) {
        if (element != null) list.add(element)
    }
    return list
}

fun <T> Iterable<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((T) -> String)? = null): String
= joinTo(StringBuffer(), separator, prefix, postfix, limit, truncated, transform).toString()

fun <T> Iterable<T>.joinTo(buffer: StringBuffer, separator: String = ", ", prefix: String = "", postfix: String = "", limit: Int = -1, truncated: String = "...", transform: ((T) -> String)? = null): StringBuffer {
    buffer.append(prefix)
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.append(separator)
        if (limit < 0 || count <= limit) {
            transform?.invoke(element) ?: element
            buffer.append(element)
        } else break
    }
    if (limit >= 0 && count > limit) buffer.append(truncated)
    buffer.append(postfix)
    return buffer
}