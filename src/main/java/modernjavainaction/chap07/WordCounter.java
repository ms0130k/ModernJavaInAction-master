package modernjavainaction.chap07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static modernjavainaction.chap07.WordCount.SENTENCE;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        }
        return lastSpace ?
                new WordCounter(counter + 1, false) :
                this;
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter,
                wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }

    public static int countWords(Stream<Character> stream) {
        return stream.reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine
        ).getCounter();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long[] numbers = LongStream.rangeClosed(1, 1000000).toArray();
        Long result = pool.invoke(new ForkJoinSumCalculator(numbers));
        System.out.println(result);
    }

    static class ForkJoinSumCalculator extends RecursiveTask<Long> {
        private final long[] numbers;
        private final int start;
        private final int end;
        private final long THRESHOLD = 10_000;

        public ForkJoinSumCalculator(long[] numbers) {
            this(numbers, 0, numbers.length);
        }

        public ForkJoinSumCalculator(long[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            if (THRESHOLD >= length) {
                return computeSequentially();
            }

            int mid = (start + end) / 2;
            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, mid);
            leftTask.fork();
            ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, mid, end);
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();
            return rightResult + leftResult;
        }

        private long computeSequentially() {
            long result = 0;
            for (int i = start; i < end; i++) {
                result += numbers[i];
            }
            return result;
        }
    }

}
