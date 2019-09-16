package org.sskit.java.collections.list;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<E> extends Iterable<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(Object o);

    E get(int index);

    void clear();

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    ListIterator<E> listIterator();

    List<E> subList(int fromIndex, int toIndex);

    boolean addAll(Collection<? extends E> c);

    void sort();

    void sort(Comparator<E> c);

}
