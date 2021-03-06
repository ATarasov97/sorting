package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Comparator;

import ru.mail.polis.collections.ArrayPriorityQueue;

/**
 * Created by alexandr on 25.11.17.
 */
public class HeapSort <T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    private ArrayPriorityQueue<T> heap;

    public HeapSort() {
        heap = new ArrayPriorityQueue<>();
    }

    public HeapSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
        heap = new ArrayPriorityQueue<T>((Comparator<T>) comparator);
    }

    @Override
    public void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            heap.add(a[i]);
        }
        for (int i = 0; i < a.length; i++) {
            a[i] = heap.extractMin();
        }
    }

    public static void main(String[] args) {
        int[] a = new int[100];
        HeapSort<Integer> q = new HeapSort<>();
        for (int i = 0; i < 100; i++) {
            a[i] = 100 - i;
        }
        q.sort(Arrays.stream(a).boxed().toArray(Integer[]::new));
        System.out.println(Arrays.toString(a));
    }

}
