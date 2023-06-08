package com.wang.exercises;

import com.wang.util.ArrayUtil;
import org.junit.Test;

import java.util.List;

/**
 * 给定两个已排序的表L1和L2，只使用基本的表操作编写计算L1 ∪ L2的过程
 */
public class ArrayUnionSetTest {


    @Test
    public void test() {
        List<Integer> l1 = ArrayUtil.initSortArrayList(((int) (Math.random() * 10) + 1), 10, false);
        List<Integer> l2 = ArrayUtil.initSortArrayList(((int) (Math.random() * 10) + 1), 10, false);

        System.out.println("L1 = " + l1);
        System.out.println("L2 = " + l2);
        int l1Size = l1.size();
        int l2Size = l2.size();
        if (l1Size == 0) {
            //return l1;
            return;
        }
        if (l2Size == 0) {
            //return l2;
            return;
        }
        for (int i = 0, j = 0; j < l2Size; ) {
            if (l1.get(i) < l2.get(j)) {
                if (i == l1.size() - 1) {
                    l1.add(l2.get(j));
                    j++;
                }
                i++;
            } else if (l1.get(i) > l2.get(j)) {
                l1.add(i, l2.get(j));
                j++;
                i++;
            } else {
                j++;
            }
        }
        System.out.println("l1 = " + l1);

    }
}
