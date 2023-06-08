package com.wang.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyArrayList<T> implements Iterable<T>{
    /**
     * 存放元素数组
     */
    protected Object[] elementData;
    /**
     * 容器当前元素个数
     */
    protected int size;
    /**
     * 容器默认大小
     */
    protected static final int DEFAULT_CAPACITY = 10;


    public MyArrayList() {
        this.size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public MyArrayList(int initCapacity) {
        this.size = 0;
        ensureCapacity(initCapacity);
    }

    /**
     * 清除容器并设置容器为默认大小
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /**
     * 修改容器大小
     *
     * @param newCapacity 容器新大小
     */
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        newCapacity = newCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : newCapacity;

        Object[] oldElementData = elementData;

        elementData = new Object[newCapacity];

        for (int i = 0; i < this.size; i++) {
            elementData[i] = oldElementData[i];
        }
    }

    /**
     * 返回容器元素数量
     *
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * 容器是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 获取索引元素值
     *
     * @param index 目标索引
     * @return
     */
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    /**
     * 添加元素 追加到末尾
     *
     * @param data
     * @return
     */
    public boolean add(T data) {
        return this.add(this.size, data);
    }

    /**
     * 添加元素至指定索引位
     *
     * @param index 指定索引
     * @param data  添加元素
     * @return
     */
    public boolean add(int index, T data) {
        checkIndex(index);
        int length = elementData.length;

        if (this.size == length) {
            int newCapacity = length + (length >> 1);
            ensureCapacity(newCapacity);
        }
        for (int i = this.size - 1; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        size++;
        elementData[index] = data;
        return true;
    }

    /**
     * 修改目标索引元素并返回原有该索引元素
     *
     * @param index 目标索引
     * @param data  修改后元素值
     * @return
     */
    public T set(int index, T data) {
        checkIndex(index);
        T oldData = (T) this.elementData[index];
        this.elementData[index] = data;
        return oldData;
    }

    /**
     * 删除目标索引的元素
     *
     * @param index 目标索引
     * @return 原索引位置元素
     */
    public T remove(int index) {
        checkIndex(index);
        T removeData = (T) elementData[index];

        for (int i = index; i < size - 1; i++) {
            this.elementData[i] = this.elementData[i + 1];
        }
        this.elementData[size - 1] = null;
        size--;
        return removeData;
    }

    /**
     * 删除第一个位置出现的元素
     *
     * @param data 目标元素
     * @return
     */
    public boolean remove(T data) {
        if (Objects.isNull(data)) {
            for (int i = 0; i < this.size; i++) {
                if (Objects.isNull(this.elementData[i])) {
                    this.remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (Objects.equals(this.elementData[i], data)) {
                    this.remove(i);
                    return true;
                }
            }
        }
        return false;
    }


    private void checkIndex(int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) elementData[currentIndex++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--currentIndex);
        }
    }
}
