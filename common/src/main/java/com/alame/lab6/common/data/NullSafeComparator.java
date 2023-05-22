package com.alame.lab6.common.data;

/**
 * class for comparing objects that can be null
 */
public class NullSafeComparator{
    /**
     * compare objects that can be null
     * @param c1 - first object
     * @param c2 - second object
     * @return a negative integer, zero, or a positive integer as first object is less than, equal to, or greater than the second object.
     * @param <T> the type of objects that this object may be compared to
     */
    public static <T extends Comparable<T>> int compare(T c1, T c2){
        if (c1==c2) return 0;
        if (c1 == null) return -1;
        if (c2 == null) return 1;
        return c1.compareTo(c2);
    }
}
