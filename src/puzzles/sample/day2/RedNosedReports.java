package puzzles.sample.day2;

import java.util.ArrayList;
import java.util.List;

import puzzles.Puzzle;

public class RedNosedReports extends Puzzle {

    public RedNosedReports(String fileName) {
        super("test2", fileName);
    }

    @Override
    protected String part1() {
        List<int[]> parsed = parseInput();

        int safeReports = 0;

        for (int[] report : parsed) {

            int i = 1;
            boolean isRising = false;
            boolean same = true;
            while (i < report.length
                    && same
                    && Math.abs(report[i - 1] - report[i]) >= 1
                    && Math.abs(report[i - 1] - report[i]) <= 3) {
                boolean wasRising = isRising;
                isRising = report[i] > report[i - 1];
                if (i != 1) {
                    same = wasRising == isRising;
                }
                i++;
            }
            if (i == report.length)
                safeReports++;
        }

        return String.valueOf(safeReports);
    }

    @Override
    protected String part2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'part2'");
    }

    private List<int[]> parseInput() {
        List<int[]> parsed = new ArrayList<>();

        for (String line : super.puzzleInput) {
            String[] subStrings = line.split("\s+");
            var array = List.of(subStrings).stream().mapToInt(x -> Integer.valueOf(x)).toArray();
            parsed.add(array);
        }

        return parsed;
    }

    @Override
    protected void processInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processInput'");
    }

}
