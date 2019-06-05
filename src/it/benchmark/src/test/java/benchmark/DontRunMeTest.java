package benchmark;

import org.junit.Test;

public class DontRunMeTest {
    @Test
    public void dontRunThis() throws Exception {
        throw new Exception("Normal tests should not be run with benchmarks.");
    }
}
