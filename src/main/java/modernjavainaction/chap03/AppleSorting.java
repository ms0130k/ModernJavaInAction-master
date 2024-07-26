package modernjavainaction.chap03;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleSorting {
    public static void main(String[] args) {
        List<Apple> inventory = generate();
        inventory.sort(new AppleComparator());

        inventory = generate();
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        });

        inventory = generate();
        inventory.sort((Apple a1, Apple a2) -> Integer.compare(a1.getWeight(), a2.getWeight()));

        inventory = generate();
        inventory.sort(Comparator.comparingInt(Apple::getWeight));

        inventory = generate();
        inventory.sort(Comparator.comparing(Apple::getWeight));

        Comparator<Apple> comparator =Comparator.comparingInt(Apple::getWeight);
        Comparator<Apple> reverse = comparator.reversed();

        inventory.sort(reverse.thenComparing(Apple::getColor));
//        System.out.println(inventory);

        Predicate<Apple> applePredicate = apple -> apple.getWeight() > 50;
        List<Apple> collect = inventory.stream()
                .filter(applePredicate.negate())
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple o1, Apple o2) {
            return Integer.compare(o1.getWeight(), o2.getWeight());
        }
    }

    private static List<Apple> generate() {
        List<Apple> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            result.add(Apple.of(randomInt(), randomColor()));
        }
        return result;
    }

    private static int randomInt() {
        return (int) (Math.random() * 100);
    }

    private static Color randomColor() {
        return Math.random() < 0.5 ? Color.RED : Color.GREEN;
    }
}
