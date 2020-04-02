package com.haoyanbing.datastructure.list;

class MyLinkedList {
    Node head, tail;
    int count;

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }


    public MyLinkedList() {
        this.count = 0;
    }

    public int get(int index) {
        if (index >= 0 && index < count) {
            // 找到index节点
            Node curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.value;
        }
        return -1;
    }

    public void addAtHead(int val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
        } else {
            Node curr = new Node(val);
            curr.next = head;
            head = curr;
        }
        count++;
    }

    public void addAtTail(int val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
        } else {
            Node node = new Node(val);
            tail.next = node;
            tail = node;
        }
        count++;
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
        } else if (index == count) {
            addAtTail(val);
        } else if (index < count) {
            // 找到index的前一个节点
            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            Node newNode = new Node(val);
            newNode.next = curr.next;
            curr.next = newNode;
            count++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index >= 0 && index < count) {
            if (index == 0) {
                head = head.next;
            } else {
                // 找到index的前一个节点
                Node curr = head;
                for (int i = 0; i < index - 1; i++) {
                    curr = curr.next;
                }
                if (curr.next == tail) {
                    tail = curr;
                    curr.next = null;
                } else {
                    curr.next = curr.next.next;
                }
            }
            count--;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        System.out.println(list.get(1));
        list.deleteAtIndex(1);
        System.out.println(list.get(1));

    }

}