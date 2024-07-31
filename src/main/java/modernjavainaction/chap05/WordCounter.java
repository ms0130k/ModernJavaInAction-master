package modernjavainaction.chap05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounter {
    public static void main(String[] args) {
        String pathStr = "src/main/resources/modernjavainaction/chap05/data.txt";
        Path path = Paths.get(pathStr);
        File file = path.toFile();
        boolean exists = file.exists();
        System.out.println(exists);

        try (Stream<String> lines = Files.lines(path)) {
            lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stream.iterate(0, n -> n +2)
                .limit(10)
                .forEach(System.out::println);
    }
}
