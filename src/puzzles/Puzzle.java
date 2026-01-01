package puzzles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class Puzzle {

    protected List<String> puzzleInput = null;

    public Puzzle(String folderName, String fileName) {
        System.out.println("######### " + folderName + ": " + this.getClass().getSimpleName() + " ########");
        Path filePath = Path.of("input", folderName, fileName);
        System.out.print("Reading input file " + filePath.toString() + " ...");
        try {
            this.puzzleInput = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("\nReading Input file failed.");
            e.printStackTrace();
            return;
        }
        System.out.println("Done!");
    }

    public void solve() {
        solvePuzzle(() -> part1(), "Part 1");
        solvePuzzle(() -> part2(), "Part 2");
    }

    public void solvePart1() {
        solvePuzzle(() -> part1(), "Part 1");
    }

    public void solvePart2() {
        solvePuzzle(() -> part2(), "Part 2");
    }

    private void solvePuzzle(Callable<String> task, String part) {
        try {
            Instant start = Instant.now();

            String solution = task.call();

            System.out.println("Solution for " + this.getClass().getSimpleName() + " " + part);

            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            System.out.println("Processing time ms: " + duration.toMillis());

            String bar = "";
            for (int i = 0; i < solution.length(); i++) {
                bar += "━";
            }
            System.out.println(bar);
            System.out.println(solution);
            System.out.println(bar);
        } catch (UnsupportedOperationException e) {
            // ignored
        } catch (Exception e) {
            System.out.println("Execution Failed.");
            e.printStackTrace();
        }
    }

    abstract protected String part1();

    abstract protected String part2();

}
