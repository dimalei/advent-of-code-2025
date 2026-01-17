package puzzles.day8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import puzzles.Puzzle;

public class Playground extends Puzzle {

    private List<int[]> boxes = new ArrayList<>();
    private Map<Double, int[]> distances = new HashMap<>(); // int[] = id's of boxes
    private List<Double> orderedDistances = new ArrayList<>();

    Map<Integer, Set<Integer>> circuits = new HashMap<>(); // circuitId -> List<boxId>
    Map<Integer, Integer> boxToCircuitMap = new HashMap<>(); // boxId -> circuitId

    private boolean test = true;

    private int currentCircuitId = 0;
    private int cycles = 0;

    private static int TEST_CYCLES = 10;
    private static int FINAL_CYCLES = 1000;

    public Playground(String fileName) {
        super("day8", fileName);
    }

    @Override
    protected void processInput() {

        // create junction boxes
        puzzleInput.forEach(line -> {
            var axis = line.split(",");
            boxes.add(new int[] { Integer.valueOf(axis[0]), Integer.valueOf(axis[1]), Integer.valueOf(axis[2]) });
        });

        // compute all distances
        // TODO: find a better solution
        for (int i = 0; i < boxes.size() - 1; i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                double distance = distance(boxes.get(i), boxes.get(j));
                if (distances.containsKey(distance)) {
                    throw new RuntimeException("Distance is already contained in distances: " + distance);
                }
                distances.put(distance, new int[] { i, j });

                int insertionIndex = -Collections.binarySearch(orderedDistances, distance) - 1;
                orderedDistances.add(insertionIndex, distance);
            }
        }
    }

    @Override
    protected String part1() {
        cycles = test ? TEST_CYCLES : FINAL_CYCLES;

        int i = 0;
        while (i < cycles) {
            double distance = orderedDistances.get(i++);

            int boxA = distances.get(distance)[0];
            int boxB = distances.get(distance)[1];

            if (!boxToCircuitMap.containsKey(boxA) && !boxToCircuitMap.containsKey(boxB)) {

                // case1: boxes are in no circuit

                currentCircuitId++;
                boxToCircuitMap.put(boxA, currentCircuitId);
                boxToCircuitMap.put(boxB, currentCircuitId);

                Set<Integer> newCircuit = new HashSet<>(Set.of(boxA, boxB));
                circuits.put(currentCircuitId, newCircuit);

            } else if (!boxToCircuitMap.containsKey(boxA)) {
                // case2: boxB is wired, boxA is not
                // wire A into B
                combineCircuits(boxB, boxA);

            } else {
                // case3: boxA is wired, boxB is wired or not
                // wire B into A
                combineCircuits(boxA, boxB);
            }
        }

        // System.out.println("Circuits:");
        // for (var string : circuits.entrySet()) {
        // System.out.println("ID: " + string.getKey() + ", boxes: " +
        // string.getValue().size());
        // }

        var result = circuits.values().stream()
                .map(s -> s.size())
                .sorted(Comparator.reverseOrder())
                .limit(3)
                // .peek(v -> System.out.println(v))
                .reduce(1, (a, b) -> a * b);
        return String.valueOf(result);
    }

    private void combineCircuits(int boxA, int boxB) {
        int circuitIdA = boxToCircuitMap.get(boxA);
        Set<Integer> circuitA = circuits.get(circuitIdA);

        if (boxToCircuitMap.containsKey(boxB)) {
            // all boxes in circuitB have to be moved to circuit A

            int circuitIdB = boxToCircuitMap.get(boxB);

            if (circuitIdA == circuitIdB) {
                // boxes are in the same circuit already
                return;
            }

            Set<Integer> circuitB = circuits.get(circuitIdB);

            for (int boxId : circuitB) {
                boxToCircuitMap.put(boxId, circuitIdA);
            }
            circuitA.addAll(circuitB);
            circuits.remove(circuitIdB);

        } else {
            circuitA.add(boxB);
            boxToCircuitMap.put(boxB, circuitIdA);
        }
    }

    @Override
    protected String part2() {

        int i = cycles;
        while (i < orderedDistances.size() && !(circuits.size() == 1 && boxToCircuitMap.size() == boxes.size())) {
            double distance = orderedDistances.get(i++);

            int boxA = distances.get(distance)[0];
            int boxB = distances.get(distance)[1];

            // System.out.println(
            // "combining boxes: [" + boxes.get(boxA)[0] + "," + boxes.get(boxA)[1] + "," +
            // boxes.get(boxA)[2]
            // + "] & [" + boxes.get(boxB)[0] + "," + boxes.get(boxB)[1] + "," +
            // boxes.get(boxB)[2] + "]");

            if (!boxToCircuitMap.containsKey(boxA) && !boxToCircuitMap.containsKey(boxB)) {

                // case1: boxes are in no circuit

                currentCircuitId++;
                boxToCircuitMap.put(boxA, currentCircuitId);
                boxToCircuitMap.put(boxB, currentCircuitId);

                Set<Integer> newCircuit = new HashSet<>(Set.of(boxA, boxB));
                circuits.put(currentCircuitId, newCircuit);

            } else if (!boxToCircuitMap.containsKey(boxA)) {
                // case2: boxB is wired, boxA is not
                // wire A into B
                combineCircuits(boxB, boxA);

            } else {
                // case3: boxA is wired, boxB is wired or not
                // wire B into A
                combineCircuits(boxA, boxB);
            }
        }

        int boxA = distances.get(orderedDistances.get(i - 1))[0];
        int boxB = distances.get(orderedDistances.get(i - 1))[1];

        // System.out.println("Final boxes: [" + boxes.get(boxA)[0] + "," +
        // boxes.get(boxA)[1] + "," + boxes.get(boxA)[2]
        // + "] & [" + boxes.get(boxB)[0] + "," + boxes.get(boxB)[1] + "," +
        // boxes.get(boxB)[2] + "]");

        // System.out.println("circuit size: " + circuits.size());
        // System.out.println("box to circuit size: " + boxToCircuitMap.size());
        // System.out.println("boxes size: " + boxes.size());

        long result = (long) boxes.get(boxA)[0] * boxes.get(boxB)[0];

        return String.valueOf(result);
    }

    public static double distance(int[] a, int[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        double dz = a[2] - b[2];

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public void setTest(boolean value) {
        this.test = value;
    }
}
