package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import javafx.util.Pair;

/**
 * Created by alexandr on 19.11.17.
 */
public class QuickSortThree<T extends Comparable<T>> extends AbstractSortOnComparisons {

    private Random random = new Random();

    public QuickSortThree() {
    }

    public QuickSortThree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public T[] sort(T[] a, int left, int right) {
        if (left >= right) {
            return null;
        }
        int idx = partition(a, left, right);
        sort(a, left, idx);
        sort(a, idx + 1, right);

        return a;
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
        int[] a = new int[100];
        QuickSortInsert<Integer> q = new QuickSortInsert<>();
        for (int i = 0; i < 100; i++) {
            a[i] = 100 - i;
        }
        Integer[] s = q.sort(Arrays.stream(a).boxed().toArray(Integer[]::new), 0, 99);
        System.out.println(Arrays.toString(s));
    }

}