package com.gola.practice.concurrency;

import static org.junit.Assert.*;

import org.junit.Test;

public class CounterTest {

    @Test
    public void testSynchronizedCounter() throws InterruptedException {
        Counter counter = new SynchronizedCounter();

        startThreads(counter);

        /*
         *  At last, if the result is consistent, counter should have the value 200000.
         *  Because Increment Task -> Incremented it 100000 times -> now counter's latest value is 100000.
         *  Then    Decrement Task -> Decremented it 100000 times -> now counter's latest value is 0
         *  Then    Add Task       -> Added       4  100000 times -> now counter's latest value is 400000
         *  Then    Subtract  Task -> Subtracted  2  100000 times -> now counter's latest value is 200000
         *  Now we would expect counter to be consistent because we used synchronization.
         *  So no matter how many times we run this code it will always give us 200000.
         *  In the absence of synchronization this test may not be successful every time we run.
         */
        assertEquals(200000, counter.get());
    }

    @Test
    public void testReentrantLockCounter() throws InterruptedException {
        Counter counter = new ReentrantLockCounter();

        startThreads(counter);
        /*
         *  At last, if the result is consistent, counter should have the value 100.
         *  Because Increment Task -> Incremented it 50 times -> now counter's latest value is 50
         *  Then    Decrement Task -> Decremented it 50 times -> now counter's latest value is 0
         *  Then    Add Task       -> Added       4  50 times -> now counter's latest value is 200
         *  Then    Subtract  Task -> Subtracted  2  50 times -> now counter's latest value is 100
         *  Now we would expect counter to be consistent because we used synchronization.
         *  So no matter how many times we run this code it will always give us 100.
         *  In the absence of synchronization this test may not be successful every time we run.
         */
        assertEquals(200000, counter.get()); 
    }

    private void startThreads (Counter counter) throws InterruptedException {

        Runnable incrTask = new IncrementTask(counter);
        Runnable decrTask = new DecrementTask(counter);
        Runnable addTask = new AddTask(counter);
        Runnable subTask = new SubtractTask(counter);

        Thread t1 = new Thread(incrTask);
        Thread t2 = new Thread(decrTask);
        Thread t3 = new Thread(addTask);
        Thread t4 = new Thread(subTask);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

    }
}

class IncrementTask implements Runnable {
    private Counter counter;

    IncrementTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run () {
        for (int i = 0 ; i < 100000 ; i++ ) {
            counter.increment();
        }
    } 
}

class DecrementTask implements Runnable {
    private Counter counter;

    DecrementTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run () {
        for (int i = 0 ; i < 100000 ; i++ ) {
            counter.decrement();
        }
    } 
}

class AddTask implements Runnable {
    private Counter counter;

    AddTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run () {
        for (int i = 0 ; i < 100000 ; i++ ) {
            counter.add(4);
        }
    } 
}

class SubtractTask implements Runnable {
    private Counter counter;

    SubtractTask(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run () {
        for (int i = 0 ; i < 100000 ; i++ ) {
            counter.subtract(2);
        }
    } 
}
