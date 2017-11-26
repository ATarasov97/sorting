package seminar1.collections;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    @Override
    public void push(Item item) {
        size++;
        Node<Item> tmp = new Node<>(item, head);
        head = tmp;
    }

    @Override
    public Item pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        Node<Item> tmp = head;
        head = head.next;
        return tmp.item;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {

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

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
