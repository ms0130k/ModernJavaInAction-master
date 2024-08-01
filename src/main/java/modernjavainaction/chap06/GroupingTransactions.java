package modernjavainaction.chap06;

import static java.util.stream.Collectors.groupingBy;

import java.util.*;

public class GroupingTransactions {

    public static List<Transaction> transactions = Arrays.asList(
            new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0)
    );

    public static void main(String... args) {
//    groupImperatively();
//    groupFunctionally();
//    Map<Currency, List<Transaction>> currencyListMap = groupByMine();
//    System.out.println(currencyListMap);

        Map<Currency, List<Transaction>> currencyListMap = groupByMine();
        System.out.println(currencyListMap);
        currencyListMap = groupByMyStream();
        System.out.println(currencyListMap);

    }

    private static Map<Currency, List<Transaction>> groupByMyStream() {
        List<Transaction> trs = generateTransactions();
        return trs.stream()
//                .collect(groupingBy(Transaction::getCurrency));
                .collect(groupingBy(Transaction::getCurrency));
    }

    private static Map<Currency, List<Transaction>> groupByMine() {
        Map<Currency, List<Transaction>> trsByCurrency = new HashMap<>();
        List<Transaction> trs = generateTransactions();
        for (Transaction tr : trs) {
            List<Transaction> currTrs = trsByCurrency.get(tr.getCurrency());
            if (currTrs == null) {
                currTrs = new ArrayList();
                trsByCurrency.put(tr.getCurrency(), currTrs);
            }
            currTrs.add(tr);
        }
        return trsByCurrency;
    }

    private static List<Transaction> generateTransactions() {
        return Arrays.asList(
                new Transaction(Currency.EUR, 1500.0),
                new Transaction(Currency.USD, 2300.0),
                new Transaction(Currency.GBP, 9900.0),
                new Transaction(Currency.EUR, 1100.0),
                new Transaction(Currency.JPY, 7800.0),
                new Transaction(Currency.CHF, 6700.0),
                new Transaction(Currency.EUR, 5600.0),
                new Transaction(Currency.USD, 4500.0),
                new Transaction(Currency.CHF, 3400.0),
                new Transaction(Currency.GBP, 3200.0),
                new Transaction(Currency.USD, 4600.0),
                new Transaction(Currency.JPY, 5700.0),
                new Transaction(Currency.EUR, 6800.0));
    }

    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

    public static class Transaction {

        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }

    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }

}
