package com.wang.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayStack<T> extends MyArrayList<T>  {




    public MyArrayStack() {

    }

    public MyArrayStack(int initSize) {
        ensureCapacity(initSize);
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) this.elementData[size - 1];
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Object data = elementData[--size];
        return (T) data;
    }

    public boolean push(T data) {
        int length = elementData.length;
        if (size == length) {
            length = length + (length >> 1);
            ensureCapacity(length);
        }
        elementData[size++] = data;
        return true;
    }



    @Override
    public Iterator<T> iterator() {
        return new MyArrayStackIterator();
    }

    private class MyArrayStackIterator implements Iterator<T> {
        private int currentIndex = size;

        @Override
        public boolean hasNext() {
            return currentIndex > 0;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) elementData[--currentIndex];
        }

        @Override
        public void remove() {
            MyArrayStack.this.pop();
        }
    }
}
