package modernjavainaction.chap07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class MyForkJoinSumCalculator extends RecursiveTask<Long> {
    public static final long THRESHOLD = 10_000;
    private final long[] numbers;
    private final int start;
    private final int end;
//    private int sum = 0;

    public MyForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private MyForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        System.out.println(Thread.currentThread().getName());
        int length = end - start;
        if (THRESHOLD >= length) {
            return computeSequentially();
        }
        int mid = start + length / 2;
        MyForkJoinSumCalculator leftTask = new MyForkJoinSumCalculator(numbers, start, mid);
        leftTask.fork();
        MyForkJoinSumCalculator rightTask = new MyForkJoinSumCalculator(numbers, mid, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long[] numbers = LongStream.rangeClosed(1, 10_000_000L).toArray();
        MyForkJoinSumCalculator task = new MyForkJoinSumCalculator(numbers);
        Long result = pool.invoke(task);
        System.out.println(result);
    }
}
