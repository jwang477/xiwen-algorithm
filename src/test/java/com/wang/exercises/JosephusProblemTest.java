package com.wang.exercises;

import com.wang.util.ArrayUtil;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class JosephusProblemTest {


    @Test
    public void test() {
        Random random = new Random();
        int N = random.nextInt(10) + 1;
        List<Integer> list = ArrayUtil.initSortArrayList(N, N, false);
        int M = random.nextInt(N);
        System.out.println("N = " + N);
        System.out.println("M = " + M);
        System.out.println("list = " + list);
        int count = 0;
        while (true) {
            if (list.size() == 1) {
                break;
            }
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext()) {
                Integer num = iterator.next();
                if (count == M) {
                    count = 0;
                    iterator.remove();
                    System.out.println("num = " + num);
                    if (list.size() == 1) {
                        break;
                    }
                }  else {
                    count++;
                }
            }
        }
        System.out.println("list = " + list);

    }
}
