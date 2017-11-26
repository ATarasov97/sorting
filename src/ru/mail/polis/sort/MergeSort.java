package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by alexandr on 20.11.17.
 */
public class MergeSort <T extends Comparable<T>> extends AbstractSortOnComparisons<T>{

    @Override
    public void sort(T[] array) {
        T[] t = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        sort(array, t, 0, array.length - 1);
    }

   public void sort (T[] a, T[] t, int left, int right) {
       if (right <= left) return;
       int mid = (left + right) / 2;
       sort(a, t, left, mid);
       sort(a, t, mid + 1, right);
       merge(a, t, left, mid, right);
   }

   void merge(T[] a, T[] t, int left, int mid, int right) {
       for (int i = left; i <= right; i++) {
           t[i] = a[i];
       }
       int i = left, j = mid + 1;
       for (int k = left; k <= right; k++) {
           if (i > mid) a[k] = t[i++];
           else if (j > right) a[k] = t[i++];
           else if (lesser(t[j], t[i])) a[k] = t[j++];
           else a[k] = t[i++];
       }
   }

    public static void main(String[] args) {
        Integer[] a = new Integer[100];
        MergeSort<Integer> q = new MergeSort<>();
        for (int i = 0; i < 100; i++) {
            a[i] = 100 - i;
        }
        q.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
