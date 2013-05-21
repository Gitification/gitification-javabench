package ch.mse.osf.gitification.benchmark.impl;

import java.io.IOException;

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

	public CreateEventBenchmark(int nbEvents) {
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

		for (int i = 0; i < nbEvents; i++) {
			APIGitification.postEvent(app, DummyFactory.event(user, eventType));
		}

	}

}
