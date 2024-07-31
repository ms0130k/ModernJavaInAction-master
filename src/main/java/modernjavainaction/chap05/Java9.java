package modernjavainaction.chap05;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Java9 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 2, 3, 4, 5).stream()
                .takeWhile(n -> n < 3)
                .distinct()
                .skip(1)
                .collect(toList());
        System.out.println(numbers);

        List<Integer> collect = Arrays.asList(1, 2, 3, 4, 5, 4, 6, 7, 8, 9, 10).stream()
                .dropWhile(n -> n < 5)
                .limit(3)
                .skip(1)
                .collect(toList());
        System.out.println(collect);

        List<String> characters = Arrays.asList("Hello", "World").stream()
                .flatMap(word -> Arrays.stream(word.split("")))
                .distinct()
                .collect(toList());
        System.out.println(characters);

        List<Integer> squares = Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(n -> n * n)
                .collect(toList());
        System.out.println(squares);
    }
}
