package benchmark;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestBenchmark {
    @Test
    public void runThis() throws Exception {
        // create a temporary folder to show that this method has executed
        Path path = Paths.get("target/benchmark-run");
        Files.createDirectories(path);
    }
}
