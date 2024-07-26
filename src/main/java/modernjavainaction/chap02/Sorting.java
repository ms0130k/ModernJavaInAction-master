package modernjavainaction.chap02;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;

public class Sorting {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(Apple.of(Apple.Color.RED), Apple.of(Apple.Color.GREEN));
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);
    }
}
