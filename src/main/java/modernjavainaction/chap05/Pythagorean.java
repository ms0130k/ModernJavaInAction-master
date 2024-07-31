package modernjavainaction.chap05;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pythagorean {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(1, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)}))
                        .filter(t -> t[2] % 1 == 0)
                .limit(5)
                .forEach(r -> System.out.println(r[0] + ", " + r[1] + ", " + r[2]));

//                        .mapToObj(b -> new int[]{a, b, Math.sqrt(a * a, b * b)}))

//        pythagoreanTriples
//                .limit(5)
//                .forEach(t -> System.out.printf("%d, %d, %d\n", t[0], t[1], t[2]));

        Stream<Object> objectStream = Stream.ofNullable(null);
        long count = objectStream
                .skip(1)
                .sorted()
                .count();
        System.out.printf("count: %d", count);
        System.out.println();
    }
}
