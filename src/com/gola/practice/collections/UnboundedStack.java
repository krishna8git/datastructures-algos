package com.gola.practice.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class UnboundedStack<E> implements Stack<E> {

    @SuppressWarnings("hiding")
    private class Node<E> {
        E       ele;
        Node<E> next;

        public Node(E ele) {
            this.ele = ele;
            this.next = null;
        }
    }

    private Node<E> top = null;

    private int     size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedStackIterator<>(top);
    }

    @Override
    public boolean push(E e) {
        Node<E> newTop = new Node<E>(e);
        newTop.next = top;
        top = newTop;
        ++size;
        return true;
    }

    @Override
    public E pop() {
        Node<E> temp = top;
        top = top.next;
        --size;
        return temp.ele;
    }

    @Override
    public void clear() {
        top = null; // Let the GC take care of garbage
        size = 0;
    }

    @SuppressWarnings("hiding")
    private class LinkedStackIterator<E> implements Iterator<E> {
        private Node<E> _top;

        public LinkedStackIterator(Node<E> top) {
            this._top = top;
        }

        @Override
        public boolean hasNext() {
            return _top != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E ele = _top.ele;
            _top = _top.next;
            return ele;
        }
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

    public static void main(String[] args) {
        Stack<Integer> intStack = new UnboundedStack<>();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            intStack.push(random.nextInt(100));
        }
        System.out.println("Stack Size: " + intStack.size());
        System.out.println(intStack);

        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());

        System.out.println("Stack Size: " + intStack.size());
        System.out.println(intStack);

        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());
        System.out.println("Popped Item: " + intStack.pop());

        System.out.println("Stack Size: " + intStack.size());
        System.out.println(intStack);

        for (int i = 0 ; i < 10 ; i++ ) {
            intStack.push(random.nextInt(200));
        }

        System.out.println("Stack Size: " + intStack.size());
        System.out.println(intStack);

        intStack.push(100); // Should give RuntimeException because of Stack Overflow
    }
}
