package com.wang.exercises;

import com.wang.util.ArrayUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个已排序的表L1和L2，只使用基本的表操作编写计算L1∩L2的过程
 */
public class ArrayIntersectionTest {


    @Test
    public void test01() {
        List<Integer> l1 = ArrayUtil.initSortArrayList(((int) (Math.random() * 10) + 1),10);
        List<Integer> l2 = ArrayUtil.initSortArrayList(((int) (Math.random() * 10) + 1),10);
        System.out.println("L1 = " + l1);
        System.out.println("L2 = " + l2);
        int l1Size = l1.size();
        int l2Size = l2.size();
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; (i < l1Size && j < l2Size); ) {
            if (l1.get(i) < l2.get(j)) {
                i++;
            } else if (l1.get(i) > l2.get(j)) {
                j++;
            } else {
                list.add(l1.get(i));
                i++;
                j++;
            }
        }
        System.out.println("list = " + list);

    }


}
