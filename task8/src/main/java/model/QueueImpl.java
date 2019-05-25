package model;

import java.util.function.Predicate;

public class QueueImpl<T> implements IQueue<T> {

    private int size;
    private Node first, last;


    public class Node {
        T item;
        Node next;
    }

    public QueueImpl() {
        first = null;
        last = null;
        this.size = 0;
    }

    private boolean isEmpty() {
        return (size == 0);
    }

    public T peek() { //посмотреть, но НЕ удалять, начало очереди. Если очередь пустая должен возвращаться null;
        if (isEmpty()) return null;
        return first.item;
    }


    public T pop() { //посмотреть и удалить начало очереди. Если очередь пустая должен возвращаться null;
        if (isEmpty()) return null;
        T item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        return item;
    }

    public void add(T elem) {
        Node oldLast = last;
        last = new Node();
        last.item = elem;
        last.next = null;
        if (isEmpty()) first = last;
        else
            oldLast.next = last;
        size++;
    }

    public int findIndex(Predicate<T> x, Node node, int index) {
        if (node == null)
            return -1;
        if (x.test(node.item))
            return index;

        return findIndex(x, node.next, ++index);
    }

    public int findWithLoop(Predicate<T> x) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if (x.test(node.item)) {
                return i;
            } else
                node = node.next;
        }
        return -1;
    }

    public int indexOf(Predicate<T> x) {

        return findIndex(x, first, 0);
    }

    public void addAll(QueueImpl<T> other) {
        while (!other.isEmpty()) {
            add(other.pop());
        }
    }

    public void showAll() {
        while (!isEmpty()) {
            String str = (String) pop();
            System.out.print(str);
            System.out.print(" ");
        }
    }

}
