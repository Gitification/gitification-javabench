package ch.mse.osf.gitification.model;

public class Badge {

	private String name;
	private String icon;
	private String category_id;
	private String badge_id;
	private String application_id;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Badge))
			return false;
		Badge badgeB = (Badge) obj;
		if (this.getBadge_id().equals(badgeB.getBadge_id()))
			return true;
		return false;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getBadge_id() {
		return badge_id;
	}

	public void setBadge_id(String badge_id) {
		this.badge_id = badge_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

}
