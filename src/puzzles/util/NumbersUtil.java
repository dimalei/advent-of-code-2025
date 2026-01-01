package puzzles.util;

import java.util.HashSet;
import java.util.Set;

public class NumbersUtil {

    public static int[] findDivisors(int number) {
        if (number <= 0) {
            return new int[] {};
        }
        if (number < 4) {
            return new int[] { 1 };
        }

        Set<Integer> divisors = new HashSet<>();
        divisors.add(1);

        for (int divisor1 = 2; divisor1 <= Math.sqrt(number); divisor1++) {
            int divisor2 = number / divisor1;
            if (divisor1 * divisor2 == number) {
                divisors.add(divisor1);
                divisors.add(divisor2);
            }
        }

        return divisors.stream().mapToInt(i -> i).toArray();
    }
}
