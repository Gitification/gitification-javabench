package ch.mse.osf.gitification.benchmark.impl;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import ch.mse.osf.gitification.benchmark.Benchmark;
import ch.mse.osf.gitification.helper.APIGitification;

public class PingBenchmark extends Benchmark {

	private int nbPing = 0;

	public PingBenchmark(int nbPing)
			throws IOException {
		super();
		this.nbPing = nbPing;
	}

	@Override
	public void benchmark() throws ClientProtocolException, IOException {

		long start, end;
		for (int i = 0; i < nbPing; i++) {
			start = System.nanoTime();
			APIGitification.getPing();
			end = System.nanoTime();
			out.println(i + "," + (end - start));
			out.flush();
		}
		out.close();
	}

}
