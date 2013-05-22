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

public class UserBadgesBenchmark extends Benchmark {

	private int nbBadge = 0;

	public UserBadgesBenchmark(int nbBadges)
			throws IOException {
		super();
		this.nbBadge = nbBadges;
	}

	public void attributeBadge(Application app, User user)
			throws ClientProtocolException, IOException {
		EventType eventType = APIGitification.postEventType(app,
				DummyFactory.eventType());

		Badge badge = APIGitification.postBadge(app, DummyFactory.badge());

		APIGitification.postRule(app, DummyFactory.rule(badge, eventType, 1));

		APIGitification.postEvent(app, DummyFactory.event(user, eventType));
	}

	@Override
	public void benchmark() throws ClientProtocolException, IOException {
		APIGitification.getApplications();
		Application app = APIGitification.postApplication(DummyFactory
				.application());
		User user = APIGitification.postUser(app, DummyFactory.user());
		
		long start, end;
		for (int i = 0; i < nbBadge; i++) {
			attributeBadge(app, user);
			start = System.nanoTime();
			APIGitification.getUserBadges(app, user);
			end = System.nanoTime();
			out.println(i + "," + (end - start));
			out.flush();
		}

		out.close();
	}

}
