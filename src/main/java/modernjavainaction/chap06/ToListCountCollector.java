package modernjavainaction.chap06;

import java.util.*;
import java.util.function.*;
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
//        Integer collect = menu.stream()
//                .collect(new ToListCountCollector<>());
//        System.out.println(collect);
//
//        int size = menu.stream()
//                .collect(
//                        ArrayList::new,
//                        List::add,
//                        List::addAll
//                ).size();
//        System.out.println(size);



    }

    public static <A> List<A> takeWhile1(List<A> list, Predicate<A> predicate) {
        ArrayList<A> result = new ArrayList<>();
        for (A a : list) {
            if (predicate.test(a)) {
                result.add(a);
            } else {
                return result;
            }
        }
        return result;
    }
    public static <A> List<A> takeWhile2(List<A> list, Predicate<A> predicate) {
        int i = 0;
        for (A a : list) {
            if (!predicate.test(a)) {
                return list.subList(0, i);
            }
            i++;
        }
        return list;
    }
}
