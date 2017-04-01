package com.gola.practice.concurrency.queue;

import java.util.Random;

import org.junit.Test;

public class BoundedArrayBlockingQueueTest {

    @Test
    public void enqueDeque () throws InterruptedException {
        Queue<Integer> queue = new BoundedArrayBlockingQueue<>();

        Thread t1 = new Thread( () -> putElements(queue), "[PRODUCER]");
        Thread t2 = new Thread( () -> takeElements(queue), "[CONSUMER]");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private void takeElements ( Queue<Integer> queue ) {
        for (int i = 0; i < 200; i++) {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void putElements ( Queue<Integer> queue ) {
        Random random = new Random();
        final int bound = 100;
        for (int i = 0; i < 200; i++) {
            try {
                queue.put(random.nextInt(bound));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
