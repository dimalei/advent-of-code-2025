package puzzles.day7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import puzzles.Puzzle;

public class Laboratories extends Puzzle {

    public Laboratories(String fileName) {
        super("day7", fileName);
    }

    @Override
    protected void processInput() {
    }

    @Override
    protected String part1() {
        int timesSplit = 0;

        int initialBeam = puzzleInput.getFirst().indexOf("S");
        int[] beams = new int[] { initialBeam };

        for (int level = 2; level < puzzleInput.size(); level += 2) {
            Set<Integer> newBeams = new HashSet<>();

            for (int beam : beams) {
                if (puzzleInput.get(level).charAt(beam) == '^') {
                    timesSplit++;
                    newBeams.add(beam - 1);
                    newBeams.add(beam + 1);
                } else {
                    newBeams.add(beam);
                }
            }

            beams = newBeams.stream().mapToInt(n -> n).toArray();

        }

        return String.valueOf(timesSplit);
    }

    @Override
    protected String part2() {

        Map<Integer, Long> beams = new HashMap<>();

        int initialBeam = puzzleInput.getFirst().indexOf("S");
        beams.put(initialBeam, 1L);

        for (int level = 2; level < puzzleInput.size(); level += 2) {

            int[] beamKeys = beams.keySet().stream().mapToInt(i -> i).toArray();

            for (var beam : beamKeys) {
                if (puzzleInput.get(level).charAt(beam) == '^') {

                    beams.compute(beam - 1, (k, v) -> v == null ? beams.get(beam) : v + beams.get(beam)); // left split
                    beams.compute(beam + 1, (k, v) -> v == null ? beams.get(beam) : v + beams.get(beam)); // right split
                    beams.remove(beam); // original beam
                }
            }
        }

        long sum = beams.values().stream()
                .reduce(0L, (a, b) -> a + b);

        return String.valueOf(sum);
    }
}
