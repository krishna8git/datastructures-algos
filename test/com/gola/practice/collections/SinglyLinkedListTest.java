package com.gola.practice.collections;

import org.junit.Assert;
import org.junit.Test;

public class SinglyLinkedListTest {

    @Test
    public void testAddSet () {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(10, 20, 30, 40, 50, 60);
        Assert.assertEquals(6, list.size());
        Assert.assertEquals(list.toString(), "[ 10 20 30 40 50 60 ]");

        list.add(2, 100);
        list.add(3, 1000);
        list.add(4, 10000);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(9, list.size());
        Assert.assertEquals(list.toString(), "[ 10 20 100 1000 10000 30 40 50 60 ]");

        list.set(2, 200);
        list.set(3, 3000);
        list.set(4, 40000);
        Assert.assertEquals(9, list.size());
        Assert.assertEquals(list.toString(), "[ 10 20 200 3000 40000 30 40 50 60 ]");

        list.addFirst(1000);
        Assert.assertEquals(10, list.size());
        Assert.assertEquals(list.toString(), "[ 1000 10 20 200 3000 40000 30 40 50 60 ]");

        list.addLast(20000);
        Assert.assertEquals(11, list.size());
        Assert.assertEquals(list.toString(), "[ 1000 10 20 200 3000 40000 30 40 50 60 20000 ]");

        list.add(list.size(), 13400);
        Assert.assertEquals(12, list.size());
        Assert.assertEquals(list.toString(), "[ 1000 10 20 200 3000 40000 30 40 50 60 20000 13400 ]");

        SinglyLinkedList<Integer> subList = (SinglyLinkedList<Integer>)list.subList(2, 5);
        Assert.assertEquals(3, subList.size());
        Assert.assertEquals(subList.toString(), "[ 20 200 3000 ]");

    }
}
