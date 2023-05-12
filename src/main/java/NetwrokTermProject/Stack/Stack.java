package NetwrokTermProject.Stack;

import NetwrokTermProject.LinkedList.LinkedList;

import java.util.NoSuchElementException;

public class Stack<T> {
    private LinkedList<T> items;

    public Stack() {
        items = new LinkedList<>();
    }

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        if (items.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return items.removeLast();
    }

    public T peek() {
        if (items.isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return items.get(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }
}