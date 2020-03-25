package com.haoyanbing.datastructure.queue;

/**
 * 循环队列
 * @author haoyanbing
 * @since 2020/3/25
 */
public class CircularQueue {

    private int capacity;

    private int count;

    private int[] data;

    private int head;

    private int tail;

    public CircularQueue(int k) {
        this.capacity = k;
        this.count = 0;
        this.data = new int[k];
        this.head = 0;
        this.tail = 0;
    }

    public boolean enQueue(int value) {
        if (!isFull()) {
            data[tail] = value;
            count++;
            tail++;
            if (tail >= capacity) {
                tail = 0;
            }
            return true;
        }
        return false;
    }

    public boolean deQueue() {
        if (!isEmpty()) {
            head++;
            if (head >= capacity) {
                head = 0;
            }
            count--;
            return true;
        }
        return false;
    }

    public int front() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    public int rear() {
        if (isEmpty()) {
            return -1;
        }
        if (tail == 0) {
            return data[capacity - 1];
        } else {
            return data[tail - 1];
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }
}
