package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;
    private Item[] elementData;
    private int size;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public CyclicArrayDeque() {
        elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void pushFront(Item item) {
        size++;
        tail++;
        if (size == 1) {
            head = 0;
            tail = 0;
        }
        if (size == elementData.length) {
            grow();
        }
        if (tail == elementData.length) {
            tail = 0;
        }
        elementData[tail] = item;
    }

    @Override
    public void pushBack(Item item) {
        size++;
        head--;
        if (size == 1) {
            head = 0;
            tail = 0;
        }
        if (size == elementData.length) {
            grow();
        }
        if (head == -1) {
            head = elementData.length - 1;
        }
        elementData[head] = item;
    }

    @Override
    public Item popFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item tmp = elementData[tail];
        size--;
        tail--;
        if ((size * 4 <= elementData.length) && (size != 0)){
            shrink();
        }
        if (tail == -1) {
            tail = elementData.length - 1;
        }
        return tmp;
    }

    @Override
    public Item popBack() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item tmp = elementData[head];
        size--;
        head++;
        if ((size * 4 <= elementData.length) && (size != 0)){
            shrink();
        }
        if (head == elementData.length) {
            head = 0;
        }
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

    private void grow() {
        if (head <= tail) {
            elementData = Arrays.copyOf(elementData, elementData.length * 3 / 2);
        } else {
            Item[] tmp = (Item[]) new Object[elementData.length * 3 / 2];
            System.arraycopy(Arrays.copyOfRange(elementData, head, elementData.length),
                    0, tmp, 0, elementData.length - head);
            System.arraycopy(Arrays.copyOfRange(elementData, 0, tail + 1),
                    0, tmp, elementData.length - head, tail + 1);
            elementData = tmp;
            head = 0;
            tail = size - 1;
        }
    }

    private void shrink() {
        if (head <= tail) {
            elementData = Arrays.copyOf(Arrays.copyOfRange(elementData, head, tail + 1), elementData.length / 2);
        } else {
            Item[] tmp = (Item[]) new Object[elementData.length / 2];
            System.arraycopy(Arrays.copyOfRange(elementData, head, elementData.length - 1),
                    0, tmp, 0, elementData.length - head);
            System.arraycopy(Arrays.copyOfRange(elementData, 0, tail + 1),
                    0, tmp, elementData.length - head, tail + 1);
            elementData = tmp;
        }
        head = 0;
        tail = size - 1;
    }

    @Override
    public Iterator<Item> iterator() {

        return new CyclicArrayDequeIterator();
    }

    private class CyclicArrayDequeIterator implements Iterator<Item> {

        int index = 0;

        @Override
        public boolean hasNext() {
            int result = head + index;
            if (result >= elementData.length) {
                result -= elementData.length;
            }
            return (result <= tail);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int result = head + index;
            if (result >= elementData.length) {
                result -= elementData.length;
            }
            Item tmp = elementData[result];
            index++;
            return tmp;
        }
    }
}
