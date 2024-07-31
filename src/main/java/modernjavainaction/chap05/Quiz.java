package modernjavainaction.chap05;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Quiz {
    public static void main(String[] args) {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(3, 4);

        list1.stream()
                .flatMap(i -> list2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));

        System.out.println("====================================");

        List<Integer> someNumbers = List.of(1, 2, 3, 4, 5, 6);
        Integer i = someNumbers.stream()
                .filter(n -> n % 3 == 0)
                .map(n -> n * n)
                .findAny()
                .orElse(0);

        System.out.println(i);

        Integer sum = someNumbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        Integer max = someNumbers.stream().reduce(0, Integer::max);
        System.out.println(max);

        Integer min = someNumbers.stream().reduce(0, Integer::min);
        System.out.println(min);


    }
}
