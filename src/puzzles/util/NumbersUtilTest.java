package puzzles.util;

import java.util.Arrays;
import org.junit.Test;

public class NumbersUtilTest {

    @Test
    public void testFindDivisors() {

        System.out.println("hello");

        for (int i = 0; i < 100; i++) {

            String divisors = Arrays.toString(NumbersUtil.findDivisors(i));
            System.out.println(i + ": " + divisors);
        }

        System.out.println("end");

    }

}
