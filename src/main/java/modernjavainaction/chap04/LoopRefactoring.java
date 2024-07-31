package modernjavainaction.chap04;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static modernjavainaction.chap04.Dish.menu;

public class LoopRefactoring {
    public static void main(String[] args) {
        List<String> highColoricDishes = new ArrayList<>();

//        Iterator<Dish> iterator = menu.iterator();
//        while (iterator.hasNext()) {
//            Dish dish = iterator.next();
//            if (dish.getCalories() > 300) {
//                highColoricDishes.add(dish.getName());
//            }
//        }
        List<String> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
//        System.out.println(highColoricDishes);
        System.out.println(collect);
        //[pork, beef, chicken, french fries, rice, pizza, prawns, salmon]


    }
}
