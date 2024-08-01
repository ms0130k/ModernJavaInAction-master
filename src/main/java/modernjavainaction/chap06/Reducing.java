package modernjavainaction.chap06;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
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
