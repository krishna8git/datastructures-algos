package com.gola.practice.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {

    private final ReentrantLock lock;

    private int                 value;

    ReentrantLockCounter () {
        lock = new ReentrantLock();
    }

    @Override
    public int increment () {
        ReentrantLock lock = this.lock;
        lock.lock();
        assert lock.getHoldCount() == 1; // only for dev
        try {
            return ++value;
        } finally {
            lock.unlock();
            assert lock.getHoldCount() == 0; // only for dev
        }
    }

    @Override
    public int decrement () {
        ReentrantLock lock = this.lock;
        lock.lock();
        assert lock.getHoldCount() == 1; // only for dev
        try {
            return --value;
        } finally {
            lock.unlock();
            assert lock.getHoldCount() == 0; // only for dev
        }
    }

    @Override
    public int add ( int x ) {
        ReentrantLock lock = this.lock;
        lock.lock();
        assert lock.getHoldCount() == 1; // only for dev
        try {
            return (value = value + x);
        } finally {
            lock.unlock();
            assert lock.getHoldCount() == 0; // only for dev
        }
    }

    @Override
    public int subtract ( int x ) {
        ReentrantLock lock = this.lock;
        lock.lock();
        assert lock.getHoldCount() == 1; // only for dev
        try {
            return (value = value - x);
        } finally {
            lock.unlock();
            assert lock.getHoldCount() == 0; // only for dev
        }
    }

    @Override
    public int get () {
        ReentrantLock lock = this.lock;
        lock.lock();
        assert lock.getHoldCount() == 1; // only for dev
        try {
            return value;
        } finally {
            lock.unlock();
            assert lock.getHoldCount() == 0; // only for dev
        }
    }
}
