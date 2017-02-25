package com.gola.practice.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Not thread-safe. 
 * @author Krishna
 */
public class BoundedStack<E> implements Stack<E> {

    private int              top;

    private E[]              stackData;

    private static final int DEFAULT_CAPACITY = 10;

    public BoundedStack() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public BoundedStack(int capacity) {
        top = -1;
        stackData = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator<E>(top);
    }

    @Override
    public boolean push(E e) {
        checkForNull(e);
;
        if (top >= stackData.length) {
            throw new RuntimeException("Stack Overflow");
        }
        stackData[++top] = e;
        return true;
    }

    private void checkForNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException("null not allwed in this Stack");
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack Underflow");
        }

        E ele = stackData[top--];

        return ele;
    }

    @Override
    public void clear() {
        top = -1;
    }

    @SuppressWarnings("hiding")
    private class StackIterator<E> implements Iterator<E> {

        private int cursor;

        public StackIterator(int top) {
            cursor = top;
        }

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (E) stackData[cursor--];
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
        Stack<Integer> intStack = new BoundedStack<>(15);
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

    }
}
