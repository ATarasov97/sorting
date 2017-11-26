package ru.mail.polis.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by alexandr on 19.11.17.
 */
public class QuickSortInsert<T extends Comparable<T>> extends AbstractSortOnComparisons<T>{

    private Random random = new Random();

    public QuickSortInsert() {}

    public QuickSortInsert(Comparator<? super T> comparator) {
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
        if ((right - left + 1) > 10) {
            int idx = partition(a, left, right);
            sort(a, left, idx);
            sort(a, idx + 1, right);
        } else {
            insSort(a, left, right);
        }
    }

    private void insSort(T[] a, int left, int right) {
        int n = right - left + 1;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && lesser(a[j], a[j - 1]); j--) {
                swap(a, j, j - 1);
            }
        }
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
        Integer[] a = new Integer[100];
        QuickSortInsert<Integer> q = new QuickSortInsert<>();
        for (int i = 0; i < 100; i++) {
            a[i] = 100 - i;
        }
        q.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
