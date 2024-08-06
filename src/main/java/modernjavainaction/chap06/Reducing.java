package modernjavainaction.chap06;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static modernjavainaction.chap06.Dish.dishTags;
import static modernjavainaction.chap06.Dish.menu;

public class Reducing {

    public static void main(String... args) {
        Long collect = menu.stream().collect(counting());

//        long count = menu.stream().count();
//        System.out.println(count);
//        Optional<Dish> collect = menu.stream().collect(maxBy(comparing(Dish::getCalories)));
//        System.out.println(collect.get());
//
//        System.out.println(menu.stream().collect(minBy(comparing(Dish::getCalories))).get());
//        System.out.println(menu.stream().collect(summingInt(Dish::getCalories)));
//        System.out.println(menu.stream().map(Dish::getCalories).reduce(0, Integer::sum));
//        System.out.println(menu.stream().collect(averagingInt(Dish::getCalories)));
//        IntSummaryStatistics collect1 = menu.stream().collect(summarizingInt(Dish::getCalories));
//        System.out.println(collect1);
//        System.out.println(menu.stream().map(Dish::getName).collect(joining(", ")));

//        List<Dish> dishes = List.of(
//                new Dish("mydish", false, 100, Dish.Type.MEAT),
//                new Dish("2mydish", false, 100, Dish.Type.MEAT)
//        );
//        Optional<Dish> collect2 = dishes.stream()
//                .collect(reducing((prev, curr) -> {
//                    System.out.println("prev: " + prev);
//                    System.out.println("curr: " + curr);
//                    return prev.getCalories() > curr.getCalories() ? prev : curr;
//                }));
//        Dish dish = collect2.get();
//        System.out.println(dish);
//
//        String collect1 = dishes.stream().map(Dish::getName).collect(joining());
//        System.out.println(collect1);

        Map<Grouping.CaloricLevel, List<Dish>> collect1 = menu.stream()
                .collect(groupingBy(d -> {
                    if (d.getCalories() < 200) {
                        return Grouping.CaloricLevel.DIET;
                    } else if (d.getCalories() < 400) {
                        return Grouping.CaloricLevel.NORMAL;
                    }
                    return Grouping.CaloricLevel.FAT;
                }));
        System.out.println(collect1);

        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println(caloricDishesByType);

        Map<Dish.Type, List<Boolean>> collect2 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> dish.getCalories() > 500, toList())));
        System.out.println(collect2);


        Set<String> collect3 = dishTags.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .flatMap(v -> v.stream())
//                .distinct()
                .collect(toSet());
        System.out.println(collect3);

        Map<Dish.Type, Set<String>> collect4 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        System.out.println(collect4);

        Map<Dish.Type, Map<Grouping.CaloricLevel, List<Dish>>> collect5 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400)
                                return Grouping.CaloricLevel.DIET;
                            if (dish.getCalories() <= 700)
                                return Grouping.CaloricLevel.NORMAL;
                            return Grouping.CaloricLevel.FAT;
                        })));
        System.out.println(collect5);

        Map<Dish.Type, Long> collect6 = menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println(collect6);

        menu.stream()
                .collect(groupingBy(Dish::getType));

        Map<Dish.Type, Optional<Dish>> collect7 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        maxBy(comparingInt(Dish::getCalories))));
        System.out.println(collect7);

        Map<Dish.Type, Dish> collect8 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get
                        )));
        System.out.println(collect8);

        Map<Dish.Type, Set<Grouping.CaloricLevel>> collect9 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                            if (dish.getCalories() <= 400)
                                return Grouping.CaloricLevel.DIET;
                            if (dish.getCalories() <= 600)
                                return Grouping.CaloricLevel.NORMAL;
                            return Grouping.CaloricLevel.FAT;
                        }, toCollection(HashSet::new))));
        System.out.println(collect9);

        Map<Boolean, List<Dish>> collect10 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(collect10);
        List<Dish> dishes = collect10.get(Boolean.TRUE);
        System.out.println(dishes);

        Map<Boolean, Map<Dish.Type, List<Dish>>> collect11 = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                groupingBy(Dish::getType)));

        System.out.println(collect11);

        Map<Boolean, Map<Dish.Type, Dish>> collect12 = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        groupingBy(Dish::getType, collectingAndThen(maxBy(comparing(Dish::getCalories)), Optional::get))));

        System.out.println(collect12);


//    System.out.println("Total calories in menu: " + calculateTotalCalories());
//    System.out.println("Total calories in menu: " + calculateTotalCaloriesWithMethodReference());
//    System.out.println("Total calories in menu: " + calculateTotalCaloriesWithoutCollectors());
//    System.out.println("Total calories in menu: " + calculateTotalCaloriesUsingSum());
    }

    private static int calculateTotalCalories() {
        return menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
    }

    private static int calculateTotalCaloriesWithMethodReference() {
        return menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
    }

    private static int calculateTotalCaloriesWithoutCollectors() {
        return menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
    }

    private static int calculateTotalCaloriesUsingSum() {
        return menu.stream().mapToInt(Dish::getCalories).sum();
    }

}
