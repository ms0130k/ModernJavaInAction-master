package modernjavainaction.chap15;

import java.util.concurrent.*;

import static modernjavainaction.chap15.Functions.f;
import static modernjavainaction.chap15.Functions.g;

public class ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1337;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> f(x));
        Future<Integer> z = executorService.submit(() -> g(x));
        System.out.println(y.get() + z.get());
        executorService.shutdown();
    }
}
