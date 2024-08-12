package modernjavainaction.chap07;

import java.util.stream.Stream;

public class ParallelStreamTest {
    public static void main(String[] args) {
//        long sum = iterativeSum(100);
//        System.out.println(sum);
//        long sum1 = sequentialSum(100);
//        System.out.println(sum1);
//        long sum2 = paralleflSum(100000);
//        System.out.println(sum2);

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }

    public static long iterativeSum(long n) {
        long sum = 0;
        for (long i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static long sequentialSum(long n) {
//        return Stream.iterate(1L, i -> i <= n, i -> i + 1)
//                .reduce(0L, Long::sum);
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> {
                    System.out.println(Thread.currentThread().getName());
                    return i + 1;
                })
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
}
