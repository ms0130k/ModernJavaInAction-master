package modernjavainaction.chap06;

import akka.io.dns.CNameRecord;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    public static int type = 1;

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<>() {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate) -> {
            if (type == 1) {
                List<Integer> primes = acc.get(true);
                acc.get(isPrime(primes, candidate))
                        .add(candidate);
            } else {
                acc.get(isPrime(candidate))
                        .add(candidate);
            }
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(Characteristics.IDENTITY_FINISH);
    }

    public boolean isPrime(int candidate) {
        long l = System.currentTimeMillis();
        if (candidate <= 1) return false;
        if (candidate == 2 || candidate == 3) return true;
        int candidateRoot = (int) Math.sqrt(candidate);
        boolean result = IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
//        System.out.println(System.currentTimeMillis() - l);
        return result;
    }
    public boolean isPrime(List<Integer> primes, int candidate) {
        long l = System.currentTimeMillis();
        if (candidate <= 1) return false;
        if (candidate == 2 || candidate == 3) return true;
        int candidateRoot = (int) Math.sqrt(candidate);

        boolean result = primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
//        System.out.println(System.currentTimeMillis() - l);
        return result;
    }

    public static void main(String[] args) {
//        type = 2;
        long l = System.currentTimeMillis();
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(1, 100000000)
                .boxed()
                .collect(new PrimeNumbersCollector());
        int size = collect.get(false).size();
        System.out.println(size);
        System.out.println(System.currentTimeMillis() - l);
//        9335421
//        4246
//        9335421
//        2298

//        94238545
//        98244
//        94238545
//        35228
    }
}
