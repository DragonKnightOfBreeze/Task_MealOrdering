package mealordering.domain;

import java.beans.JavaBean;
import java.io.Serializable;
import java.util.Date;

/**
 * 通知的实体类
 */
@JavaBean
public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 通知编号 */
	private int id;
	/** 通知标题 */
	private String title;
	/** 通知内容 */
	private String details;
	/** 通知时间 */
	private Date time;

	public Notice() {}

	public Notice(String title, String details) {
		this.title = title;
		this.details = details;
		this.time = new Date();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
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
