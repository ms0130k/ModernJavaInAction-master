package modernjavainaction.chap05;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WordCounter {
    public static void main(String[] args) {
        Path path = Paths.get("data.txt");
        System.out.println(new File(path.toString()).exists());

        long uniqueWords = 0;
//        try (Stream<String>)
    }
}
