package com.wang.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<T> implements Iterable<T> {

    protected int size = 0;

    protected Node<T> head;

    protected Node<T> tail;

    public MyLinkedList() {

        size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        if (size == 0) {
            return;
        }
        Node<T> temp = head;
        while (temp != null) {
            Node<T> next = temp.next;
            temp.next = null;
            temp.prev = null;
            temp.data = null;
            temp = next;
        }
        head = tail = null;
        size = 0;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public T getHead() {
        if (Objects.isNull(head)) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    public T getTail() {
        if (Objects.isNull(tail)) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }

    public boolean add(T data) {
        addTail(data);
        return true;
    }

    public boolean add(int index, T data) {
        addNodeBefore(getNode(index), data);
        return true;
    }

    public T set(int index, T data) {
        Node<T> node = getNode(index);
        T oldData = node.data;
        node.data = data;
        return oldData;
    }

    public T remove(int index) {
        Node<T> node = getNode(index);
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        T removeData = node.data;
        node.data = null;
        size--;
        return removeData;
    }

    public boolean remove(T data) {

        Node<T> temp = head;
        if (data == null) {
            for (int i = 0; temp != null; i++) {
                if (temp.data == null) {
                    remove(i);
                    return true;
                }
                temp = temp.next;
            }
        } else {
            for (int i = 0; temp != null; i++) {
                if (Objects.equals(data, temp.data)) {
                    remove(i);
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    private void addNodeBefore(Node<T> node, T data) {
        Node<T> prev = node.prev;
        Node<T> newNode = new Node<>(data, prev, node);
        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

    public void addTail(T data) {
        Node<T> node = new Node<>(data, tail, null);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public boolean contains(T data) {
        if (isEmpty()) {
            return false;
        }
        Node<T> temp = head;
        while (temp != null) {
            if ((data == null && temp.data == null) || (data != null && Objects.equals(data, temp.data))) {
                return true;
            }
            temp = temp.next;
        }

        return false;

    }

    public void addHead(T data) {
        Node<T> node = new Node<>(data, null, head);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            head = node;
        }
        size++;
    }

    private Node<T> getNode(int index) {
        checkIndex(index);
        Node<T> temp;

        if (index < (size >> 1)) {
            temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    protected static class Node<T> {
        public Node<T> prev;

        public Node<T> next;

        public T data;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {

        private Node<T> current = head;
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            currentIndex++;
            return data;
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(--currentIndex);
        }
    }
}
