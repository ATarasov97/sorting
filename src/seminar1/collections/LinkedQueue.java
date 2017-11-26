package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        size++;
        if (size == 1) {
            head = new Node<Item>(item, null);
            tail = new Node<Item>(item, null);
        } else {
            if (size == 2) {
                head.next = tail;
                tail.item = item;
            } else {
                tail.next = new Node<Item>(item, null);
                tail = tail.next;
            }
        }
    }

    @Override
    public Item dequeue() {
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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {

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

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
