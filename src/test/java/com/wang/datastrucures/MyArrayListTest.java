package com.wang.datastrucures;

import com.wang.datastructures.MyArrayList;
import com.wang.entity.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyArrayListTest {
    private MyArrayList<Person> myArrayList;

    @Before
    public void init() {
        myArrayList = new MyArrayList<>(2);
    }

    @Test
    public void addTest() {
        Person person1 = new Person("张三", "男", 23);
        Person person2 = new Person("李四", "男", 24);
        Person person3 = new Person("王五", "男", 25);
        Person person4 = new Person("赵六", "男", 26);
        Person person5 = new Person("赵六", "男", 26);
        Person person6 = new Person("赵六", "男", 26);
        Person person7 = new Person("赵六", "男", 26);
        Person person8 = new Person("赵六", "男", 26);
        Person person9 = new Person("赵六", "男", 26);
        Person person10 = new Person("赵六", "男", 26);
        Person person11 = new Person("赵六", "男", 26);
        Person person12 = new Person("赵六", "男", 26);
        Person person13 = new Person("赵六", "男", 26);
        myArrayList.add(person1);
        myArrayList.add(person2);
        myArrayList.add(person3);
        myArrayList.add(person4);
        myArrayList.add(person5);
        myArrayList.add(person6);
        myArrayList.add(person7);
        myArrayList.add(person8);
        myArrayList.add(person9);
        myArrayList.add(person10);
        myArrayList.add(person11);
        myArrayList.add(person12);
        myArrayList.add(person13);

        for (Person person : myArrayList) {
            System.out.println("person = " + person);
        }
        Iterator<Person> iterator = myArrayList.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            count++;
            Person person = iterator.next();
            if (count % 2 == 0) {
                iterator.remove();
                System.out.println("person = " + person);
            }
        }

    }

}
