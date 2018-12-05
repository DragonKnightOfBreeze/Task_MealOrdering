package mealordering.domain;

/**
 * 通知的实体类
 */
public class Notice {
	/** 通知编号 */
	private int id;
	/** 通知标题 */
	private String title;
	/** 通知内容 */
	private String details;
	/** 通知时间 */
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
