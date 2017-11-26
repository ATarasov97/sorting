package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

import ru.mail.polis.structures.IntKeyObject;
import seminar1.collections.ArrayPriorityQueue;

/**
 * Created by alexandr on 25.11.17.
 */
public class CountingSort<T extends IntKeyObject> {

    public T[] sort (T[] a) {
        int max = findMax(a);
        int[] count = new int[max + 1];
        for (T x : a) count[x.getKey()]++;
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        IntKeyObject[] res = (IntKeyObject[]) Array.newInstance(IntKeyObject.class, a.length);
        for (int i = a.length - 1; i >= 0; i--) {
            res[--count[a[i].getKey()]] = a[i];
        }
        return (T[]) res;
    }

    private int findMax(T[] a) {
        int max = 0;
        for (T x : a) {
            max = Math.max(max, x.getKey());
        }
        return max;
    }

    static class Obj implements IntKeyObject {

        int key;
        Integer value;

        Obj(int i, int j) {
            key = i;
            value = j;
        }

        @Override
        public int getKey() {
            return 0;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Obj[] a = new Obj[100];
        CountingSort<Obj> q = new CountingSort<>();
        for (int i = 0; i < 100; i++) {
            a[i] = new Obj(100 - i, 1);
        }
        Obj[] s = (Obj[])q.sort(a);
        for (int i = 0; i < s.length - 1; i++) {
            System.out.println(s[i].getKey());
        }
    }

}
