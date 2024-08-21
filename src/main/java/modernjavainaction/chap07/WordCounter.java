package modernjavainaction.chap07;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static modernjavainaction.chap07.WordCount.SENTENCE;

public class WordCounter {
    public static int countWordIteratively(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        boolean prevIsSpace = true;
        for (char c : chars) {
            if (Character.isWhitespace(c)) {
                prevIsSpace = true;
                continue;
            }
            if (prevIsSpace) result++;
            prevIsSpace = false;
        }
        return result;
    }

    public static void main(String[] args) {
        int i = countWordIteratively(SENTENCE);
        assert i == 19;
        long start = System.nanoTime();
        int iterationCount = 1000000;
        for (int j = 0; j < iterationCount; j++) {
            List<Character> collect = SENTENCE.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());
        }
        System.out.println((System.nanoTime() - start) / 1_000_000);

        start = System.nanoTime();
        for (int j = 0; j < iterationCount; j++) {
            List<Character> collect1 = IntStream.range(0, SENTENCE.length())
                    .mapToObj(SENTENCE::charAt)
                    .collect(Collectors.toList());
        }
        System.out.println((System.nanoTime() - start) / 1_000_000);
    }
}
