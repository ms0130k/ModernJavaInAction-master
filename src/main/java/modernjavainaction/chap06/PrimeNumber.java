package modernjavainaction.chap06;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.partitioningBy;

public class PrimeNumber {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int index = 1000000 * 9;
//        int index = 100;
//        Map<Boolean, List<Integer>> groupingByIsPrime = getGroupingByIsPrime(index);
        Map<Boolean, Long> groupingByIsPrimeToCounting = getGroupingByIsPrimeToCounting(index);
        System.out.println(groupingByIsPrimeToCounting);
//        {false=8397511, true=602489}
        //        System.out.println(groupingByIsPrime);
        System.out.println(System.currentTimeMillis() - l);
//        boolean prime = isPrime(1);
//        System.out.println(prime);
    }

    public static boolean isPrime(int candidate) {
        int limit = (int) Math.sqrt(candidate);

        if (1 >= candidate) return false;
        if (2 == candidate || 3 == candidate) return true;
        for (int i = 2; i <= limit; i++) {
            if (candidate % i == 0) {
                return false;
            }
        }
        return true;

//        return IntStream.rangeClosed(2, limit)
//                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> getGroupingByIsPrime(int index) {
        return IntStream.rangeClosed(1, index)
                .boxed()
                .collect(partitioningBy(PrimeNumber::isPrime));
    }
    public static Map<Boolean, Long> getGroupingByIsPrimeToCounting(int index) {
        return IntStream.rangeClosed(1, index)
                .boxed()
                .collect(partitioningBy(PrimeNumber::isPrime, Collectors.counting()));
    }
}
