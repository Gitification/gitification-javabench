package ch.mse.osf.gitification.benchmark.impl;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import ch.mse.osf.gitification.benchmark.Benchmark;
import ch.mse.osf.gitification.helper.APIGitification;
import ch.mse.osf.gitification.helper.DummyFactory;
import ch.mse.osf.gitification.model.Application;

public class AllUsersBenchmark extends Benchmark {

	private int nbUsers = 0;

	public AllUsersBenchmark(int nbUsers)
			throws IOException {
		super();
		this.nbUsers = nbUsers;
	}

	@Override
	public void benchmark() throws ClientProtocolException, IOException {
		APIGitification.getApplications();
		Application app = APIGitification.postApplication(DummyFactory
				.application());

		long start, end;
		for (int i = 0; i < nbUsers; i++) {
			APIGitification.postUser(app, DummyFactory.user());
			start = System.nanoTime();
			APIGitification.getUsers(app);
			end = System.nanoTime();
			out.println(i + "," + (end - start));
			out.flush();
		}

		out.close();
	}

}
