package org.sskit.java.collections.list;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private int size;

    private Node<E> first;
    private Node<E> last;

    private class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    public SinglyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public SinglyLinkedList(Collection<? extends E> elements) {
        addAll(elements);
    }

    public SinglyLinkedList(List<? extends E> elements, int from, int to) {
        if (from > to) {
            throw new IndexOutOfBoundsException(" Invalid index range. Valid Index Range 0 - " + (size - 1));
        }
        if (from >= elements.size() | to >= elements.size()) {
            throw new IndexOutOfBoundsException(" Invalid index range. Valid Index Range 0 - " + (size - 1));
        }

        for (int i = from; i < to; i++) {
            add(elements.get(i));
        }
    }

    @SafeVarargs
    public SinglyLinkedList(E... elements) {
        for (E element : elements) {
            add(element);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        E ele = (E) o;
        if (isFirst(ele) || isLast(ele)) {
            return true;
        }

        for (Node<E> temp = first.next; temp != last; temp = temp.next) {
            if (o.equals(temp.item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(first);
    }

    @SuppressWarnings("hiding")
    private class LinkedListIterator<E> implements Iterator<E> {
        private Node<E> _first;

        public LinkedListIterator(Node<E> first) {
            this._first = first;
        }

        @Override
        public boolean hasNext() {
            return _first != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E ele = _first.item;
            _first = _first.next;
            return ele;
        }
    }

    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public boolean addFirst(E e) {
        Node<E> node = new Node<>(e);
        node.next = first;
        first = node;
        size++;
        return true;
    }

    public boolean addLast(E e) {
        linkLast(e);
        return true;
    }

    private void linkLast(E e) {
        checkForNull(e);
        Node<E> newLast = new Node<>(e);
        if (first == null) {
            first = newLast;
        } else {
            last.next = newLast;
        }
        last = newLast;
        ++size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        checkForNull(o);
        if (isFirst((E) o)) {
            first = first.next;
            --size;
            return true;
        }
        Node<E> prev = first;
        for (Node<E> n = first.next; n != null; prev = n, n = n.next) {
            if (n.equals(o)) {
                prev.next = n.next;
                n.next = null; // Safe - TO avoid Dangling
                --size;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        first = last = null; // Let the GC take care of cleaning.
        size = 0;
    }

    @Override
    public E set(int index, E element) {
        E prevEle;
        if (index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + " valied index range 0 - " + (size - 1));
        }
        if (isLast(element)) {
            prevEle = last.item;
            last.item = element;
            return prevEle;
        }

        Node<E> temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        prevEle = temp.item;
        temp.item = element;
        return prevEle;
    }

    @Override
    public void add(int index, E element) {
        if (index >= size) {
            linkLast(element);
            return;
        }
        if (index == 0) {
            addFirst(element);
            return;
        }

        Node<E> temp = first;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }

        Node<E> newNode = new Node<>(element);
        newNode.next = temp.next;
        temp.next = newNode;
        ++size;
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        for (E e : elements) {
            add(e);
        }
        return true;
    }

    @Override
    public void sort() {
        sort(null);
    }

    /**
     * This method implements in-place merge sort algorithm on linked lists.
     */
    @Override
    public void sort(Comparator<E> c) {
        first = mergeSort(first, c);
    }

    private Node mergeSort(Node<E> h, Comparator<E> c) {
        if (h == null || h.next == null) {
            return h;
        }

        Node mid = getMiddleNode(h);
        Node midNext = mid.next;
        mid.next = null;

        Node left = mergeSort(h, c);
        Node right = mergeSort(midNext, c);

        Node sortedList = merge(left, right, c);
        return sortedList;
    }

    private Node merge(Node<E> left, Node<E> right, Comparator<E> c) {
        Node result = null;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        int diff = 0;
        E i1 = left.item;
        E i2 = right.item;
        if (c == null) {
            if (!(left.item instanceof Comparable)) {
                throw new IllegalArgumentException("The element should either be Comparable or a new Comparator instance needs to be provided");
            } else {
                Comparable leftItem = (Comparable<? extends E>) i1;
                Comparable rightItem = (Comparable<? extends E>) i2;
                diff = leftItem.compareTo(rightItem);
            }
        } else {
            diff = c.compare(i1, i2);
        }
        if (diff <= 0) {
            result = left;
            result.next = merge(left.next, right, c);
        } else {
            result = right;
            result.next = merge(left, right.next, c);
        }
        return result;
    }

    public E mid(Node<E> h) {
        return getMiddleNode(h).item;
    }

    private Node<E> getMiddleNode(Node<E> h) {
        if (h == null) {
            return h;
        }

        Node slow, fast;
        slow = fast = h;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Override
    public E remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("SinglyLinkedLIst doesn't support list iterator");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new SinglyLinkedList<>(this, fromIndex, toIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (E e : this) {
            sb.append(e).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void checkForNull(Object e) {
        if (e == null) {
            throw new NullPointerException();
        }
    }

    public boolean isFirst(E e) {
        return e.equals(first.item);
    }

    public boolean isLast(E e) {
        return e.equals(last.item);
    }

    public E first() {
        return first.item;
    }

    public E last() {
        return last.item;
    }

    @Override
    public E get(int index) {
        Node<E> temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

}
