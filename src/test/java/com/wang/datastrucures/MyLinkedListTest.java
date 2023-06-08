package com.wang.datastrucures;

import com.wang.datastructures.MyLinkedList;
import com.wang.entity.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyLinkedListTest {

    private MyLinkedList<Person> myLinkedList;

    @Before
    public void init() {
        myLinkedList = new MyLinkedList<>();
    }

    @Test
    public void addTest() {
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
        myLinkedList.addTail(person1);
        myLinkedList.addTail(person2);
        myLinkedList.addTail(person3);
        myLinkedList.addTail(person4);
        myLinkedList.addTail(person5);
        myLinkedList.addTail(person6);
        myLinkedList.addTail(person7);
        myLinkedList.addTail(person8);
        myLinkedList.addHead(person9);
        myLinkedList.addHead(person10);
        myLinkedList.addHead(person11);
        myLinkedList.addHead(person12);
        myLinkedList.addHead(person13);
        Person head = myLinkedList.getHead();
        System.out.println("head = " + head);
        Person tail = myLinkedList.getTail();
        System.out.println("tail = " + tail);
        int size = myLinkedList.size();
        System.out.println("size = " +size );
        myLinkedList.remove(person1);

        for (Person person : myLinkedList) {
            System.out.println("person = " + person);
        }
        System.out.println("====================================================");
        Iterator<Person> iterator = myLinkedList.iterator();

        int count = 0;
        while (iterator.hasNext()) {
            count++;
            Person person = iterator.next();
            if (count % 2 == 0) {
                iterator.remove();
                System.out.println("person =  " + person);
            }

        }
        System.out.println("====================================================");

        for (Person person : myLinkedList) {
            System.out.println("person = " + person);
        }

    }
}
