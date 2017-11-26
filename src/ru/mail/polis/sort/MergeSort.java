package ru.mail.polis.sort;

import java.util.Arrays;

/**
 * Created by alexandr on 20.11.17.
 */
public class MergeSort <T extends Comparable<T>> extends AbstractSortOnComparisons{

   public T[] sort (T[] a, T[] t, int left, int right) {
       if (right <= left) return a;
       int mid = (left + right) / 2;
       sort(a, t, left, mid);
       sort(a, t, mid + 1, right);
       merge(a, t, left, mid, right);
       return a;
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
        int[] a = new int[100];
        MergeSort<Integer> q = new MergeSort<>();
        for (int i = 0; i < 100; i++) {
            a[i] = 100 - i;
        }
        int[] t = new int[a.length];
        Integer[] s = q.sort(Arrays.stream(a).boxed().toArray(Integer[]::new),
                Arrays.stream(a).boxed().toArray(Integer[]::new), 0, 99);
        System.out.println(Arrays.toString(s));
    }

}
