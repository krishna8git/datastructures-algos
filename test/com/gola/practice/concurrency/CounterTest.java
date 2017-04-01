package com.gola.practice.concurrency;

import static org.junit.Assert.*;

import org.junit.Test;

public class CounterTest {

    @Test
    public void testSynchronizedCounter () throws InterruptedException {
        Counter counter = new SynchronizedCounter();

        startThreads(counter);

        assertEquals(200000, counter.get());
    }

    @Test
    public void testReentrantLockCounter () throws InterruptedException {
        Counter counter = new ReentrantLockCounter();

        startThreads(counter);

        assertEquals(200000, counter.get());
    }

    private void startThreads ( Counter counter ) throws InterruptedException {

        Thread t1 = new Thread( () -> increment(counter), "INCREMENT THREAD");
        Thread t2 = new Thread( () -> decrement(counter), "DECREMENT THREAD");
        Thread t3 = new Thread( () -> add(counter, 4), "ADDER THREAD");
        Thread t4 = new Thread( () -> subtract(counter, 2), "SUBTRACT THREAD");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

    }

    private void increment ( Counter counter ) {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }

    private void decrement ( Counter counter ) {
        for (int i = 0; i < 100000; i++) {
            counter.decrement();
        }
    }

    private void add ( Counter counter, int j ) {
        for (int i = 0; i < 100000; i++) {
            counter.add(j);
        }
    }

    private void subtract ( Counter counter, int j ) {
        for (int i = 0; i < 100000; i++) {
            counter.subtract(j);
        }
    }

}
