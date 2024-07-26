package modernjavainaction.chap02;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class PrettyPrintApple {
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for(Apple apple: inventory) {
            String output = formatter.apply(apple);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = List.of(Apple.of(Apple.Color.RED), Apple.of(Apple.Color.GREEN));

        AppleFormatter formatter = apple -> "An apple of " + apple.getColor() + " color";
        AppleFormatter formatter2 = apple -> null;

        prettyPrintApple(inventory, formatter);
        prettyPrintApple(inventory, formatter2);

        ToIntFunction<String> stringToInt = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;
        TripleFunction<Integer, String, Boolean, Triple> createTriple = Triple::new;
    }

    public void test() {
        Predicate<String> startsWithNumber = this::startsWithNumber;
    }

    public boolean startsWithNumber(String str) {
        return true;
    }
}

class Triple {
    Triple(int a, String b, boolean c) {

    }
}

interface TripleFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
