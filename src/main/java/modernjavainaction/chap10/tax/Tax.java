package modernjavainaction.chap10.tax;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public class Tax {
    public static double regional(double value) {
        return value * 1.1;
    }
    public static double general(double value) {
        return value + 500;
    }
    public static double surcharge(double value) {
        return value / 2;
    }

    static class Order {
        double price = 100;

        double getPrice() {
            return price;
        }
    }
    static class Calculator {
        DoubleUnaryOperator operator = d -> d;

        private Calculator() {}

        static Calculator of() {
            return new Calculator();
        }

        Calculator with(DoubleUnaryOperator operator) {
            this.operator = this.operator.andThen(operator);
            return this;
        }

        double calculate(Order order) {
            return operator.applyAsDouble(order.getPrice());
        }
    }

    public static void main(String[] args) {
        double result = Calculator.of()
                .with(Tax::regional)
                .with(Tax::general)
                .with(Tax::surcharge)
                .calculate(new Order());

        System.out.println(result == 305.0);
    }
}
