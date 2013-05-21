package ch.mse.osf.gitification.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.mse.osf.gitification.model.Application;
import ch.mse.osf.gitification.model.Badge;
import ch.mse.osf.gitification.model.Event;
import ch.mse.osf.gitification.model.EventType;
import ch.mse.osf.gitification.model.Rule;
import ch.mse.osf.gitification.model.User;

public class DummyFactory {
		

	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	public static Application application(){
		Application app = new Application();
		app.setAdmin("blu@blu.com");
		app.setSite("http://blu.com");
		app.setCallback("http://blu.com");
		return app;
	}
	
	public static User user(){
		User user = new User();
		user.setLogin("bblublu");
		user.setFirstname("dfad");
		user.setLastname("afdasd");
		user.setEmail("blu@blu.com");
		return user;		
	}
	
	
	public static EventType eventType(){
		EventType et = new EventType();
		et.setName("dblutype");
		return et;
	}
	
	public static Badge badge(){
		Badge badge = new Badge();
		badge.setName("badblu");
		badge.setIcon("http://ba.com/df.png");
		badge.setCategory_id("none");
		return badge;
	}
	
	public static Rule rule(Badge badge, EventType eventType, int threshold){
		Rule rule = new Rule();
		rule.setName("darule");
		rule.setBadge_id(badge.getBadge_id());
		rule.getEvent_types().get(0).setEvent_type(eventType.getType_id());
		rule.getEvent_types().get(0).setThreshold(threshold);
		return rule;
	}
	
	public static Event event(User user, EventType eventType){
		Event event = new Event();
		event.setType(eventType.getType_id());
		event.setUser(user.getUser_id());
		event.setIssued(df.format(new Date()));
		return event;
	}


}
