package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleInteger implements Numerical<SimpleInteger> {

    private static final int DIGIT_COUNT = 10;

    private final Integer data;
    private final int length;

    public SimpleInteger(Integer data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Source must be not null");
        }
        if (data < 0) {
            throw new IllegalArgumentException("Source must be positive");
        }
        this.data = data;
        int i = 0;
        while ((data / (int)Math.pow(DIGIT_COUNT, i)) != 0) {
            i++;
        }
        this.length = ++i;
    }

    @Override
    public int getDigit(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index " + index);
        } else if (index >= getDigitCount()) {
            return 0;
        } else {
            return (data / (int)Math.pow(DIGIT_COUNT, index)) % DIGIT_COUNT;
        }
    }

    @Override
    public int getDigitMaxValue() {
        return DIGIT_COUNT - 1;
    }

    @Override
    public int getDigitCount() {
        return length;
    }

    @Override
    public int compareTo(SimpleInteger anotherSimpleInteger) {
        return this.data.compareTo(anotherSimpleInteger.data);
    }

     public String toString() {
        return String.valueOf(data);
    }
}
