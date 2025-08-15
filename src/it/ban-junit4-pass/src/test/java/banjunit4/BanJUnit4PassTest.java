package banjunit4;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Suite
public class BanJUnit4PassTest {
    @Test
    public void thisFails() {
        assertTrue(true);
    }
}
