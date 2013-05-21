package ch.mse.osf.gitification.javabench;

import java.util.ArrayList;
import java.util.List;

import ch.mse.osf.gitification.benchmark.Benchmark;
import ch.mse.osf.gitification.benchmark.BenchmarkExecutor;
import ch.mse.osf.gitification.benchmark.impl.CreateEventBenchmark;
import ch.mse.osf.gitification.validator.BadgeAwardValidator;

public class JavaBench {

	public static void main(String[] args) throws Exception {
		// APIGitification.setBaseURL("http://ks25416.kimsufi.com/api/");
		//Default is http://0.0.0.0:8080/api/

		BadgeAwardValidator.validateBadgeAward(); // Don't skip this
		
		benchs(); 

	}

	public static void benchs() {
		List<Benchmark> benchs = new ArrayList<Benchmark>();
		benchs.add(new CreateEventBenchmark(100));
		BenchmarkExecutor.execute(benchs);
	}
}
