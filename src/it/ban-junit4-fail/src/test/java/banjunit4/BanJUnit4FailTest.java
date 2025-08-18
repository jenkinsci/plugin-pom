package banjunit4;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;

public class BanJUnit4FailTest {
    @Test
    public void thisFails() {
        assertTrue(true);
        assertFalse(false);
    }
}
