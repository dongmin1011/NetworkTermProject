package NetwrokTermProject.LinkedList;

import java.util.NoSuchElementException;

class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

}
// 노드 클래스
public class LinkedList<T> {



    private int size;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }

            current.setNext(current.getNext().getNext());

            if (current.getNext() == null) {
                tail = current;
            }
        }

        size--;
    }
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("LinkedList is empty");
        }

        Node<T> removedNode;

        if (size == 1) {
            removedNode = head;
            head = null;
            tail = null;
        } else {
            Node<T> currentNode = head;
            while (currentNode.getNext() != tail) {
                currentNode = currentNode.getNext();
            }
            removedNode = tail;
            tail = currentNode;
            tail.setNext(null);
        }

        size--;
        return removedNode.getData();
    }

    public void update(int index, T newData) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }

        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        currentNode.setData(newData);
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


}

