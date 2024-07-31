package modernjavainaction.chap05;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Fibonacci {
    public static void main(String[] args) {
//        0, 1, 1, 2, 3, 5, 8, 13, 21
        int[] arr = {0, 1};
        int[] arr2 = {
                arr[1], arr[0] + arr[1]
        };

//        Stream.iterate(new int[]{0, 1},
//                n -> n.length == 2,
//                        n -> new int[]{n[1], n[0] + n[1]})
//                .map(a -> a[0])
//                .limit(10)
//                .forEach(System.out::println);

//        int sum = Stream.iterate(1, n -> n > 0 && n < 100, n -> n + 2)
//                .mapToInt(n -> {
////                    System.out.println(n);
//                    return n;
//                })
//                .sum();
//        System.out.println(sum);
        long count = Stream.iterate(0, n -> {
                    System.out.println(n);
                    return n + 2;
                })
                .takeWhile(n -> {
                    System.out.println("takeWhile");
                    return n < 100;
                })
                .count();
//        System.out.println("end");
//        System.out.println(count);

//        Stream.generate(Math::random)
//                .limit(5)
//                .map(d -> d * 100)
//                .mapToInt(d -> d.intValue())
//                .forEach(System.out::println);
        IntSupplier fib = new IntSupplier() {
            private int prev = 0;
            private int curr = 1;
            @Override
            public int getAsInt() {
                int oldPrev = prev;
                int next = prev + curr;
                prev = curr;
                curr = next;
                return oldPrev;
            }
        };

        IntStream.generate(fib)
                .takeWhile(n -> n < 100)
                .limit(20)
                .forEach(System.out::println);

    }
}
