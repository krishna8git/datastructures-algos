package com.gola.practice.collections;

import org.junit.Assert;
import org.junit.Test;

public class UnboundedStackTest {

    @Test
    public void testPushPop () {
        Stack<Integer> intStack = new UnboundedStack<>(65, 56, 64, 25, 94, 88, 88, 24, 55, 61, 6, 64, 46, 89, 79);
        Assert.assertEquals(15, intStack.size());
        Assert.assertEquals("[ 79 89 46 64 6 61 55 24 88 88 94 25 64 56 65 ]", intStack.toString());

        Assert.assertEquals(new Integer(79), intStack.pop());
        Assert.assertEquals(new Integer(89), intStack.pop());
        Assert.assertEquals(new Integer(46), intStack.pop());
        Assert.assertEquals(new Integer(64), intStack.pop());
        Assert.assertEquals(new Integer(6), intStack.pop());
        Assert.assertEquals(10, intStack.size());
        Assert.assertEquals("[ 61 55 24 88 88 94 25 64 56 65 ]", intStack.toString());

        intStack.push(10000);
        intStack.push(20000);
        intStack.push(30000);
        Assert.assertEquals(13, intStack.size());
        Assert.assertEquals("[ 30000 20000 10000 61 55 24 88 88 94 25 64 56 65 ]", intStack.toString());

        Assert.assertEquals(new Integer(30000), intStack.pop());
        Assert.assertEquals(new Integer(20000), intStack.pop());
        Assert.assertEquals(new Integer(10000), intStack.pop());
        Assert.assertEquals(10, intStack.size());
        Assert.assertEquals("[ 61 55 24 88 88 94 25 64 56 65 ]", intStack.toString());
    }
}
