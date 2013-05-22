package ch.mse.osf.gitification.benchmark.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.http.client.ClientProtocolException;

import ch.mse.osf.gitification.benchmark.Benchmark;
import ch.mse.osf.gitification.helper.APIGitification;
import ch.mse.osf.gitification.helper.DummyFactory;
import ch.mse.osf.gitification.model.Application;
import ch.mse.osf.gitification.model.Badge;
import ch.mse.osf.gitification.model.EventType;
import ch.mse.osf.gitification.model.User;

public class CreateEventBenchmark extends Benchmark {

	private int nbEvents = 0;


	public CreateEventBenchmark(int nbEvents) throws IOException {
		super();
		this.nbEvents = nbEvents;	
	}

	@Override
	public void benchmark() throws ClientProtocolException, IOException {
		APIGitification.getApplications();
		Application app = APIGitification.postApplication(DummyFactory
				.application());
		User user = APIGitification.postUser(app, DummyFactory.user());

		EventType eventType = APIGitification.postEventType(app,
				DummyFactory.eventType());

		Badge badge = APIGitification.postBadge(app, DummyFactory.badge());

		APIGitification.postRule(app, DummyFactory.rule(badge, eventType, 6));

		long start, end;
		for (int i = 0; i < nbEvents; i++) {
			start = System.nanoTime();
			APIGitification.postEvent(app, DummyFactory.event(user, eventType));
			end = System.nanoTime();
			out.println(i + "," + (end - start));
		}
		out.flush();
		out.close();
	}

}
