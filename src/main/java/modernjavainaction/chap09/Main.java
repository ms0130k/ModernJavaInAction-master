package modernjavainaction.chap09;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
//        doSomething((Task) () -> System.out.println("task"));
//        Validator validator = new Validator(s -> s.matches("\\d+"));
//        System.out.println(validator.validate("9"));
//        System.out.println(validator.validate("d"));
//
//        new OnlineBanking().processCustomer(10, System.out::println);

        Feed feed = new Feed();
        feed.registerObserver(new Guardian());
        feed.registerObserver(System.out::println);

        feed.notifyObserver("test queen");
    }

    interface Task {
        public void execute();
    }

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task t) {
        t.execute();
    }


    public static class Validator {
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }

    public interface ValidationStrategy {
        boolean execute(String s);
    }

    public static class OnlineBanking {
        public void processCustomer(int id, IntConsumer makeCustomerHappy) {
            makeCustomerHappy.accept(id);
        }

    }

    interface Observer {
        void notify(String tweet);
    }

    static class NYTimes implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        }
    }

    static class Guardian implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet more news from London! " + tweet);
            }
        }
    }

    static class LeMonde implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        }
    }

    interface Subject {
        void registerObserver(Observer o);

        void notifyObserver(String tweet);
    }

    static class Feed implements Subject {
        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void notifyObserver(String tweet) {
            observers.forEach(observer -> observer.notify(tweet));
        }
    }
}
