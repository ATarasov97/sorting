package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by alexandr on 19.11.17.
 */
public class QuickSort1<T extends Comparable<T>> extends AbstractSortOnComparisons{

    private Random random = new Random();

    public QuickSort1() {}

    public QuickSort1(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public T[] sort(T[] a, int left, int right) {
        if (left >= right) {
            return null;
        }
        if ((right - left + 1) > 10) {
            int idx = partition(a, left, right);
            sort(a, left, idx);
            sort(a, idx + 1, right);
        } else {
            return insSort(a, left, right);
        }
        return a;
    }

    private T[] insSort(T[] a, int left, int right) {
        int n = right - left + 1;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && lesser(a[j], a[j - 1]); j--) {
                swap(a, j, j - 1);
            }
        }
        return a;
    }

    private int partition(T[] a, int left, int right) {
        T p = a[left + random.nextInt(right - left + 1)];
        int i = left, j = right;
        while (i <= j) {
            while(lesser(a[i], p)) i++;
            while(greater(a[j], p)) j--;
            if (i <= j) swap(a, i++, j--);
        }
        return j;
    }

    public static void main(String[] args) {
        int[] a = new int[100];
        QuickSort1<Integer> q = new QuickSort1<>();
        for (int i = 0; i < 100; i++) {
            a[i] = 100 - i;
        }
        Integer[] s = q.sort(Arrays.stream(a).boxed().toArray( Integer[]::new ), 0, 99);
        System.out.println(Arrays.toString(s));
    }

}
