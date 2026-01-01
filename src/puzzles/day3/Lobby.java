package puzzles.day3;

import puzzles.Puzzle;

public class Lobby extends Puzzle {

    public Lobby(String fileName) {
        super("day3", fileName);
    }

    @Override
    protected String part1() {

        int total = 0;

        for (String line : super.puzzleInput) {
            total += largestJoltage(line.toCharArray());
        }

        return String.valueOf(total);
    }

    private int largestJoltage(char[] numbers) {
        char largestTen = 0;
        char largestOne = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > largestTen && i < numbers.length - 1) {
                largestTen = numbers[i];
                largestOne = 0;
            } else if (numbers[i] > largestOne) {
                largestOne = numbers[i];
            }
        }

        StringBuilder totalBuilder = new StringBuilder();
        totalBuilder.append(largestTen).append(largestOne);

        return Integer.valueOf(totalBuilder.toString());
    }

    @Override
    protected String part2() {
        long total = 0;

        for (String line : super.puzzleInput) {
            total += largestJoltageTwelve(line.toCharArray());
        }

        return String.valueOf(total);
    }

    private long largestJoltageTwelve(char[] numbers) {
        char[] largestNth = new char[12];

        for (int i = 0; i < numbers.length; i++) {

            // ignore final largest Nths
            int largestNthIndexBegin = largestNth.length - (numbers.length - i);

            for (int n = largestNthIndexBegin < 0 ? 0 : largestNthIndexBegin; n < largestNth.length; n++) {

                if (numbers[i] > largestNth[n]) {
                    largestNth[n] = numbers[i];

                    // reset all less significant Nth
                    for (int m = n + 1; m < largestNth.length; m++) {
                        largestNth[m] = 0;
                    }
                    break;
                }

            }
        }

        StringBuilder totalBuilder = new StringBuilder();
        totalBuilder.append(largestNth);

        return Long.valueOf(totalBuilder.toString());
    }

}
