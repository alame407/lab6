package com.alame.lab6.client.utility.structers;

import java.util.LinkedList;

/**
 * limit max size queue
 * @param <E>
 */
public class LimitedQueue<E> extends LinkedList<E> {
    /**
     * limit of max size
     */
     final int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    /**
     * add element in queue, if size > limit delete first element
     * @param o element to append in queue
     * @return element was added
     */
    @Override
    public boolean add(E o) {
        boolean added = super.add(o);
        while (added && size() > limit) {
            super.remove();
        }
        return added;
    }

}
