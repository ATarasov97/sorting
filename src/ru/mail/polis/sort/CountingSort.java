package ru.mail.polis.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;

/**
 * Created by alexandr on 25.11.17.
 */
public class CountingSort<T extends IntKeyObject> {

    public void sort (T[] a) {
        int max = findMax(a);
        int[] count = new int[max + 1];
        for (T x : a) count[x.getKey()]++;
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        T[] res = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
        for (int i = a.length - 1; i >= 0; i--) {
            res[--count[a[i].getKey()]] = a[i];
        }
        System.arraycopy(res, 0, a, 0, a.length);
    }

    private int findMax(T[] a) {
        int max = 0;
        for (T x : a) {
            max = Math.max(max, x.getKey());
        }
        return max;
    }


    public static void main(String[] args) {
        IntKeyStringValueObject[] a = new IntKeyStringValueObject[100];
        CountingSort<IntKeyStringValueObject> q = new CountingSort<>();
        for (int i = 0; i < 100; i++) {
            a[i] = new IntKeyStringValueObject(100 - i, "a");
        }
        q.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
