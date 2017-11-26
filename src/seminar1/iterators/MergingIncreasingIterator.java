package seminar1.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    int firstLast;
    int secondLast;

    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
        firstLast = -1;
        secondLast = -1;
    }

    @Override
    public boolean hasNext() {
        return (first.hasNext() || second.hasNext() || (firstLast != -1) || (secondLast != -1));
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (first.hasNext() && (firstLast == -1)) {
            firstLast = first.next();
        }
        if (second.hasNext() && (secondLast == -1)) {
            secondLast = second.next();
        }
        int tmp;
        if (secondLast == -1) {
            tmp = firstLast;
            firstLast = -1;
            return tmp;
        }
        if (firstLast == -1) {
            tmp = secondLast;
            secondLast = -1;
            return tmp;
        }
        if (firstLast <= secondLast) {
            tmp = firstLast;
            firstLast = -1;
        } else {
            tmp = secondLast;
            secondLast = -1;
        }
        return tmp;
    }
}
