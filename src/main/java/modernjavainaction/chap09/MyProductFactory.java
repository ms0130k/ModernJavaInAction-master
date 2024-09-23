package modernjavainaction.chap09;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;


public class MyProductFactory {
    interface Product {
        String getName();
    }

    static class Loan implements Product {
        @Override
        public String getName() {
            return "Loan";
        }
    }

    static class Stock implements Product {
        @Override
        public String getName() {
            return "Stock";
        }
    }

    static class Bond implements Product {
        @Override
        public String getName() {
            return "Bond";
        }
    }

    private static final Map<String, Supplier<Product>> map = Map.of(
            "Loan", Loan::new,
            "Stock", Stock::new,
            "Bond", Bond::new
    );

    public static Product createProduct(String name) {
        return Optional.ofNullable(map.get(name))
                .orElseThrow(() -> new IllegalArgumentException("No such product " + name))
                .get();

    }

    public static void main(String[] args) {
        MyProductFactory.createProduct("Lon");
    }
}
