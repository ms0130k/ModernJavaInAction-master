package modernjavainaction.chap07;

import static modernjavainaction.chap07.ParallelStreamsHarness.FORK_JOIN_POOL;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
//    private final long THRESHOLD = 30;
    private final long THRESHOLD = 1_000_000;
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
//        System.out.println(Thread.currentThread().getName());
        int length = end - start;
        if (THRESHOLD >= length) {
            return computeSequentially();
        }
        int mid = (start + end) / 2;
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, mid);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, mid, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return rightResult + leftResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static Long forkJoinSum(Long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long[] numbers = LongStream.rangeClosed(1, 10_000_000L).toArray();
        ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
        Long result = pool.invoke(task);
        System.out.println(result);
    }
}
