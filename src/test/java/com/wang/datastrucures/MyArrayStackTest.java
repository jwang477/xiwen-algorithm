package com.wang.datastrucures;

import com.wang.datastructures.MyArrayStack;
import com.wang.entity.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyArrayStackTest {
    private MyArrayStack<Person> myArrayStack;

    @Before
    public void init() {
        myArrayStack = new MyArrayStack<>(2);
    }

    @Test
    public void pushTest() {
        Person person1 = new Person("张三", "男", 1);
        Person person2 = new Person("李四", "男", 2);
        Person person3 = new Person("王五", "男", 3);
        Person person4 = new Person("赵六", "男", 4);
        Person person5 = new Person("赵六", "男", 5);
        Person person6 = new Person("赵六", "男", 6);
        Person person7 = new Person("赵六", "男", 7);
        Person person8 = new Person("赵六", "男", 8);
        Person person9 = new Person("赵六", "男", 9);
        Person person10 = new Person("赵六", "男", 10);
        Person person11 = new Person("赵六", "男", 11);
        Person person12 = new Person("赵六", "男", 12);
        Person person13 = new Person("赵六", "男", 13);
        myArrayStack.push(person1);
        myArrayStack.push(person2);
        myArrayStack.push(person3);
        myArrayStack.push(person4);
        myArrayStack.push(person5);
        myArrayStack.push(person6);
        myArrayStack.push(person7);
        myArrayStack.push(person8);
        myArrayStack.push(person9);
        myArrayStack.push(person10);
        myArrayStack.push(person11);
        myArrayStack.push(person12);
        myArrayStack.push(person13);

        for (Person person : myArrayStack) {
            System.out.println("person = " + person);
        }
        System.out.println(" ========================================================================");
        Iterator<Person> iterator = myArrayStack.iterator();

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
