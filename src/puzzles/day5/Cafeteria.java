package puzzles.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import puzzles.Puzzle;

public class Cafeteria extends Puzzle {

    private List<long[]> ranges = new LinkedList<>();
    private List<Long> ingredients = new ArrayList<>();

    public Cafeteria(String fileName) {
        super("day5", fileName);
    }

    @Override
    protected String part1() {
        int okIngredients = 0;

        for (long ingredient : ingredients) {

            // only search in ranges which beginning is smaller than ingredient
            int searchToIndex = Collections.binarySearch(
                    ranges,
                    new long[] { ingredient },
                    Comparator.comparingLong(r -> r[0]))
                    * -1 - 1;

            if (appearsInRanges(ingredient, searchToIndex)) {
                okIngredients++;
            }
        }

        return String.valueOf(okIngredients);

    }

    // search for the first range which end incliudes the ingredient
    private boolean appearsInRanges(long ingredient, int searchToIndex) {

        int i = 0;
        while (i < searchToIndex && ingredient > ranges.get(i)[1]) {
            i++;
        }

        boolean appears = i < searchToIndex;

        return appears;
    }

    @Override
    protected String part2() {

        int i = 0;

        // merge ranges
        while (i < ranges.size() - 1) {
            for (int j = i + 1; j < ranges.size(); j++) {
                if (isOverlapping(ranges.get(i), ranges.get(j))) {

                    long[] newRange = unionRange(ranges.get(i), ranges.get(j));
                    ranges.set(i, newRange);
                    ranges.remove(j);

                    i = i - 1;
                    break;
                }
            }
            i++;
        }

        // debug
        // System.out.println("ranges:");
        // for (long[] range : ranges) {
        // System.out.println(range[0] + " - " + range[1]);
        // }

        // count id's
        long counter = 0;

        for (long[] range : ranges) {
            counter += range[1] - range[0] + 1;
        }

        return String.valueOf(counter);
    }

    protected static boolean isOverlapping(long[] rangeA, long[] rangeB) {
        return rangeA[0] >= rangeB[0] && rangeA[0] - 1 <= rangeB[1]
                || rangeA[1] + 1 >= rangeB[0] && rangeA[1] <= rangeB[1]
                || rangeB[0] >= rangeA[0] && rangeB[0] - 1 <= rangeA[1]
                || rangeB[1] + 1 >= rangeA[0] && rangeB[1] <= rangeA[1];
    }

    protected static long[] unionRange(long[] rangeA, long[] rangeB) {

        long[] newRange = new long[2];

        newRange[0] = rangeA[0] < rangeB[0] ? rangeA[0] : rangeB[0];
        newRange[1] = rangeA[1] > rangeB[1] ? rangeA[1] : rangeB[1];

        return newRange;
    }

    @Override
    protected void processInput() {
        boolean parsingRanges = true;
        for (String line : super.puzzleInput) {

            if (line.isBlank()) {
                parsingRanges = false;

                // create an ordered list (range beginning) of ranges
            } else if (parsingRanges) {
                long[] range = Arrays.stream(line.split("-"))
                        .mapToLong(num -> Long.valueOf(num))
                        .toArray();

                int insertionPoint = Collections.binarySearch(ranges, range, Comparator.comparingLong(r -> r[0]));
                if (insertionPoint < 0) {
                    insertionPoint = insertionPoint * -1 - 1;
                }

                ranges.add(insertionPoint, range);

                // create an unordered lists of ids to check
            } else {
                ingredients.add(Long.valueOf(line));
            }
        }
    }

}
