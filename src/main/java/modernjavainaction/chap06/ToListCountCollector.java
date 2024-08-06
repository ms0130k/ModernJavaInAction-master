package modernjavainaction.chap06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static modernjavainaction.chap06.Dish.menu;

public class ToListCountCollector<T> implements Collector<T, List<T>, Integer> {

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, Integer> finisher() {
        return list -> list.size();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.CONCURRENT,
                Characteristics.UNORDERED
        ));
    }

    public static void main(String[] args) {
        Integer collect = menu.stream()
                .collect(new ToListCountCollector<>());
        System.out.println(collect);
    }
}
