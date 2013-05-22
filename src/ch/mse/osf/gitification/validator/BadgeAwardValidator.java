package ch.mse.osf.gitification.validator;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.mse.osf.gitification.helper.APIGitification;
import ch.mse.osf.gitification.helper.DummyFactory;
import ch.mse.osf.gitification.model.Application;
import ch.mse.osf.gitification.model.Badge;
import ch.mse.osf.gitification.model.EventType;
import ch.mse.osf.gitification.model.User;

public class BadgeAwardValidator {

	private static final Logger log = LoggerFactory.getLogger(BadgeAwardValidator.class);
	
	/**
	 * Validate that the server awards badge more or less correctly
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	public static void validateBadgeAward() throws ClientProtocolException,
			IOException, Exception {
		log.info("Validating badge award system (long process)");
		for (int j = 1; j < 3; j++) {
			valideBadgeAward();
		}
		log.info("Badge award system seems to work");
	}

	private static void valideBadgeAward() throws Exception {
		Application app = APIGitification.postApplication(DummyFactory
				.application());
		User user = APIGitification.postUser(app, DummyFactory.user());

		EventType eventType = APIGitification.postEventType(app,
				DummyFactory.eventType());
		Badge badge = APIGitification.postBadge(app, DummyFactory.badge());
		APIGitification.postRule(app,
				DummyFactory.rule(badge, eventType, 6));

		Badge badge2 = APIGitification.postBadge(app, DummyFactory.badge());
		APIGitification.postRule(app,
				DummyFactory.rule(badge2, eventType, 7));

		Badge badge3 = APIGitification.postBadge(app, DummyFactory.badge());
		EventType eventType2 = APIGitification.postEventType(app,
				DummyFactory.eventType());
		APIGitification.postRule(app,
				DummyFactory.rule(badge3, eventType2, 10));

		Badge badge4 = APIGitification.postBadge(app, DummyFactory.badge());
		EventType eventType4 = APIGitification.postEventType(app,
				DummyFactory.eventType());
		APIGitification.postRule(app,
				DummyFactory.rule(badge4, eventType4, 10));

		for (int i = 0; i < 6; i++) {
			APIGitification.postEvent(app,
					DummyFactory.event(user, eventType));
			APIGitification.postEvent(app,
					DummyFactory.event(user, eventType2));
		}
		for (int i = 0; i < 10; i++) {
			APIGitification.postEvent(app,
					DummyFactory.event(user, eventType4));
		}
		
		List<Badge> badges = APIGitification.getUserBadges(app, user);

		if (!badges.contains(badge))
			throw new Exception("Badge not awarded whereas it should be "
					+ badge);
		if (badges.contains(badge2))
			throw new Exception("Badge awarded whereas it should not be "
					+ badge2);
		if (badges.contains(badge3))
			throw new Exception("Badge awarded whereas it should not be "
					+ badge3);
		if (!badges.contains(badge4))
			throw new Exception("Badge not awarded whereas it should be "
					+ badge4);

	}

}
