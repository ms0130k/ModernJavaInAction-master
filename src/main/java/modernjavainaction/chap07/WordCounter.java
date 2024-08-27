package modernjavainaction.chap07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static modernjavainaction.chap07.WordCount.SENTENCE;

public class WordCounter {
    private int count;
    private boolean isSpace;

    private WordCounter(int count, boolean isSpace) {
        this.count = count;
        this.isSpace = isSpace;
    }

    private WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return isSpace ? this : new WordCounter(count, true);
        }
        return isSpace ? new WordCounter(count + 1, false) : this;
    }

    private WordCounter combine(WordCounter wordCounter) {
        System.out.println("combine");
        return new WordCounter(count + wordCounter.getCount(), true);
    }

    private int getCount() {
        return count;
    }

    public static int countWords(String sentence) {
        return IntStream.range(0, sentence.length())
                .parallel()
                .mapToObj(sentence::charAt)
                .reduce(new WordCounter(0, true),
                        WordCounter::accumulate,
                        WordCounter::combine
                ).getCount();
    }

    public static void main(String[] args) {
        int i = countWords(SENTENCE);
        System.out.println(i);
    }
}
