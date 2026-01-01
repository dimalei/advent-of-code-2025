package puzzles.day1;

import puzzles.Puzzle;

public class SecretEntrance extends Puzzle {
    final int STARTING_POS = 50;
    final int WHEEL_SIZE = 100;

    public SecretEntrance(String fileName) {
        super("day1", fileName);
    }

    @Override
    protected String part1() {
        int zeroCounter = 0;
        int wheelPos = STARTING_POS;

        for (String line : super.puzzleInput) {

            int rotationAmount = Integer.parseInt(line.substring(1));

            if (line.startsWith("R")) {
                wheelPos += rotationAmount;
            } else {
                wheelPos -= rotationAmount;
            }

            wheelPos = Math.floorMod(wheelPos, WHEEL_SIZE);

            if (wheelPos == 0) {
                zeroCounter++;
            }
        }

        return String.valueOf(zeroCounter);
    }

    @Override
    protected String part2() {
        int zeroCounter = 0;
        int wheelPos = STARTING_POS;

        for (String line : super.puzzleInput) {

            int rotationAmount = Integer.parseInt(line.substring(1));

            zeroCounter += rotationAmount / 100;
            rotationAmount = rotationAmount % 100;

            if (line.startsWith("L")) {
                rotationAmount = rotationAmount * -1;
            }

            int prevPos = wheelPos;
            wheelPos += rotationAmount;

            if (wheelPos > 100) {
                zeroCounter++;
            } else if (wheelPos < 0 && prevPos > 0) {
                zeroCounter++;
            }

            wheelPos = Math.floorMod(wheelPos, WHEEL_SIZE);

            if (wheelPos == 0) {
                zeroCounter++;
            }
        }

        return String.valueOf(zeroCounter);
    }

}
