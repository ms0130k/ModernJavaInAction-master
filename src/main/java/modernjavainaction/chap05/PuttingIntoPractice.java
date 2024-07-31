package modernjavainaction.chap05;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PuttingIntoPractice {

  public static void main(String... args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );
    // 질의 1: 2011년부터 발생한 모든 거래를 찾아 값으로 정렬(작은 값에서 큰 값).
    List<Transaction> transactionsFrom2011 = transactions.stream()
            .filter(tr -> tr.getYear() >= 2011)
            .sorted(comparing(Transaction::getValue))
            .collect(toList());
    System.out.println(transactionsFrom2011);

    // 질의 2: 거래자가 근무하는 모든 고유 도시는?
    List<String> cities = transactions.stream()
            .map(Transaction::getTrader)
            .map(Trader::getCity)
            .distinct()
            .collect(toList());
    System.out.println(cities);

    // 질의 3: Cambridge의 모든 거래자를 찾아 이름으로 정렬.
    List<String> names = transactions.stream()
            .map(Transaction::getTrader)
            .filter(trd -> "Cambridge".equals(trd.getCity()))
            .map(Trader::getName)
            .distinct()
            .sorted()
            .collect(toList());
    System.out.println(names);

    // 질의 4: 알파벳 순으로 정렬된 모든 거래자의 이름 문자열을 반환
    List<String> allNames = transactions.stream()
            .map(Transaction::getTrader)
            .map(Trader::getName)
            .distinct()
            .sorted()
            .collect(toList());
    System.out.println(allNames);

    // 질의 5: Milan에 거주하는 거래자가 있는가?
    boolean existsInMilan = transactions.stream()
            .map(Transaction::getTrader)
            .anyMatch(trd -> "Milan".equals(trd.getCity()));
    System.out.println(existsInMilan);

    // 질의 6: Cambridge에 사는 거래자의 모든 거래내역 출력.
    transactions.stream()
            .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
            .map(Transaction::getValue)
            .forEach(System.out::println);

    // 질의 7: 모든 거래에서 최고값은 얼마인가?
    Optional<Transaction> max = transactions.stream()
            .max(comparing(Transaction::getValue));
    if (max.isPresent()) {
      System.out.printf("max: %d\n", max.get().getValue());
    }

    // 가장 작은 값을 가진 거래 탐색
    Optional<Integer> optionalMin = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::min);
    if (optionalMin.isPresent()) {
      System.out.printf("optionalMin.get(): %d\n", optionalMin.get());
    }
    transactions = List.of();
    int sum = transactions.stream()
            .mapToInt(Transaction::getValue)
            .sum();
    System.out.printf("sum: %d\n", sum);

    OptionalInt max1 = transactions.stream()
            .mapToInt(Transaction::getValue)
            .max();
//    System.out.printf("max1: %d\n", max1);


    int sum1 = IntStream.range(0, 100)
            .sum();
    System.out.println(sum1);
    int sum2 = IntStream.rangeClosed(0, 100)
            .sum();
    System.out.println(sum2);

    System.out.println("" + max1.isPresent() + max1.isEmpty());
    System.out.println(Math.sqrt(2) % 1);
    System.out.println(Math.sqrt(2) % 1.0 == Math.sqrt(2) % 1);

//    // 질의 1: 2011년부터 발생한 모든 거래를 찾아 값으로 정렬(작은 값에서 큰 값).
//    List<Transaction> tr2011 = transactions.stream()
//        .filter(transaction -> transaction.getYear() == 2011)
//        .sorted(comparing(Transaction::getValue))
//        .collect(toList());
//    System.out.println(tr2011);
//
//    // 질의 2: 거래자가 근무하는 모든 고유 도시는?
//    List<String> cities = transactions.stream()
//        .map(transaction -> transaction.getTrader().getCity())
//        .distinct()
//        .collect(toList());
//    System.out.println(cities);
//
//    // 질의 3: Cambridge의 모든 거래자를 찾아 이름으로 정렬.
//    List<Trader> traders = transactions.stream()
//        .map(Transaction::getTrader)
//        .filter(trader -> trader.getCity().equals("Cambridge"))
//        .distinct()
//        .sorted(comparing(Trader::getName))
//        .collect(toList());
//    System.out.println(traders);
//
//    // 질의 4: 알파벳 순으로 정렬된 모든 거래자의 이름 문자열을 반환
//    String traderStr = transactions.stream()
//        .map(transaction -> transaction.getTrader().getName())
//        .distinct()
//        .sorted()
//        .reduce("", (n1, n2) -> n1 + n2);
//    System.out.println(traderStr);
//
//    // 질의 5: Milan에 거주하는 거래자가 있는가?
//    boolean milanBased = transactions.stream()
//        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
//    System.out.println(milanBased);
//
//    // 질의 6: Cambridge에 사는 거래자의 모든 거래내역 출력.
//    transactions.stream()
//        .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
//        .map(Transaction::getValue)
//        .forEach(System.out::println);
//
//    // 질의 7: 모든 거래에서 최고값은 얼마인가?
//    int highestValue = transactions.stream()
//        .map(Transaction::getValue)
//        .reduce(0, Integer::max);
//    System.out.println(highestValue);
//
//    // 가장 작은 값을 가진 거래 탐색
//    Optional<Transaction> smallestTransaction = transactions.stream()
//        .min(comparing(Transaction::getValue));
//    // 거래가 없을 때 기본 문자열을 사용할 수 있도록발견된 거래가 있으면 문자열로 바꾸는 꼼수를 사용함(예, the Stream is empty)
//    System.out.println(smallestTransaction.map(String::valueOf).orElse("No transactions found"));
  }

}
