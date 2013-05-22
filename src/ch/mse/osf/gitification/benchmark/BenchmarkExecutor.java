package ch.mse.osf.gitification.benchmark;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BenchmarkExecutor {
	private static final Logger log = LoggerFactory.getLogger(BenchmarkExecutor.class);
	
	public static void execute(List<Benchmark> benchmarks) {
		for(Benchmark benchmark : benchmarks){
			log.info("Start benchmark: " + benchmark.getClass().getName());
			if(!benchmark.callBenchmark()) continue;
			log.info("End benchmark: " + benchmark.getClass().getName());
		}
		
	}

}
