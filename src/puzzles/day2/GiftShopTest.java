package puzzles.day2;

import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class GiftShopTest {

    GiftShop gs = new GiftShop("test.txt");

    @Test
    void testIsInvalid() {

        assertTrue(gs.isInvalid(123123));

    }
}
