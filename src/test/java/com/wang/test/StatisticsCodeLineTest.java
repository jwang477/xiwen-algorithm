package com.wang.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class StatisticsCodeLineTest {

    @Test
    public void test() throws Exception{
        String path = "C:\\project\\data-handler-service";
        File file = new File(path);
        int totalCount = codeLine(file);
        System.out.println("totalCount = " + totalCount);
    }

    public int codeLine(File file) throws Exception {
        int count = 0;
        File[] files = file.listFiles();
        for (File temp : files) {
            if (temp.isDirectory()) {
                count += codeLine(temp);
            }else if (temp.isFile()){
                if (temp.getName().contains(".java")) {
                    BufferedReader reader = new BufferedReader(new FileReader(temp));
                    String len ;
                    while ((len=reader.readLine())!=null){
                        count++;
                    }
                    reader.close();
                }
            }
        }
        return count;
    }

}
