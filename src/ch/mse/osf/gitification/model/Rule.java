package ch.mse.osf.gitification.model;

import java.util.ArrayList;
import java.util.List;

public class Rule {

	private String name;
	private String badge_id;
	private String rule_id;

	List<EventTypes> event_types = new ArrayList<EventTypes>();

	public Rule() {
		event_types.add(new EventTypes());
	}

	public class EventTypes {
		private String event_type;
		private int threshold;

		public String getEvent_type() {
			return event_type;
		}

		public void setEvent_type(String event_type) {
			this.event_type = event_type;
		}

		public int getThreshold() {
			return threshold;
		}

		public void setThreshold(int threshold) {
			this.threshold = threshold;
		}

	}

	public String getRule_id() {
		return rule_id;
	}

	public void setRule_id(String rule_id) {
		this.rule_id = rule_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBadge_id() {
		return badge_id;
	}

	public void setBadge_id(String badge_id) {
		this.badge_id = badge_id;
	}

	public List<EventTypes> getEvent_types() {
		return event_types;
	}

	public void setEvent_types(List<EventTypes> eventypes) {
		this.event_types = eventypes;
	}

}
