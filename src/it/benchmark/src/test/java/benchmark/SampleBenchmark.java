package benchmark;

import jenkins.benchmark.jmh.JmhBenchmark;
import jenkins.benchmark.jmh.JmhBenchmarkState;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;

@JmhBenchmark
public class SampleBenchmark {
    public static class MyState extends JmhBenchmarkState {
    }

    @Benchmark
    public void benchmark(MyState state) throws IOException {
        state.getJenkins().setSystemMessage("Hello world");
    }
}
