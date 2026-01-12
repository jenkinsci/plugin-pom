package banjunit4;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.Suite;
import org.apache.commons.lang3.time.DateUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Suite(failIfNoTests = false)
public class BanCommonsLangPassTest {
    @Test
    public void thisFails() {
        assertTrue(true);
    }
}
