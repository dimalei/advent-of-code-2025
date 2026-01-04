package puzzles.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import puzzles.Puzzle;

public class TrashCompactor extends Puzzle {

    public TrashCompactor(String fileName) {
        super("day6", fileName);
    }

    @Override
    protected void processInput() {
        // parsing is this puzzles task, hence no pre-processing
    }

    @Override
    protected String part1() {
        int[][] numbers;
        int numberRows;
        String[] operators;

        numberRows = super.puzzleInput.size() - 1;

        numbers = new int[numberRows][];

        for (int i = 0; i < numberRows; i++) {
            numbers[i] = Arrays.asList(super.puzzleInput.get(i).trim().split("\s+"))
                    .stream()
                    .mapToInt(num -> Integer.parseInt(num))
                    .toArray();
        }

        operators = super.puzzleInput.getLast().split("\s+");

        long sum = calculateNumbers(numbers, operators, numberRows);

        // printNumbers();

        return String.valueOf(sum);
    }

    private long calculateNumbers(int[][] numbers, String[] operators, int numberRows) {
        long sum = 0;

        for (int column = 0; column < operators.length; column++) {

            long result = numbers[0][column];

            if (operators[column].equals("*")) {
                for (int row = 1; row < numberRows; row++) {
                    result *= numbers[row][column];
                }
            } else if (operators[column].equals("+")) {
                for (int row = 1; row < numberRows; row++) {
                    result += numbers[row][column];
                }
            }

            sum += result;
        }

        return sum;
    }

    private void printNumbers(int[][] numbers, String[] operators) {

        for (int[] row : numbers) {
            String rowStr = "";
            for (var col : row) {
                rowStr += col + ", ";
            }
            System.out.println("[" + rowStr.substring(0, rowStr.length() - 2) + "]");
        }

        String rowStr = "";
        for (var col : operators) {
            rowStr += col + ", ";
        }
        System.out.println("[" + rowStr.substring(0, rowStr.length() - 2) + "]");

    }

    @Override
    protected String part2() {
        long sum = 0;

        int column = puzzleInput.getFirst().length() - 1;

        List<Integer> numbers = new ArrayList<>();
        StringBuilder numberStringBuilder = new StringBuilder();

        while (column >= 0) {

            // parse all numbers
            for (int row = 0; row < puzzleInput.size() - 1; row++) {
                numberStringBuilder.append(puzzleInput.get(row).charAt(column));
            }
            numbers.add(Integer.valueOf(numberStringBuilder.toString().trim()));

            if (puzzleInput.getLast().charAt(column) == '*') {
                // multiply all
                long summand = numbers.getFirst();
                for (int i = 1; i < numbers.size(); i++) {
                    summand *= numbers.get(i);
                }

                // System.out.println(numbers + " multiplied = " + summand);

                sum += summand;
                numbers.clear();
                column--;

            } else if (puzzleInput.getLast().charAt(column) == '+') {
                // add all
                long summand = numbers.getFirst();
                for (int i = 1; i < numbers.size(); i++) {
                    summand += numbers.get(i);
                }

                // System.out.println(numbers + " added = " + summand);

                sum += summand;
                numbers.clear();
                column--;
            }

            numberStringBuilder.delete(0, numberStringBuilder.length());
            column--;
        }

        return String.valueOf(sum);
    }

}
