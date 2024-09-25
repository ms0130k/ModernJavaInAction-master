package modernjavainaction.chap09;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class MyDebugging {
    public static void main(String[] args) {
        List<Car> cars = List.of(
                new Car("TES", Color.GREEN),
                new Car("HMA", Color.GREEN),
                new Car("TES", Color.RED)
        );

        Map<String, Map<Color, List<Car>>> carsByBrandAndColor =
                cars.stream().collect(groupingBy(Car::getBrand,
                        groupingBy(Car::getColor)));
        System.out.println(carsByBrandAndColor);
    }

    private enum Color {
        RED, GREEN
    }

    private static class Car {
        private final String brand;
        private final Color color;

        public Car(String brand, Color color) {
            this.brand = brand;
            this.color = color;
        }
        public String getBrand() {
            return brand;
        }

        public Color getColor() {
            return color;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "brand='" + brand + '\'' +
                    ", color=" + color +
                    '}';
        }
    }
}
