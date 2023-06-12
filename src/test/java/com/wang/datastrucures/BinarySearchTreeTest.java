package com.wang.datastrucures;

import com.wang.datastructures.BinarySearchTree;
import com.wang.util.ArrayUtil;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class BinarySearchTreeTest {

    @Test
    public void binarySearchTreeTest() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> sortArrayList = ArrayUtil.initSortArrayList(10, 20, false);
        Collections.shuffle(sortArrayList);
        System.out.println("sortArrayList = " + sortArrayList);

        for (Integer num : sortArrayList) {
            tree.put(num);
        }
        tree.printTree();
        //tree.preOrderPrint();
        tree.postOrderPrint2();
    }
}
