package seminar1.iterators;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import seminar1.collections.ArrayPriorityQueue;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(n + k * log n),
 *  n — количество итераторов
 *  k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    private Comparator<PeekingIncreasingIterator> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());
    private ArrayPriorityQueue<PeekingIncreasingIterator> iterators;

    public MergingPeekingIncreasingIterator(IPeekingIterator... peekingIterator) {
        iterators = new ArrayPriorityQueue<>(comparator);
        for (int i = 0; i < peekingIterator.length; i++) {
            iterators.add((PeekingIncreasingIterator) peekingIterator[i]);
        }
    }

    @Override
    public boolean hasNext() {
        for (PeekingIncreasingIterator iterator : iterators) {
            if (iterator.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next(){
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        return  iterators.peek().next();
    }

}
