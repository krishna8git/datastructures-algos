package org.sskit.java.collections;

import java.util.Random;

import org.sskit.java.collections.stack.BoundedStack;
import org.sskit.java.collections.stack.Stack;
import org.junit.Assert;
import org.junit.Test;

public class BoundedStackTest {

    private Random random = new Random();
    private Stack<Integer> intStack;

    private void initStackTest(int stackSize) {
        intStack = new BoundedStack<>(stackSize);
    }

    @Test
    public void testPushAndPop() {
        int size = 200_00_000; // 20 million
        initStackTest(size);

        for (int i = 0; i < size; i++) {
            intStack.push(random.nextInt(200000));
        }
        Assert.assertEquals(size, intStack.size());

        for (int i = 0; i < 100_00_000; i++) {
            intStack.pop();
        }
        Assert.assertEquals(100_00_000, intStack.size());
    }

    @Test(expected = RuntimeException.class)
    public void testOverFlow() {
        int size = 200_00_000; // 20 million
        initStackTest(size);

        for (int i = 0; i < size; i++) {
            intStack.push(random.nextInt(200000));
        }
        intStack.push(random.nextInt(100));
    }
}
