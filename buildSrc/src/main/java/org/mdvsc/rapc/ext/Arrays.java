package org.mdvsc.rapc.ext;

public class Arrays {

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        net.rim.device.api.util.Arrays.sort(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     *
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *     if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static void sort(int[] a, int fromIndex, int toIndex) {
        net.rim.device.api.util.Arrays.sort(a, fromIndex, toIndex);
    }

    /**
     * Sorts the specified array into ascending numerical order.
     *
     * @param a the array to be sorted
     */
    public static void sort(long[] a) {
        net.rim.device.api.util.Arrays.sort(a, 0, a.length);
    }

    /**
     * Sorts the specified range of the array into ascending order. The range
     * to be sorted extends from the index {@code fromIndex}, inclusive, to
     * the index {@code toIndex}, exclusive. If {@code fromIndex == toIndex},
     * the range to be sorted is empty.
     *
     * @param a the array to be sorted
     * @param fromIndex the index of the first element, inclusive, to be sorted
     * @param toIndex the index of the last element, exclusive, to be sorted
     *
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *     if {@code fromIndex < 0} or {@code toIndex > a.length}
     */
    public static void sort(long[] a, int fromIndex, int toIndex) {
        net.rim.device.api.util.Arrays.sort(a, fromIndex, toIndex);
    }

    public static <T> void sort(T[] a, Comparator<? super T> c) {
        net.rim.device.api.util.Arrays.sort(a, (o, o1) -> c.compare((T)o, (T)o1));
    }

    public static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c) {
        net.rim.device.api.util.Arrays.sort(a, fromIndex, toIndex - fromIndex, (o, o1) -> c.compare((T)o, (T)o1));
    }
// Searching

    /**
     * Searches the specified array of longs for the specified value using the
     * binary search algorithm.  The array must be sorted (as
     * by the {@link #sort(long[])} method) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element greater than the key, or {@code a.length} if all
     *         elements in the array are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(long[] a, long key) {
        return net.rim.device.api.util.Arrays.binarySearch(a, key, 0, a.length);
    }

    /**
     * Searches a range of
     * the specified array of longs for the specified value using the
     * binary search algorithm.
     * The range must be sorted (as
     * by the {@link #sort(long[], int, int)} method)
     * prior to making this call.  If it
     * is not sorted, the results are undefined.  If the range contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or {@code toIndex} if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > a.length}
     * @since 1.6
     */
    public static int binarySearch(long[] a, int fromIndex, int toIndex, long key) {
        return net.rim.device.api.util.Arrays.binarySearch(a, key, fromIndex, toIndex);
    }

    /**
     * Searches the specified array of ints for the specified value using the
     * binary search algorithm.  The array must be sorted (as
     * by the {@link #sort(int[])} method) prior to making this call.  If it
     * is not sorted, the results are undefined.  If the array contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element greater than the key, or {@code a.length} if all
     *         elements in the array are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(int[] a, int key) {
        return net.rim.device.api.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches a range of
     * the specified array of ints for the specified value using the
     * binary search algorithm.
     * The range must be sorted (as
     * by the {@link #sort(int[], int, int)} method)
     * prior to making this call.  If it
     * is not sorted, the results are undefined.  If the range contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or {@code toIndex} if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > a.length}
     * @since 1.6
     */
    public static int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        return net.rim.device.api.util.Arrays.binarySearch(a, key, fromIndex, toIndex);
    }

    /**
     * Searches the specified array of shorts for the specified value using
     * the binary search algorithm.  The array must be sorted
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element greater than the key, or {@code a.length} if all
     *         elements in the array are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(short[] a, short key) {
        return net.rim.device.api.util.Arrays.binarySearch(a, key);
    }

    /**
     * Searches a range of
     * the specified array of shorts for the specified value using
     * the binary search algorithm.
     * The range must be sorted
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or {@code toIndex} if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > a.length}
     * @since 1.6
     */
    public static int binarySearch(short[] a, int fromIndex, int toIndex, short key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            short midVal = a[mid];
            if (midVal < key) low = mid + 1; else if (midVal > key) high = mid - 1; else return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of chars for the specified value using the
     * binary search algorithm.  The array must be sorted (as
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element greater than the key, or {@code a.length} if all
     *         elements in the array are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(char[] a, char key) {
        return binarySearch(a, 0, a.length, key);
    }

    /**
     * Searches a range of
     * the specified array of chars for the specified value using the
     * binary search algorithm.
     * The range must be sorted (as
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or {@code toIndex} if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > a.length}
     * @since 1.6
     */
    public static int binarySearch(char[] a, int fromIndex, int toIndex, char key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];
            if (midVal < key) low = mid + 1; else if (midVal > key) high = mid - 1; else return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of bytes for the specified value using the
     * binary search algorithm.  The array must be sorted (as
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element greater than the key, or {@code a.length} if all
     *         elements in the array are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(byte[] a, byte key) {
        return binarySearch(a, 0, a.length, key);
    }

    /**
     * Searches a range of
     * the specified array of bytes for the specified value using the
     * binary search algorithm.
     * The range must be sorted (as
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or {@code toIndex} if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > a.length}
     * @since 1.6
     */
    public static int binarySearch(byte[] a, int fromIndex, int toIndex, byte key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            byte midVal = a[mid];
            if (midVal < key) low = mid + 1; else if (midVal > key) high = mid - 1; else return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of doubles for the specified value using
     * the binary search algorithm.  The array must be sorted
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element greater than the key, or {@code a.length} if all
     *         elements in the array are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(double[] a, double key) {
        return binarySearch(a, 0, a.length, key);
    }

    /**
     * Searches a range of
     * the specified array of doubles for the specified value using
     * the binary search algorithm.
     * If it is not sorted, the results are undefined.  If the range contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found.  This method considers all NaN values to be
     * equivalent and equal.
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>.  The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or {@code toIndex} if all
     *         elements in the range are less than the specified key.  Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > a.length}
     * @since 1.6
     */
    public static int binarySearch(double[] a, int fromIndex, int toIndex, double key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            double midVal = a[mid];
            if (midVal < key) low = mid + 1; else if (midVal > key) high = mid - 1; else return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    /**
     * Searches the specified array of floats for the specified value using
     * the binary search algorithm.
     *
     * @param a the array to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>. The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element greater than the key, or {@code a.length} if all
     *         elements in the array are less than the specified key. Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     */
    public static int binarySearch(float[] a, float key) {
        return binarySearch(a, 0, a.length, key);
    } 

    /**
     * Searches a range of
     * the specified array of floats for the specified value using
     * the binary search algorithm.
     * prior to making this call. If
     * it is not sorted, the results are undefined. If the range contains
     * multiple elements with the specified value, there is no guarantee which
     * one will be found. This method considers all NaN values to be
     * equivalent and equal.
     *
     * @param a the array to be searched
     * @param fromIndex the index of the first element (inclusive) to be
     *          searched
     * @param toIndex the index of the last element (exclusive) to be searched
     * @param key the value to be searched for
     * @return index of the search key, if it is contained in the array
     *         within the specified range;
     *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>. The
     *         <i>insertion point</i> is defined as the point at which the
     *         key would be inserted into the array: the index of the first
     *         element in the range greater than the key,
     *         or {@code toIndex} if all
     *         elements in the range are less than the specified key. Note
     *         that this guarantees that the return value will be &gt;= 0 if
     *         and only if the key is found.
     * @throws IllegalArgumentException
     *         if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code fromIndex < 0 or toIndex > a.length}
     * @since 1.6
     */
    public static int binarySearch(float[] a, int fromIndex, int toIndex, float key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            float midVal = a[mid];
            if (midVal < key) low = mid + 1; else if (midVal > key) high = mid - 1; else return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

// Equality Testing

    /**
     * Returns {@code true} if the two specified arrays of longs are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     */
    public static boolean equals(long[] a, long[] a2) {
        return net.rim.device.api.util.Arrays.equals(a, a2);
    }

    /**
     * Returns true if the two specified arrays of longs, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @since 9
     */
    public static boolean equals(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of ints are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     */
    public static boolean equals(int[] a, int[] a2){
        return net.rim.device.api.util.Arrays.equals(a, a2);
    }

    /**
     * Returns true if the two specified arrays of ints, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @since 9
     */
    public static boolean equals(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of shorts are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     */
    public static boolean equals(short[] a, short[] a2) {
        return net.rim.device.api.util.Arrays.equals(a, a2);
    }

    /**
     * Returns true if the two specified arrays of shorts, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @since 9
     */
    public static boolean equals(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of chars are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     */
    public static boolean equals(char[] a, char[] a2) {
        return net.rim.device.api.util.Arrays.equals(a, a2);
    }

    /**
     * Returns true if the two specified arrays of chars, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @since 9
     */
    public static boolean equals(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of bytes are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     */
    public static boolean equals(byte[] a, byte[] a2) {
        return net.rim.device.api.util.Arrays.equals(a, a2);
    }

    /**
     * Returns true if the two specified arrays of bytes, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @since 9
     */
    public static boolean equals(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of booleans are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     */
    public static boolean equals(boolean[] a, boolean[] a2) {
        int aSize = a.length;
        int bSize = a2.length;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[pos] != a2[pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns true if the two specified arrays of booleans, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @since 9
     */
    public static boolean equals(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of doubles are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * Two doubles {@code d1} and {@code d2} are considered equal if:
     * <pre>    {@code Double.valueOf(d1).equals(Double.valueOf(d2))}</pre>
     * (Unlike the {@code ==} operator, this method considers
     * {@code NaN} equal to itself, and 0.0d unequal to -0.0d.)
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     * @see Double#equals(Object)
     */
    public static boolean equals(double[] a, double[] a2) {
        int aSize = a.length;
        int bSize = a2.length;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[pos] != a2[pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns true if the two specified arrays of doubles, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * <p>Two doubles {@code d1} and {@code d2} are considered equal if:
     * <pre>    {@code Double.valueOf(d1).equals(Double.valueOf(d2))}</pre>
     * (Unlike the {@code ==} operator, this method considers
     * {@code NaN} equal to itself, and 0.0d unequal to -0.0d.)
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @see Double#equals(Object)
     * @since 9
     */
    public static boolean equals(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of floats are
     * <i>equal</i> to one another.  Two arrays are considered equal if both
     * arrays contain the same number of elements, and all corresponding pairs
     * of elements in the two arrays are equal.  In other words, two arrays
     * are equal if they contain the same elements in the same order.  Also,
     * two array references are considered equal if both are {@code null}.
     *
     * Two floats {@code f1} and {@code f2} are considered equal if:
     * <pre>    {@code Float.valueOf(f1).equals(Float.valueOf(f2))}</pre>
     * (Unlike the {@code ==} operator, this method considers
     * {@code NaN} equal to itself, and 0.0f unequal to -0.0f.)
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     * @see Float#equals(Object)
     */
    public static boolean equals(float[] a, float[] a2) {
        int aSize = a.length;
        int bSize = a2.length;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[pos] != a2[pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns true if the two specified arrays of floats, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * <p>Two floats {@code f1} and {@code f2} are considered equal if:
     * <pre>    {@code Float.valueOf(f1).equals(Float.valueOf(f2))}</pre>
     * (Unlike the {@code ==} operator, this method considers
     * {@code NaN} equal to itself, and 0.0f unequal to -0.0f.)
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @see Float#equals(Object)
     * @since 9
     */
    public static boolean equals(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

    /**
     * Returns {@code true} if the two specified arrays of Objects are
     * <i>equal</i> to one another.  The two arrays are considered equal if
     * both arrays contain the same number of elements, and all corresponding
     * pairs of elements in the two arrays are equal.  Two objects {@code e1}
     * and {@code e2} are considered <i>equal</i> if
     * {@code Objects.equals(e1, e2)}.
     * In other words, the two arrays are equal if
     * they contain the same elements in the same order.  Also, two array
     * references are considered equal if both are {@code null}.
     *
     * @param a one array to be tested for equality
     * @param a2 the other array to be tested for equality
     * @return {@code true} if the two arrays are equal
     */
    public static boolean equals(Object[] a, Object[] a2) {
        return net.rim.device.api.util.Arrays.equals(a, a2);
    }

    /**
     * Returns true if the two specified arrays of Objects, over the specified
     * ranges, are <i>equal</i> to one another.
     *
     * <p>Two arrays are considered equal if the number of elements covered by
     * each range is the same, and all corresponding pairs of elements over the
     * specified ranges in the two arrays are equal.  In other words, two arrays
     * are equal if they contain, over the specified ranges, the same elements
     * in the same order.
     *
     * <p>Two objects {@code e1} and {@code e2} are considered <i>equal</i> if
     * {@code Objects.equals(e1, e2)}.
     *
     * @param a the first array to be tested for equality
     * @param aFromIndex the index (inclusive) of the first element in the
     *                   first array to be tested
     * @param aToIndex the index (exclusive) of the last element in the
     *                 first array to be tested
     * @param b the second array to be tested for equality
     * @param bFromIndex the index (inclusive) of the first element in the
     *                   second array to be tested
     * @param bToIndex the index (exclusive) of the last element in the
     *                 second array to be tested
     * @return {@code true} if the two arrays, over the specified ranges, are
     *         equal
     * @throws IllegalArgumentException
     *         if {@code aFromIndex > aToIndex} or
     *         if {@code bFromIndex > bToIndex}
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code aFromIndex < 0 or aToIndex > a.length} or
     *         if {@code bFromIndex < 0 or bToIndex > b.length}
     * @throws NullPointerException
     *         if either array is {@code null}
     * @since 9
     */
    public static boolean equals(Object[] a, int aFromIndex, int aToIndex, Object[] b, int bFromIndex, int bToIndex) {
        int aSize = aFromIndex - aToIndex;
        int bSize = bFromIndex - bToIndex;
        if (bSize != aSize) return false;
        int pos = 0;
        while (pos < aSize) {
            if (a[aFromIndex + pos] != b[bFromIndex + pos]) return false;
            pos++;
        }
        return true;
    }

// Filling

    /**
     * Assigns the specified long vue to each element of the specified array
     * of longs.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     */
    public static void fill(long[] a, long v) {
        net.rim.device.api.util.Arrays.fill(a, v);
    }

    /**
     * Assigns the specified long vue to each element of the specified
     * range of the specified array of longs.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(long[] a, int fromIndex, int toIndex, long v) {
        net.rim.device.api.util.Arrays.fill(a, v, fromIndex, toIndex - fromIndex);
    }

    /**
     * Assigns the specified int value to each element of the specified array
     * of ints.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     */
    public static void fill(int[] a, int v) {
        net.rim.device.api.util.Arrays.fill(a, v);
    }

    /**
     * Assigns the specified int value to each element of the specified
     * range of the specified array of ints.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(int[] a, int fromIndex, int toIndex, int v) {
        net.rim.device.api.util.Arrays.fill(a, v, fromIndex, toIndex - fromIndex);
    }

    /**
     * Assigns the specified short value to each element of the specified array
     * of shorts.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     */
    public static void fill(short[] a, short v) {
        net.rim.device.api.util.Arrays.fill(a, v);
    }

    /**
     * Assigns the specified short value to each element of the specified
     * range of the specified array of shorts.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(short[] a, int fromIndex, int toIndex, short v) {
        net.rim.device.api.util.Arrays.fill(a, v, fromIndex, toIndex - fromIndex);
    }

    /**
     * Assigns the specified char value to each element of the specified
     * range of the specified array of chars.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(char[] a, int fromIndex, int toIndex, char v) {
        net.rim.device.api.util.Arrays.fill(a, v, fromIndex, toIndex - fromIndex);
    }

    /**
     * Assigns the specified byte value to each element of the specified array
     * of bytes.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     */
    public static void fill(byte[] a, byte v) {
        net.rim.device.api.util.Arrays.fill(a, v);
    }

    /**
     * Assigns the specified byte value to each element of the specified
     * range of the specified array of bytes.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(byte[] a, int fromIndex, int toIndex, byte v) {
        net.rim.device.api.util.Arrays.fill(a, v, fromIndex, toIndex - fromIndex);
    }

    /**
     * Assigns the specified boolean value to each element of the specified
     * array of booleans.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     */
    public static void fill(boolean[] a, boolean v) {
        net.rim.device.api.util.Arrays.fill(a, v);
    }

    /**
     * Assigns the specified boolean value to each element of the specified
     * range of the specified array of booleans.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(boolean[] a, int fromIndex, int toIndex, boolean v) {
        net.rim.device.api.util.Arrays.fill(a, v, fromIndex, toIndex - fromIndex);
    }

    /**
     * Assigns the specified double value to each element of the specified
     * array of doubles.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     */
    public static void fill(double[] a, double v) {
        for (int i = 0; i < a.length; i++) a[i] = v;
    }

    /**
     * Assigns the specified double value to each element of the specified
     * range of the specified array of doubles.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(double[] a, int fromIndex, int toIndex, double v) {
        for (; fromIndex < toIndex; fromIndex++) a[fromIndex] = v;
    }

    /**
     * Assigns the specified float value to each element of the specified array
     * of floats.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     */
    public static void fill(float[] a, float v) {
        for (int i = 0; i < a.length; i++) a[i] = v;
    }

    /**
     * Assigns the specified float value to each element of the specified
     * range of the specified array of floats.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     */
    public static void fill(float[] a, int fromIndex, int toIndex, float v) {
        for (; fromIndex < toIndex; fromIndex++) a[fromIndex] = v;
    }

    /**
     * Assigns the specified Object reference to each element of the specified
     * array of Objects.
     *
     * @param a the array to be filled
     * @param v the value to be stored in all elements of the array
     * @throws ArrayStoreException if the specified value is not of a
     *         runtime type that can be stored in the specified array
     */
    public static void fill(Object[] a, Object v) {
        for (int i = 0; i < a.length; i++) a[i] = v;
    }

    /**
     * Assigns the specified Object reference to each element of the specified
     * range of the specified array of Objects.  The range to be filled
     * extends from index {@code fromIndex}, inclusive, to index
     * {@code toIndex}, exclusive.  (If {@code fromIndex==toIndex}, the
     * range to be filled is empty.)
     *
     * @param a the array to be filled
     * @param fromIndex the index of the first element (inclusive) to be
     *        filled with the specified value
     * @param toIndex the index of the last element (exclusive) to be
     *        filled with the specified value
     * @param v the value to be stored in all elements of the array
     * @throws IllegalArgumentException if {@code fromIndex > toIndex}
     * @throws ArrayIndexOutOfBoundsException if {@code fromIndex < 0} or
     *         {@code toIndex > a.length}
     * @throws ArrayStoreException if the specified value is not of a
     *         runtime type that can be stored in the specified array
     */
    public static void fill(Object[] a, int fromIndex, int toIndex, Object v) {
        for (; fromIndex < toIndex; fromIndex++) a[fromIndex] = v;
    }

// Cloning
    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code (byte)0}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static byte[] copyOf(byte[] original, int newLength) {
        if (newLength > original.length) {
            byte[] newArray = new byte[newLength];
            System.arraycopy(original, 0, newArray, 0, original.length);
            return newArray;
        } else return net.rim.device.api.util.Arrays.copy(original, 0, newLength);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code (short)0}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static short[] copyOf(short[] original, int newLength) {
        if (newLength > original.length) {
            short[] newArray = new short[newLength];
            System.arraycopy(original, 0, newArray, 0, original.length);
            return newArray;
        } else return net.rim.device.api.util.Arrays.copy(original, 0, newLength);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static int[] copyOf(int[] original, int newLength) {
        if (newLength > original.length) {
            int[] newArray = new int[newLength];
            System.arraycopy(original, 0, newArray, 0, original.length);
            return newArray;
        } else return net.rim.device.api.util.Arrays.copy(original, 0, newLength);
    }


    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0L}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static long[] copyOf(long[] original, int newLength) {
        if (newLength > original.length) {
            long[] newArray = new long[newLength];
            System.arraycopy(original, 0, newArray, 0, original.length);
            return newArray;
        } else return net.rim.device.api.util.Arrays.copy(original, 0, newLength);
    }

    /**
     * Copies the specified array, truncating or padding with null characters (if necessary)
     * so the copy has the specified length.  For all indices that are valid
     * in both the original array and the copy, the two arrays will contain
     * identical values.  For any indices that are valid in the copy but not
     * the original, the copy will contain {@code '\u005cu0000'}.  Such indices
     * will exist if and only if the specified length is greater than that of
     * the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with null characters
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static char[] copyOf(char[] original, int newLength) {
        if (newLength > original.length) {
            char[] newArray = new char[newLength];
            System.arraycopy(original, 0, newArray, 0, original.length);
            return newArray;
        } else return net.rim.device.api.util.Arrays.copy(original, 0, newLength);
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0f}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static float[] copyOf(float[] original, int newLength) {
        float[] newArray = new float[newLength];
        System.arraycopy(original, 0, newArray, 0, Math.min(original.length, newLength));
        return newArray;
    }

    public static <T> T[] copyOf(T[] original, int newLength) {
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[newLength];
        System.arraycopy(original, 0, copy, 0,
                Math.min(original.length, newLength));
        return copy;
    }

    /**
     * Copies the specified array, truncating or padding with zeros (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code 0d}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with zeros
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static double[] copyOf(double[] original, int newLength) {
        double[] newArray = new double[newLength];
        System.arraycopy(original, 0, newArray, 0, Math.min(original.length, newLength));
        return newArray;
    }

    /**
     * Copies the specified array, truncating or padding with {@code false} (if necessary)
     * so the copy has the specified length.  For all indices that are
     * valid in both the original array and the copy, the two arrays will
     * contain identical values.  For any indices that are valid in the
     * copy but not the original, the copy will contain {@code false}.
     * Such indices will exist if and only if the specified length
     * is greater than that of the original array.
     *
     * @param original the array to be copied
     * @param newLength the length of the copy to be returned
     * @return a copy of the original array, truncated or padded with false elements
     *     to obtain the specified length
     * @throws NegativeArraySizeException if {@code newLength} is negative
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static boolean[] copyOf(boolean[] original, int newLength) {
        boolean[] newArray = new boolean[newLength];
        System.arraycopy(original, 0, newArray, 0, Math.min(original.length, newLength));
        return newArray;
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code (byte)0} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with zeros to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static byte[] copyOfRange(byte[] original, int from, int to) {
        return net.rim.device.api.util.Arrays.copy(original, from, to - from);
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code (short)0} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with zeros to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static short[] copyOfRange(short[] original, int from, int to) {
        return net.rim.device.api.util.Arrays.copy(original, from, to - from);
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code 0} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with zeros to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static int[] copyOfRange(int[] original, int from, int to) {
        return net.rim.device.api.util.Arrays.copy(original, from, to - from);
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code 0L} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with zeros to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static long[] copyOfRange(long[] original, int from, int to) {
        return net.rim.device.api.util.Arrays.copy(original, from, to - from);
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code '\u005cu0000'} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with null characters to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static char[] copyOfRange(char[] original, int from, int to) {
        return net.rim.device.api.util.Arrays.copy(original, from, to - from);
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code 0f} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with zeros to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static float[] copyOfRange(float[] original, int from, int to) {
        float[] newArray = new float[to - from];
        System.arraycopy(original, from, newArray, 0, newArray.length);
        return newArray;
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code 0d} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with zeros to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static double[] copyOfRange(double[] original, int from, int to) {
        double[] newArray = new double[to - from];
        System.arraycopy(original, from, newArray, 0, newArray.length);
        return newArray;
    }

    /**
     * Copies the specified range of the specified array into a new array.
     * The initial index of the range ({@code from}) must lie between zero
     * and {@code original.length}, inclusive.  The value at
     * {@code original[from]} is placed into the initial element of the copy
     * (unless {@code from == original.length} or {@code from == to}).
     * Values from subsequent elements in the original array are placed into
     * subsequent elements in the copy.  The final index of the range
     * ({@code to}), which must be greater than or equal to {@code from},
     * may be greater than {@code original.length}, in which case
     * {@code false} is placed in all elements of the copy whose index is
     * greater than or equal to {@code original.length - from}.  The length
     * of the returned array will be {@code to - from}.
     *
     * @param original the array from which a range is to be copied
     * @param from the initial index of the range to be copied, inclusive
     * @param to the final index of the range to be copied, exclusive.
     *     (This index may lie outside the array.)
     * @return a new array containing the specified range from the original array,
     *     truncated or padded with false elements to obtain the required length
     * @throws ArrayIndexOutOfBoundsException if {@code from < 0}
     *     or {@code from > original.length}
     * @throws IllegalArgumentException if {@code from > to}
     * @throws NullPointerException if {@code original} is null
     * @since 1.6
     */
    public static boolean[] copyOfRange(boolean[] original, int from, int to) {
        return net.rim.device.api.util.Arrays.copy(original, from, to - from);
    }

    public static <T> T[] copyOfRange(T[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0) {
            throw new IllegalArgumentException();
        }
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) new Object[newLength];
        System.arraycopy(original, from, copy, 0,
                Math.min(original.length - from, newLength));
        return copy;
    }

    public static <T> List<T> asList(T... a) {
        return new ArrayList<>(a);
    }

}
