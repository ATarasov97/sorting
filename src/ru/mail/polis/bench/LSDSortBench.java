package ru.mail.polis.bench;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;


@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class LSDSortBench {

    private final int N = 1000;
    private SimpleInteger[] a1, a3, a4, a5, a6;
    private SimpleString[] a2, a7;

    private LSDSort<SimpleInteger> sortInt = new LSDSort<>();
    private LSDSort<SimpleString> sortString = new LSDSort<>();

    private SimpleInteger[] fix(Integer[] a) {
        SimpleInteger[] res = new SimpleInteger[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = new SimpleInteger(a[i]);
        }
        return res;
    }

    private SimpleString[] fix(String[] a) {
        SimpleString[] res = new SimpleString[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = new SimpleString(a[i]);
        }
        return res;
    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        a1 = fix(SortUtils.generateNarrowRangeArray(N));
        a2 = fix(SortUtils.generateLongStringArray(N));
        a3 = fix(SortUtils.generateReverseHeap(N));
        a4 = fix(SortUtils.generateSortedArray(N));
        a5 = fix(SortUtils.generateReversedSortedArray(N));
        a6 = fix(SortUtils.generateIntArray(N));
        a7 = fix(SortUtils.generateStringArray(N));
    }

    @Benchmark
    public void measureNarrowRange(Blackhole bh) {
        sortInt.sort(a1);
        bh.consume(a1);
    }

    @Benchmark
    public void measureLongString(Blackhole bh) {
        sortString.sort(a2);
        bh.consume(a2);
    }

    @Benchmark
    public void measureRevHeap(Blackhole bh) {
        sortInt.sort(a3);
        bh.consume(a3);
    }

    @Benchmark
    public void measureSorted(Blackhole bh) {
        sortInt.sort(a4);
        bh.consume(a4);
    }

    @Benchmark
    public void measureReverseSorted(Blackhole bh) {
        sortInt.sort(a5);
        bh.consume(a5);
    }

    @Benchmark
    public void measureRandInt(Blackhole bh) {
        sortInt.sort(a6);
        bh.consume(a6);
    }

    @Benchmark
    public void measureRandString(Blackhole bh) {
        sortString.sort(a7);
        bh.consume(a7);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LSDSortBench.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}