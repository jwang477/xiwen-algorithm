package com.wang.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class FileReaderTest {

    @Test
    public void test01() throws Exception{
        FileReader reader = new FileReader("c:/develop/b.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";
        HashSet<String> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        int totalCount  = 0;
        while ((line = bufferedReader.readLine()) != null){
            totalCount++;
            if (!set.add(line)){
                //System.out.println("line = " + line);
                count++;
            }else{
                String num = line.replace("喜闻测试消息:", "");
                list.add(Integer.valueOf(num));
            }
        }
        bufferedReader.close();
        reader.close();
        System.out.println("count = " + count);
        System.out.println("totalCount = " + totalCount);
        Collections.sort(list);
        System.out.println(list);
        System.out.println("size = " + list.size());
        for (int i=0;i<list.size();i++){
            if (list.get(i)!=i+1){
                //System.out.println(list.get(i));
            }
        }
    }
    @Test
    public void test02() throws Exception{
        FileReader reader = new FileReader("c:/develop/b.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";
        HashSet<String> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        int totalCount  = 0;
        while ((line = bufferedReader.readLine()) != null){
            totalCount++;
           if (!line.contains("喜闻")){
               count++;
           }
        }
        bufferedReader.close();
        reader.close();
        System.out.println("count = " + count);
        System.out.println("totalCount = " + totalCount);
        //Collections.sort(list);
        //System.out.println(list);
        //System.out.println("size = " + list.size());
        //for (int i=0;i<list.size();i++){
        //    if (list.get(i)!=i+1){
        //        //System.out.println(list.get(i));
        //    }
        //}
    }
}
