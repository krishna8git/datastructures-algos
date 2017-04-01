package com.gola.practice.concurrency.queue;

public interface Queue<E> {

    void put(E e) throws InterruptedException;

    E take() throws InterruptedException;

    int size();

}
