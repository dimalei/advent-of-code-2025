package puzzles.day5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class CafeteriaTest {

    @Test
    void testIsOverlapping() {
        long[] rangeA = new long[] { 1, 2 };
        long[] rangeB = new long[] { 2, 3 };

        assertTrue(Cafeteria.isOverlapping(rangeA, rangeB));
        assertTrue(Cafeteria.isOverlapping(rangeB, rangeA));

        long[] rangeC = new long[] { 3, 4 };

        assertTrue(Cafeteria.isOverlapping(rangeA, rangeC));
        assertTrue(Cafeteria.isOverlapping(rangeC, rangeA));

        long[] rangeD = new long[] { 4, 5 };

        assertFalse(Cafeteria.isOverlapping(rangeA, rangeD));
        assertFalse(Cafeteria.isOverlapping(rangeD, rangeA));

        long[] rangeE = new long[] { 10, 18 };
        long[] rangeF = new long[] { 16, 20 };

        assertTrue(Cafeteria.isOverlapping(rangeE, rangeF));

        long[] rangeG = new long[] { 1, 100 };
        long[] rangeH = new long[] { 3, 5 };

        assertTrue(Cafeteria.isOverlapping(rangeG, rangeH));

    }

    @Test
    void testUnionRange() {
        long[] rangeA = new long[] { 1, 2 };
        long[] rangeB = new long[] { 2, 3 };

        long[] newRangeA = Cafeteria.unionRange(rangeA, rangeB);
        long[] newRangeB = Cafeteria.unionRange(rangeB, rangeA);

        assertEquals(1, newRangeA[0]);
        assertEquals(1, newRangeB[0]);
        assertEquals(3, newRangeA[1]);
        assertEquals(3, newRangeB[1]);
    }
}
