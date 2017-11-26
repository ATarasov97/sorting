package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by alexandr on 19.11.17.
 */
public class QuickSortThree<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

    private Random random = new Random();

    public QuickSortThree() {
    }

    public QuickSortThree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    public void sort(T[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int idx = partition(a, left, right);
        sort(a, left, idx);
        sort(a, idx + 1, right);
    }


    private int partition(T[] a, int left, int right) {
        T p = a[left + random.nextInt(right - left + 1)];
        int i = left, lt = left, gt = right;
        while (i <= gt) {
            if (lesser(a[i], a[lt])) {
                swap(a, i++, lt++);
            } else {
                if (greater(a[i], a[lt])) {
                    swap(a, gt--, i);
                } else {
                    i++;
                }
            }
        }
        return lt;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[100];
        QuickSortThree<Integer> q = new QuickSortThree<>();
        for (int i = 0; i < 100; i++) {
            a[i] = 100 - i;
        }
        q.sort(a);
        System.out.println(Arrays.toString(a));
    }

}