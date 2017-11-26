package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void pushFront(Item item) {
        size++;
        if (size == 1) {
            head = new Node<Item>(item, null, null);
            tail = head;
        } else {
            if (size == 2) {
                head.next = new Node<Item>(item, head, null);
                tail = head.next;
            } else {
                tail.next = new Node<Item>(item, tail, null);
                tail = tail.next;
            }
        }
    }

    @Override
    public void pushBack(Item item) {
        size++;
        if (size == 1) {
            head = new Node<Item>(item, null, null);
            tail = head;
        } else {
            if (size == 2) {
                tail.previous = new Node<Item>(item, null, tail);
                head = tail.previous;
            } else {
                head.previous = new Node<Item>(item, null, head);
                head = head.previous;
            }
        }
    }

    @Override
    public Item popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        Item tmp = tail.item;
        tail = tail.previous;
        return tmp;
    }

    @Override
    public Item popBack() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        Item tmp = head.item;
        head = head.next;
        return tmp;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Item> iterator() {
        return new LinkedDequeIterator();
    }

    private class LinkedDequeIterator implements Iterator<Item> {

        private Node<Item> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode.next != null;
        }

        @Override
        public Item next() {
            if (hasNext()) {
                Item result = currentNode.item;
                currentNode = currentNode.next;
                return result;
            } else {
                throw new NoSuchElementException();
            }
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> previous, Node<Item> next) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }
}
