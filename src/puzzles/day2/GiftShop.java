package puzzles.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import puzzles.Puzzle;
import puzzles.util.NumbersUtil;

public class GiftShop extends Puzzle {

    private List<long[]> ranges = new ArrayList<>();
    private Map<Integer, int[]> divisorsCache = new HashMap<>();

    public GiftShop(String fileName) {
        super("day2", fileName);
        parseInput();
    }

    /*
     * Turn the single line puzzle input into ranges (upper, lower)
     */
    private void parseInput() {
        for (String line : super.puzzleInput) {

            String[] allRanges = line.split(",");

            for (String range : allRanges) {
                String[] rangeArray = range.split("-");
                ranges.add(new long[] {
                        Long.parseLong(rangeArray[0]),
                        Long.parseLong(rangeArray[1])
                });
            }
        }
    }

    @Override
    protected String part1() {

        long sum = 0;

        for (long[] range : ranges) {
            List<Long> invalids = findInvalidDoubles(range);
            for (long invalid : invalids) {
                sum += invalid;
            }
        }

        return String.valueOf(sum);
    }

    private List<Long> findInvalidDoubles(long[] range) {

        List<Long> invalids = new ArrayList<>();

        for (long i = range[0]; i <= range[1]; i++) {

            if (isInvalidDouble(i)) {
                invalids.add(i);
            }
        }
        return invalids;
    }

    private boolean isInvalidDouble(long num) {

        String numStr = String.valueOf(num);

        if (numStr.length() % 2 != 0) {
            return false;
        }

        int patternLength = numStr.length() / 2;

        return numStr.substring(0, patternLength).equals(numStr.substring(patternLength));
    }

    @Override
    protected String part2() {
        long sum = 0;

        for (long[] range : ranges) {
            List<Long> invalids = findInvalids(range);
            for (long invalid : invalids) {
                sum += invalid;
            }
        }

        return String.valueOf(sum);
    }

    private List<Long> findInvalids(long[] range) {

        List<Long> invalids = new ArrayList<>();

        for (long i = range[0]; i <= range[1]; i++) {
            if (isInvalid(i)) {
                invalids.add(i);
            }
        }
        return invalids;
    }

    /*
     * A number is invalid if it consists of repeating patterns. eg. 11, 1212,
     * 543543 etc.
     */
    protected boolean isInvalid(long number) {
        // all numbers below 11 can't contain patterns
        if (number < 11L) {
            return false;
        }

        String numStr = String.valueOf(number);

        // compute divisors of number
        if (divisorsCache.get(numStr.length()) == null) {
            divisorsCache.put(numStr.length(), NumbersUtil.findDivisors(numStr.length()));
        }

        // each divisor of numbers represents the possible pattern lenght
        for (int patterLenght : divisorsCache.get(numStr.length())) {
            String pattern = numStr.substring(0, patterLenght);

            // sequentially search for patterns
            int index = patterLenght;
            while (index <= numStr.length() - patterLenght
                    && numStr.substring(index, index + patterLenght).equals(pattern)) {
                index += patterLenght;
            }
            if (index == numStr.length()) {
                return true;
            }
        }

        return false;
    }

}
