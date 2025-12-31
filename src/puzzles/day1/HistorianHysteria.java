package puzzles.day1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import puzzles.Puzzle;

public class HistorianHysteria extends Puzzle {

    public HistorianHysteria(String fileName) {
        super(1, fileName);
    }

    @Override
    protected String part1() {

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        parseInput(listA, listB, super.puzzleInput);

        listA.sort(Comparator.naturalOrder());
        listB.sort(Comparator.naturalOrder());

        List<Integer> deltas = new ArrayList<>();

        for (int i = 0; i < listA.size(); i++) {
            deltas.add(Math.abs(listA.get(i) - listB.get(i)));
        }

        return deltas.stream().collect(Collectors.summingInt((element) -> Integer.valueOf(element))).toString();
    }

    @Override
    protected String part2() {

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        parseInput(listA, listB, super.puzzleInput);

        int score = 0;

        for (int a : listA) {
            score += a * appearsIn(a, listB);
        }

        return String.valueOf(score);
    }

    private int appearsIn(int number, List<Integer> numbers) {
        int apperances = 0;
        for (int b : numbers) {
            if (number == b)
                apperances++;
        }
        return apperances;
    }

    private void parseInput(List<Integer> listA, List<Integer> listB, List<String> input) {
        for (String line : input) {
            String[] numbers = line.split("\s+");
            try {
                listA.add(Integer.valueOf(numbers[0]));
                listB.add(Integer.valueOf(numbers[1]));
            } catch (Exception e) {
                System.out.println("failed to parse: \'" + line + "\'");
            }
        }
    }
}
