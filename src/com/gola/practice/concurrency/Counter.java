package com.gola.practice.concurrency;

public interface Counter {

    int increment ();

    int decrement ();

    int add (int x);

    int subtract (int x);

    int get();
}
