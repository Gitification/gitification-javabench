package ch.mse.osf.gitification.helper;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.mse.osf.gitification.model.Application;
import ch.mse.osf.gitification.model.Badge;
import ch.mse.osf.gitification.model.Event;
import ch.mse.osf.gitification.model.EventType;
import ch.mse.osf.gitification.model.Rule;
import ch.mse.osf.gitification.model.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class APIGitification {
	private static ObjectMapper mapper = new ObjectMapper();
	private static String baseURL = "http://0.0.0.0:8080/api/";
	
	private static final Logger log = LoggerFactory.getLogger(APIGitification.class);


	public static String getBaseURL() {
		return baseURL;
	}

	public static void setBaseURL(String baseURL) {
		APIGitification.baseURL = baseURL;
	}

	public static Application postApplication(Application app)
			throws ClientProtocolException, IOException {
		log.debug("**** POST APPLICATION ****");
		String content = HTTPRequester.post(baseURL + "applications",
				mapper.writeValueAsString(app));
		String appid = mapper.readTree(content).path("payload")
				.path("application_id").textValue();
		log.debug(appid);
		app.setApplication_id(appid);
		return app;
	}

	public static void getApplications() throws ClientProtocolException, IOException {
		String responseBody = HTTPRequester.get(baseURL + "applications");
		log.debug(responseBody);
	}

	public static void getUser(Application app, User user)
			throws ClientProtocolException, IOException {
		log.debug("**** GET USER ******");
		String responseBody = HTTPRequester.get(baseURL + "applications/"
				+ app.getApplication_id() + "/users/" + user.getUser_id());
		log.debug(baseURL + "applications/"
				+ app.getApplication_id() + "/users/" + user.getUser_id());
		log.debug(responseBody);
	}
	
	public static void getUsers(Application app)
			throws ClientProtocolException, IOException {
		log.debug("**** GET USERS ******");
		String responseBody = HTTPRequester.get(baseURL + "applications/"
				+ app.getApplication_id() + "/users");
		log.debug(responseBody);
	}
	
	public static List<Badge> getUserBadges(Application app, User user)
			throws ClientProtocolException, IOException {
		log.debug("**** GET USER ******");
		String responseBody = HTTPRequester.get(baseURL + "applications/"
				+ app.getApplication_id() + "/users/" + user.getUser_id()+ "/badges");
		log.debug(responseBody);
		List<Badge> badges = mapper.readValue(mapper.readTree(responseBody).path("badges_list").traverse(), new TypeReference<List<Badge>>() {});
		return badges;
	}
	
	public static void getFindLeaderBoard(Application app) throws ClientProtocolException, IOException{
		log.debug("**** GET FINDLEADERBOARD ******");
		String responseBody = HTTPRequester.get(baseURL + "applications/"
				+ app.getApplication_id() + "/leaderboard");
		log.debug(responseBody);
	}


	public static User postUser(Application app, User user) throws ClientProtocolException,
			IOException {
		log.debug("**** POST USER ****");
		String content = HTTPRequester.post(baseURL + "applications/" + app.getApplication_id()
				+ "/users", mapper.writeValueAsString(user));
		String userid = mapper.readTree(content).path("payload")
				.path("user_id").textValue();
		log.debug(userid);
		user.setUser_id(userid);
		return user;
	}

	public static EventType postEventType(Application app, EventType eventType)
			throws ClientProtocolException, IOException {
		log.debug("**** POST EVENTTYPE ****");
		String content = HTTPRequester.post(baseURL + "applications/" + app.getApplication_id()
				+ "/events/types", mapper.writeValueAsString(eventType));
		String typeid = mapper.readTree(content).path("payload")
				.path("type_id").textValue();
		log.debug(typeid);
		eventType.setType_id(typeid);
		return eventType;
	}

	public static Badge postBadge(Application app, Badge badge) throws ClientProtocolException,
			IOException {
		log.debug("**** POST BADGE ****");
		String content = HTTPRequester.post(baseURL + "applications/" + app.getApplication_id()
				+ "/badges", mapper.writeValueAsString(badge));
		String badgeid = mapper.readTree(content).path("payload")
				.path("badge_id").textValue();
		log.debug(badgeid);
		badge.setBadge_id(badgeid);
		return badge;
	}

	public static Rule postRule(Application app, Rule rule)
			throws ClientProtocolException, IOException {
		log.debug("**** POST RULE ****");
		String content = HTTPRequester.post(baseURL + "applications/" + app.getApplication_id()
				+ "/rules", mapper.writeValueAsString(rule));
		String ruleid = mapper.readTree(content).path("payload")
				.path("rule_id").textValue();
		log.debug(ruleid);
		rule.setRule_id(ruleid);
		return rule;
	}

	public static Event postEvent(Application app, Event event)
			throws ClientProtocolException, IOException {
		log.debug("**** POST EVENT ****");
		String content = HTTPRequester.post(baseURL + "applications/" + app.getApplication_id()
				+ "/events", mapper.writeValueAsString(event));
		String eventid = mapper.readTree(content).path("payload")
				.path("event_id").textValue();
		log.debug(eventid);
		event.setEvent_id(eventid);
		return event;
	}
}
