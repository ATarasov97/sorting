package ru.mail.polis.sort;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import seminar1.collections.ArrayPriorityQueue;

public class SortUtils {

    private static final Random r = ThreadLocalRandom.current();

    public static void swap(Integer[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static void swap(int[] a, int i, int j) {
        int x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static Integer[] generateNarrowRangeArray(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(10);
        }
        return a;
    }

    public static String[] generateLongStringArray(int n) {
        String[] a = new String[n];
        for (int i = 0; i < a.length; i++) {
            char[] string = new char[1000];
            for (int j = 0; j < string.length; j++) {
                string[j] = (char) ('a' + r.nextInt('z' - 'a' + 1));
            }
            a[i] = new String (string);
        }
        return a;
    }

    public static Integer[] generateReverseHeap(int n) {
        Integer[] a = new Integer[n];
        ArrayPriorityQueue<Integer> heap = new ArrayPriorityQueue<>();
        Integer[] randArray = generateIntArray(a.length);
        for (int i = 0; i < a.length; i++) {
            heap.add(randArray[i]);
        }
        Integer[] tmp = new Integer[a.length];
        System.arraycopy(heap.getElementData(), 0, tmp, 0, a.length);
        for (int i = 0; i < a.length; i++) {
            a[i] = tmp[a.length - 1 - i];
        }
        return a;
    }

    public static Integer[] generateSortedArray(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        return a;
    }

    public static Integer[] generateReversedSortedArray(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = n - 1 - i;
        }
        return a;
    }

    public static Integer[] generateIntArray(int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        for (int i = a.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            SortUtils.swap(a, i, j);
        }
        return a;
    }

    public static String[] generateStringArray(int n) {
        String[] a = new String[n];
        for (int i = 0; i < a.length; i++) {
            char[] string = new char[1 + r.nextInt(99)];
            for (int j = 0; j < string.length; j++) {
                string[j] = (char) ('a' + r.nextInt('z' - 'a' + 1));
            }
            a[i] = new String (string);
        }
        return a;
    }

    public static boolean isArraySorted(int[] a) {
        boolean isSorted = true;
        for (int i = 0; i < a.length - 1 && isSorted; i++) {
            isSorted = a[i] <= a[i + 1];
        }
        return isSorted;
    }

    public static <T extends Comparable<? super T>> boolean isArraySorted(T[] array) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = array[i].compareTo(array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static <T> boolean isArraySorted(T[] array, Comparator<T> comparator) {
        boolean isSorted = true;
        for (int i = 0; i < array.length - 1 && isSorted; i++) {
            isSorted = comparator.compare(array[i], array[i + 1]) <= 0;
        }
        return isSorted;
    }

    public static void main(String[] args) {
        generateLongStringArray(1000);
    }
}
