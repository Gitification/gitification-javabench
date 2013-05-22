package ch.mse.osf.gitification.javabench;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ch.mse.osf.gitification.benchmark.Benchmark;
import ch.mse.osf.gitification.benchmark.BenchmarkExecutor;
import ch.mse.osf.gitification.benchmark.impl.AllUsersBenchmark;
import ch.mse.osf.gitification.benchmark.impl.CreateEventBenchmark;
import ch.mse.osf.gitification.benchmark.impl.LeaderBoardBenchmark;
import ch.mse.osf.gitification.benchmark.impl.PingBenchmark;
import ch.mse.osf.gitification.benchmark.impl.UserBadgesBenchmark;
import ch.mse.osf.gitification.validator.BadgeAwardValidator;

public class JavaBench {

	public static void main(String[] args) throws Exception {
		//APIGitification.setBaseURL("http://example.com/api/");
		//Default is http://0.0.0.0:8080/api/

		//BadgeAwardValidator.validateBadgeAward(); // Don't skip this
		
		benchs(); 

	}

	public static void benchs() throws IOException {
		List<Benchmark> benchs = new ArrayList<Benchmark>();
		benchs.add(new CreateEventBenchmark(1000));
		//benchs.add(new LeaderBoardBenchmark(5,5));
		//benchs.add(new UserBadgesBenchmark(10));
		//benchs.add(new AllUsersBenchmark(10));
		benchs.add(new PingBenchmark(1000));
		BenchmarkExecutor.execute(benchs);
	}
}
