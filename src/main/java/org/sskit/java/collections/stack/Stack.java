package org.sskit.java.collections.stack;

import java.util.Iterator;

public interface Stack<E> extends Iterable<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    boolean push(E e);

    E pop();

    void clear();
}
