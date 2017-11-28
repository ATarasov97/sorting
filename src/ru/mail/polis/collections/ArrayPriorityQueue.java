package ru.mail.polis.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private static final int DEFAULT_CAPACITY = 10;

    private Key[] elementData;

    private int tail = -1;
    private Comparator<Key> comparator;
    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue() {
        elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    }

    public Key[] getElementData() {
        return elementData;
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Comparator<Key> comparator) {
        elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    @Override
    public void add(Key key) {
        tail++;
        if (tail == elementData.length) {
            grow();
        }
        elementData[tail] = key;
        siftUp();
    }

    @Override
    public Key peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elementData[0];
    }

    @Override
    public Key extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Key tmp = elementData[0];
        elementData[0] = elementData[tail];
        tail--;
        if (((tail + 1) * 4) < elementData.length) {
            shrink();
        }
        siftDown();
        return tmp;
    }

    @Override
    public boolean isEmpty() {
        return  (tail == -1);
    }

    @Override
    public int size() {
        return (tail + 1);
    }

    private void siftUp() {
        int i = tail;
        while (greater((i - 1) / 2, i) && (i > 0)) {
            Key tmp = elementData[i];
            elementData[i] = elementData[(i - 1) / 2];
            i = (i - 1) / 2;
            elementData[i] = tmp;
        }
    }

    private void siftDown() {
        int i = 0;
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int min = i;
            if (left <= tail) {
                if (greater(min, left)) {
                    min = left;
                }
            }
            if (right <= tail) {
                if (greater(min, right)) {
                    min = right;
                }
            }
            if (min == i) {
                break;
            }
            Key tmp = elementData[i];
            elementData[i] = elementData[min];
            i = min;
            elementData[i] = tmp;
        }
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, elementData.length * 3 / 2);
    }

    private void shrink() {
        elementData = Arrays.copyOf(elementData, elementData.length / 2);
    }

    private boolean greater(int i, int j) {
        return comparator == null
                ? elementData[i].compareTo(elementData[j]) > 0
                : comparator.compare(elementData[i], elementData[j]) > 0
                ;
    }

  @Override
    public Iterator<Key> iterator() {

        return new ArrayPriorityQueueIterator();
    }

    private class ArrayPriorityQueueIterator implements Iterator<Key> {

        int index = 0;

        @Override
        public boolean hasNext() {
            return (index < tail);
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elementData[index++];
        }
    }
}
