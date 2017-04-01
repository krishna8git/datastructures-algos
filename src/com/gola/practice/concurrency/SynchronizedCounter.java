package com.gola.practice.concurrency;

public class SynchronizedCounter implements Counter {

    int value;

    @Override
    public synchronized int increment () {
        return ++value;
    }

    @Override
    public synchronized int decrement () {
        return --value;
    }

    @Override
    public synchronized int add (int x) {
        value = value + x;
        return value;
    }

    @Override
    public synchronized int subtract (int x) {
        value = value - x;
        return value;
    }

    @Override
    public int get () {
        return value;
    }

}
