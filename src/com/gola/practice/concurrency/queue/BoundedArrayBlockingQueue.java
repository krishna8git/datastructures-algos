package com.gola.practice.concurrency.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedArrayBlockingQueue<E> implements Queue<E> {

    private final ReentrantLock lock      = new ReentrantLock();

    private final Condition     notEmpty = lock.newCondition();

    private final Condition     notFull = lock.newCondition();

    private final int           capacity;

    private int                 size;

    private final E[]           elements;

    BoundedArrayBlockingQueue () {
        this(10);
    }

    @SuppressWarnings ( "unchecked" )
    BoundedArrayBlockingQueue ( int capacity ) {
        this.capacity = capacity;
        elements = (E[]) new Object[capacity];
    }

    @Override
    public void put ( E e ) throws InterruptedException {
        ReentrantLock lock = this.lock;
        lock.lock();

        try {
            String thread = Thread.currentThread().getName();
            while (size >= capacity) {
                System.out.println(thread + " Waiting for CONSUMER To Consume some elements");
                notFull.await();
            }
            elements[size++] = e;
            System.out.println(thread + " Produced Element: " + e + " at Index: " + (size - 1));
            notEmpty.signal();
        } finally {
            lock.unlock();
            assert lock.getHoldCount() == 0;
        }
    }

    @Override
    public E take () throws InterruptedException {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            String thread = Thread.currentThread().getName();
            while (size <= 0) {
                System.out.println(thread + " Waiting for PRODUCER To Produce some elements");
                notEmpty.await();
            }
            E element = elements[--size];
            System.out.println(thread + " Consumed Element: " + element + " at Index: " + (size + 1));
            notFull.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size () {
        ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return size;
        } finally {
            lock.unlock();
        }
    }

}
