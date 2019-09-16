package org.sskit.java.collections;

import org.sskit.java.collections.list.List;

import java.util.Comparator;

public class Utils {


    public static <E> boolean isListSorted(List<E> list, Comparator<E> c) {
        for (int i = 1; i < list.size(); i++) {
            int diff = 0;
            E i1 = list.get(i - 1);
            E i2 = list.get(i);
            if (c == null) {
                if (!(i1 instanceof Comparable)) {
                    throw new IllegalArgumentException("The element should either be Comparable or a new Comparator instance needs to be provided");
                } else {
                    Comparable leftItem = (Comparable<? extends E>) i1;
                    Comparable rightItem = (Comparable<? extends E>) i2;
                    diff = leftItem.compareTo(rightItem);

                }
            } else {
                diff = c.compare(i1, i2);
            }
            if (diff > 0) {
                return false;
            }
        }
        return true;
    }
}
