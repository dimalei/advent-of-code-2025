package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PuzzleInputReader {

    public static List<String> readFile(String folderName, String fileName) throws IOException {
        Path filePath = Path.of("input", folderName, fileName);
        System.out.println(filePath.toString());
        return Files.readAllLines(filePath);
    }
}
