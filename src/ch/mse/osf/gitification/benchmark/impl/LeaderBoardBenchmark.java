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

public class LeaderBoardBenchmark extends Benchmark {

	private int nbUsers = 0;
	private int nbBadgesPerUser = 0;

	public LeaderBoardBenchmark(int nbUsers, int nbBadgesPerUser)
			throws IOException {
		super();
		this.nbUsers = nbUsers;
		this.nbBadgesPerUser = nbBadgesPerUser;
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

		long start, end;
		for (int i = 0; i < nbUsers; i++) {
			User user = APIGitification.postUser(app, DummyFactory.user());
			for (int j = 0; j < nbBadgesPerUser; j++) {
				attributeBadge(app, user);
			}
			start = System.nanoTime();
			APIGitification.getFindLeaderBoard(app);
			end = System.nanoTime();
			out.println(i + "," + (end - start));
			out.flush();
		}

		out.close();
	}

}
