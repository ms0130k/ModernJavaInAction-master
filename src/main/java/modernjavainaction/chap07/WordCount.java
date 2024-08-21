package modernjavainaction.chap07;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCount {

    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita \n\n"
                    + "mi  ritrovai in una  selva oscura"
                    + " che la  dritta via era   smarrita ";

    public static void main(String[] args) {


//        int i1 = countWordsIteratively(SENTENCE);

//        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
//        System.out.println("Found " + countWords(SENTENCE) + " words");
//        System.out.println(countWordsByMine(SENTENCE));
//        System.out.println(countWordsByMine2(SENTENCE));
//        System.out.println(calculateTime(WordCount::countWordsByMine2, SENTENCE));
//        System.out.println(calculateTime(WordCount::countWordsByMine, SENTENCE));
//        System.out.println(calculateTime(WordCount::countWordsByMine3, SENTENCE));
//        int i = countWordByMine4(SENTENCE);
//        System.out.println(i);
    }

    public static <T, R> long calculateTime(Function<T, Integer> f, T input) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int result = f.apply(input);
            if (result != 19) {
                System.out.println("fail:: " + result);
            }
        }
        long end = System.nanoTime() - start;
        return end / 1_000_000;
    }

    public static int countWordsByMine(String sentence) {
        String[] words = sentence.split("\\s+");
        ArrayList<String> wordList = new ArrayList<>();
        for (String word : words) {
//            if (wordList.contains(word) || "".equals(word)) {
            if ("".equals(word)) {
                continue;
            }
            wordList.add(word);
        }

        return wordList.size();
    }

    public static int countWordsByMine3(String sentence) {
        String[] words = sentence.split("\\s+");
        ArrayList<String> wordList = new ArrayList<>();
        for (String word : words) {
            if ("".equals(word)) {
                continue;
            }
            wordList.add(word);
        }

        return wordList.size();
    }

    public static int countWordsByMine2(String sentenc) {
        int counter = 0;
        char[] chars = sentenc.toCharArray();
        boolean prevIsSpace = true;
        for (char c : chars) {
            if (Character.isWhitespace(c)) {
                prevIsSpace = true;
                continue;
            }
            if (prevIsSpace) {
                counter++;
            }
            prevIsSpace = false;
        }
        return counter;
    }

    public static int countWordByMine4(String sentence) {
        int result = 0;
        boolean prevIsSpace = true;
        char[] chars = sentence.toCharArray();
        for (char c : chars) {
            if (Character.isWhitespace(c)) {
                prevIsSpace = true;
                continue;
            }
            if (prevIsSpace) {
                result++;
            }
            prevIsSpace = false;
        }
        return result;
    }

    public static void printArray(String[] array) {
        for (String s : array) {
            System.out.println(s);
        }
    }


    public static int countWordsIteratively(String s) {
        int result = 0;
        boolean prevIsSpace = true;

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (Character.isWhitespace(c)) {
                prevIsSpace = true;
                continue;
            }
            if (prevIsSpace) {
                result++;
            }
            prevIsSpace = false;
        }
        return result;
    }

    public static int countWords(String s) {
        //Stream<Character> stream = IntStream.range(0, s.length())
        //    .mapToObj(SENTENCE::charAt).parallel();
        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);

        return countWords(stream);
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

    private static class WordCounter {

        private final int counter;
        private final boolean lastSpace;

        public WordCounter(int counter, boolean lastSpace) {
            this.counter = counter;
            this.lastSpace = lastSpace;
        }

        public WordCounter accumulate(Character c) {
            if (Character.isWhitespace(c)) {
                return lastSpace ? this : new WordCounter(counter, true);
            } else {
                return lastSpace ? new WordCounter(counter + 1, false) : this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
        }

        public int getCounter() {
            return counter;
        }

    }

    private static class WordCounterSpliterator implements Spliterator<Character> {

        private final String string;
        private int currentChar = 0;

        private WordCounterSpliterator(String string) {
            this.string = string;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(string.charAt(currentChar++));
            return currentChar < string.length();
        }

        @Override
        public Spliterator<Character> trySplit() {
            int currentSize = string.length() - currentChar;
            if (currentSize < 10) {
                return null;
            }
            for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
                if (Character.isWhitespace(string.charAt(splitPos))) {
                    Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                    currentChar = splitPos;
                    return spliterator;
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return string.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }

    }

}
