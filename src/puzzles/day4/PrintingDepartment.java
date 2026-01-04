package puzzles.day4;

import java.util.ArrayList;
import java.util.List;

import puzzles.Puzzle;

public class PrintingDepartment extends Puzzle {

    char[][] floor;
    char[][] floorOut;

    public PrintingDepartment(String fileName) {
        super("day4", fileName);
    }

    @Override
    protected void processInput() {
        int width = super.puzzleInput.get(0).length();
        int height = super.puzzleInput.size();

        floor = new char[height][width];

        // debug
        floorOut = new char[height][width];

        for (int i = 0; i < super.puzzleInput.size(); i++) {
            floor[i] = super.puzzleInput.get(i).toCharArray();
            floorOut[i] = super.puzzleInput.get(i).toCharArray();
        }

        // printFloor(floor);
    }

    private void printFloor(char[][] floor) {
        int width = floor[0].length;
        int height = floor.length;
        for (int row = 0; row < width; row++) {
            String out = "";
            for (int col = 0; col < height; col++) {
                out += floor[row][col];
            }
            System.out.println(out);
        }
    }

    @Override
    protected String part1() {
        int rollsAccessible = 0;

        for (int row = 0; row < floor.length; row++) {
            for (int col = 0; col < floor[0].length; col++) {
                if (floorAt(row, col) == '@' && surroundingRolls(row, col) < 4) {
                    rollsAccessible++;
                    floorOut[row][col] = 'x';
                }
            }
        }

        // printFloor(floorOut);

        return String.valueOf(rollsAccessible);
    }

    private int surroundingRolls(int row, int col) {

        int surroundingRolls = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0) && floorAt(row + i, col + j) == '@') {
                    surroundingRolls++;
                }
            }
        }

        return surroundingRolls;
    }

    private char floorAt(int row, int col) {
        try {
            return floor[row][col];
        } catch (IndexOutOfBoundsException e) {
            return '.';
        }
    }

    @Override
    protected String part2() {
        int rollsRemoved = 0;
        List<int[]> rollsRemovable = findremovableRolls();

        while (rollsRemovable.size() > 0) {
            rollsRemoved += rollsRemovable.size();
            removeAccessibleRolls(rollsRemovable);
            rollsRemovable = findremovableRolls();
        }

        return String.valueOf(rollsRemoved);
    }

    private List<int[]> findremovableRolls() {
        List<int[]> rollsAccessible = new ArrayList<>();
        for (int row = 0; row < floor.length; row++) {
            for (int col = 0; col < floor[0].length; col++) {
                if (floorAt(row, col) == '@' && surroundingRolls(row, col) < 4) {
                    rollsAccessible.add(new int[] { row, col });
                }
            }
        }
        return rollsAccessible;
    }

    private void removeAccessibleRolls(List<int[]> accessibleRolls) {
        for (int[] location : accessibleRolls) {
            floor[location[0]][location[1]] = '.';
        }
    }

}
