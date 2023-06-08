package com.wang.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedStack<T> extends MyLinkedList<T> {


    public MyLinkedStack() {

    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) tail.data;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(size - 1);
    }

    public boolean push(T data) {
        addTail(data);
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedStackIterator();
    }

    private class MyLinkedStackIterator implements Iterator<T> {

        private Node<T> current = tail;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.prev;
            return data;
        }

        @Override
        public void remove() {
            pop();
        }
    }


}
