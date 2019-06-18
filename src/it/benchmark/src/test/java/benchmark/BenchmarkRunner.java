package benchmark;

import jenkins.benchmark.jmh.BenchmarkFinder;
import org.junit.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class BenchmarkRunner {
    @Test
    public void runThis() throws Exception {
        // number of iterations is kept to a minimum just to verify that the benchmarks work without spending extra
        // time during builds.
        ChainedOptionsBuilder optionsBuilder =
                new OptionsBuilder()
                        .forks(1)
                        .warmupIterations(1)
                        .warmupBatchSize(1)
                        .measurementIterations(1)
                        .measurementBatchSize(1)
                        .shouldFailOnError(true)
                        .result("jmh-report.json")
                        .timeUnit(TimeUnit.MICROSECONDS)
                        .resultFormat(ResultFormatType.JSON);
        BenchmarkFinder finder = new BenchmarkFinder(getClass());
        finder.findBenchmarks(optionsBuilder);
        new Runner(optionsBuilder.build()).run();
    }
}
