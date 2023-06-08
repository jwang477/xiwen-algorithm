package com.wang.datastrucures;

import com.wang.datastructures.MyLinkedStack;
import com.wang.entity.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyLinkedStackTest {
    private MyLinkedStack<Person> myLinkedStack;

    @Before
    public void init() {
        myLinkedStack = new MyLinkedStack<>();
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
        myLinkedStack.push(person1);
        myLinkedStack.push(person2);
        myLinkedStack.push(person3);
        myLinkedStack.push(person4);
        myLinkedStack.push(person5);
        myLinkedStack.push(person6);
        myLinkedStack.push(person7);
        myLinkedStack.push(person8);
        myLinkedStack.push(person9);
        myLinkedStack.push(person10);
        myLinkedStack.push(person11);
        myLinkedStack.push(person12);
        myLinkedStack.push(person13);

        for (Person person : myLinkedStack) {
            System.out.println("person = " + person);
        }
        System.out.println(" ========================================================================");
        Iterator<Person> iterator = myLinkedStack.iterator();

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
