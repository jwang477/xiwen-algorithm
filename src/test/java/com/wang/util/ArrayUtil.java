package com.wang.util;

import java.util.*;

public class ArrayUtil {


    public static int[] initSortArray(int length) {
        int[] arr = new int[length];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt();
        }
        Arrays.sort(arr);
        return arr;
    }

    public static List<Integer> initSortArrayList(int length) {
        return initSortArrayList(length, 1000, true);
    }

    public static List<Integer> initSortArrayList(int length, int maxRandom) {
        return initSortArrayList(length, maxRandom, true);
    }

    public static List<Integer> initSortArrayList(int length, int maxRandom, boolean repetition) {
        ArrayList<Integer> list = new ArrayList<>(length);
        Random random = new Random();
        if (repetition) {

            for (int i = 0; i < length; i++) {
                list.add(random.nextInt(maxRandom) + 1);
            }
        } else {
            if (length > maxRandom) {
                throw new RuntimeException("数组不可重复时 最大值需大于长度");
            }
            int count = 0;
            while (true) {
                int nextInt = random.nextInt(maxRandom) + 1;
                if (!list.contains(nextInt)) {
                    list.add(nextInt);
                    count++;
                    if (count == length) {
                        break;
                    }
                }
            }
        }

        Collections.sort(list);
        return list;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            throw new NullPointerException();
        }
        System.out.print("[ ");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ",");
        }
        if (arr.length < 1) {
            System.out.println(" ]");
        }else{
            System.out.println(arr[arr.length - 1] + " ]");
        }


    }
}
