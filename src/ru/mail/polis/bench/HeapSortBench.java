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

import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.SortUtils;



@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class HeapSortBench {

    private final int N = 1000;
    private Integer[] a1, a3, a4, a5, a6;
    private String[] a2, a7;

    private HeapSort<Integer> sortInt = new HeapSort<>();
    private HeapSort<String> sortString = new HeapSort<>();

    @Setup(value = Level.Trial)
    public void setUpInvocation() {
        a1 = SortUtils.generateNarrowRangeArray(N);
        a2 = SortUtils.generateLongStringArray(N);
        a3 = SortUtils.generateReverseHeap(N);
        a4 = SortUtils.generateSortedArray(N);
        a5 = SortUtils.generateReversedSortedArray(N);
        a6 = SortUtils.generateIntArray(N);
        a7 = SortUtils.generateStringArray(N);
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
                .include(HeapSortBench.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}