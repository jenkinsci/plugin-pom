package benchmark;

import java.io.IOException;
import jenkins.benchmark.jmh.JmhBenchmark;
import jenkins.benchmark.jmh.JmhBenchmarkState;
import org.openjdk.jmh.annotations.Benchmark;

@JmhBenchmark
public class SampleBenchmark {
    public static class MyState extends JmhBenchmarkState {}

    @Benchmark
    public void benchmark(MyState state) throws IOException {
        state.getJenkins().setSystemMessage("Hello world");
    }
}
