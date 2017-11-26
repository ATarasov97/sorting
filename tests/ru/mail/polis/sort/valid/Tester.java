package ru.mail.polis.sort.valid;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.mail.polis.sort.BubbleSort;
import ru.mail.polis.sort.SortUtils;

@RunWith(value = Parameterized.class)
public class Tester {

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(final Description description) {
            System.err.println("=== Running " + description.getMethodName());
        }
    };

    @Parameterized.Parameter
    public int[] array;

    @Parameterized.Parameters(name = "{index}")
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][]{
            {0},
            {0, 0, 0, 0},
            {4, 3, 2, 1},
            {0, 1, 1, 0},
            {1},
            {Integer.MAX_VALUE, 0, 0, Integer.MIN_VALUE},
                Arrays.stream(SortUtils.generateIntArray(1)).mapToInt(Integer::intValue).toArray(),
                Arrays.stream(SortUtils.generateIntArray(10)).mapToInt(Integer::intValue).toArray(),
                Arrays.stream(SortUtils.generateIntArray(100)).mapToInt(Integer::intValue).toArray(),
                Arrays.stream(SortUtils.generateIntArray(1000)).mapToInt(Integer::intValue).toArray(),
                Arrays.stream(SortUtils.generateIntArray(10000)).mapToInt(Integer::intValue).toArray(),
        });
    }



    @Test
    public void test01_checkBubbleSort() throws IOException {
        Assert.assertTrue(SortUtils.isArraySorted(BubbleSort.sort(array)));
    }

}
