package ru.mail.polis.sort;

import java.lang.reflect.Array;

import ru.mail.polis.structures.Numerical;

/**
 * Created by alexandr on 26.11.17.
 */
public class LSDSort<T extends Numerical> extends AbstractSortOnComparisons<T> {

    @Override
    public void sort(T[] array) {
        final int r = array[0].getDigitMaxValue() + 1;
        int d = findMaxDigit(array);
        for (int k = 0; k < d; k++) {
            int[] count = new int[r];
            for (T x : array) count[x.getDigit(k)]++;
            for (int i = 1; i < r; i++) {
                count[i] += count[i - 1];
            }
            T[] res = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
            for (int i = array.length - 1; i >= 0; i--) {
                res[--count[array[i].getDigit(k)]] = array[i];
            }
            System.arraycopy(res, 0, array, 0, array.length);
        }
    }

    private int findMaxDigit(T[] a) {
        int max = 0;
        for (T x : a) {
            max = Math.max(max, x.getDigitCount());
        }
        return max;
    }
}
