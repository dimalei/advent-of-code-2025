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

    private List<Vector3D> boxes = new ArrayList<>();
    private Map<Double, int[]> distances = new HashMap<>(); // int[] = id's of boxes
    private List<Double> orderedDistances = new ArrayList<>();

    Map<Integer, Set<Integer>> circuits = new HashMap<>(); // circuitId -> List<boxId>
    Map<Integer, Integer> boxToCircuitMap = new HashMap<>(); // boxId -> circuitId

    public Playground(String fileName) {
        super("day8", fileName);
    }

    @Override
    protected void processInput() {

        // create junction boxes
        puzzleInput.forEach(line -> {
            var axis = line.split(",");
            boxes.add(new Vector3D(Integer.valueOf(axis[0]), Integer.valueOf(axis[1]), Integer.valueOf(axis[2])));
        });

        // compute all distances
        for (int i = 0; i < boxes.size() - 1; i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                double distance = Vector3D.distance(boxes.get(i), boxes.get(j));
                if (distances.containsKey(distance)) {
                    throw new RuntimeException("Distance is already contained in distances: " + distance);
                }
                distances.put(distance, new int[] { i, j });

                int insertionIndex = -Collections.binarySearch(orderedDistances, distance) - 1;
                orderedDistances.add(insertionIndex, distance);
            }
        }

        // for (double distance : orderedDistances) {
        // System.out.println(distance + " : " + distances.get(distance)[0] + "," +
        // distances.get(distance)[1]);
        // }
    }

    @Override
    protected String part1() {

        int currentCircuitId = 0;

        for (double distance : orderedDistances.subList(0, 1000)) {

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
            int breakPoint = 0;
        }

        System.out.println("Circuits:");
        for (var string : circuits.entrySet()) {
            System.out.println("ID: " + string.getKey() + ", boxes: " +
                    string.getValue().size());
        }

        var result = circuits.values().stream()
                .map(s -> s.size())
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .peek(v -> System.out.println(v))
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'part2'");
    }

}
