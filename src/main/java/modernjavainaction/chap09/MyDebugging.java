package modernjavainaction.chap09;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;

import static java.util.Comparator.comparing;
import static org.junit.Assert.assertEquals;

public class MyDebugging {

    //TODO 옵저버 다시 작성해볼것
    interface Subject {
        void registObserver(Observer o);
        void nofifyObservers(String s);
    }

    interface Observer {
        void notify(String s);
    }

    static class ConcreteObserver1 implements Observer {

        @Override
        public void notify(String s) {
            System.out.println("Observer1: " + s);
        }
    }

    static class ConcreteObserver2 implements Observer {
        @Override
        public void notify(String s) {
            System.out.println("Observer2: " + s);
        }
    }

    static class ConcreteSubject implements Subject {
        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void nofifyObservers(String s) {
            observers.forEach(o -> o.notify(s));
        }
    }

    public static void main(String[] args) {
//        Point point = new Point(1, 2);
//        point = point.moveRightBy(2);
//        System.out.println(point.getX());
//        System.out.println(point.getY());

//        List<Integer> list = Arrays.asList(1, 2, null);
//        list.forEach(Number::intValue);

//        List<Integer> list = Arrays.asList(2, 3, 4, 5);
//        list.stream()
//                .peek(x -> System.out.println("from stream: " + x))
//                .map(x -> x + 17)
//                .peek(x -> System.out.println("after map: " + x))
//                .filter(x -> x % 2 == 0)
//                .peek(x -> System.out.println("after filter: " + x))
//                .limit(3)
//                .peek(x -> System.out.println("after limit: " + x))
//                .forEach(System.out::println);
//        ConcreteSubject concreteSubject = new ConcreteSubject();
//        concreteSubject.registObserver(System.out::println);
//        concreteSubject.registObserver(System.out::println);
//        concreteSubject.nofifyObservers("test");
//
//        Collections.sort(new ArrayList<IntWrapper>() {
//            {
//                add(new IntWrapper(3));
//                add(new IntWrapper(5));
//                add(new IntWrapper(1));
//            }
//        }, comparing(IntWrapper::getAge).reversed());
//
//        try {
//            List<String> collect = Files.lines(Paths.get(""))
//                    .filter(String::isBlank)
//                    .limit(40)
//                    .collect(toList());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        List<UnaryOperator<String>> operators =
                List.of(
                        s -> s + 1,
                        s -> s + 2,
                        s -> s + 3
                );
        String result = "";
        for (UnaryOperator<String> operator : operators) {
            result = operator.apply(result);
        }
        System.out.println(result);
        String reduce = operators.stream()
                .reduce("", (res, op) -> op.apply(res), (s1, s2) -> s1 + s2);
        System.out.println(reduce);
    }

    static class IntWrapper {
        private final int field;

        IntWrapper(int field) {
            this.field = field;
        }
        private int getAge() {
            return field;
        }
    }

    private static class Point {
        public static final Comparator<Point> compareByXAndThenY =
                comparing(Point::getX).thenComparing(Point::getY);
        private final int x;
        private final int y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Point getThis() {
            return this;
        }

        public Point moveRightBy(int x) {
            return new Point(this.x + x, this.y);
        }
    }

    @Test
    public void testMoveRightBy() {
        Point point = new Point(5, 5).moveRightBy(5);
        assertEquals(10, point.getX());
        assertEquals(5, point.getY());
    }

    @Test
    public void testComparingTwoPoints() {
        Point p1 = new Point(11, 15);
        Point p2 = new Point(10, 20);
        int result = Point.compareByXAndThenY.compare(p1, p2);
        System.out.println(result);
    }

    interface MyComparator<T> {
        int compare(T t1, T t2);
    }

    class MyComparatorImpl implements MyComparator<Point> {

        @Override
        public int compare(Point t1, Point t2) {
            return t1.getX() - t2.getX();
        }
    }
}
