package modernjavainaction.chap06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.*;
import static modernjavainaction.chap06.Dish.menu;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    public Set<Characteristics> characteristics() {
        return Set.of(UNORDERED, CONCURRENT, IDENTITY_FINISH);
    }

    public static void main(String[] args) {
        List<Dish.Type> collect = menu.stream()
                .map(Dish::getType)
//                .collect(new ToListCollector<>());
                .collect(ArrayList::new,
                        List::add,
                        List::addAll);
//        System.out.println(collect);

        boolean b = IntStream.rangeClosed(2, 1)
                .noneMatch(i -> 1 % i == 0);
//        System.out.println(b);

        boolean prime = isPrime(1);
        System.out.println(prime);
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int) Math.sqrt(candidate);
        return primes.stream()
                .takeWhile(i -> i <= candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
