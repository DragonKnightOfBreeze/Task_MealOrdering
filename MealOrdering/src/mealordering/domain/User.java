/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.domain;

import java.io.Serializable;

public class User implements Serializable {
	/** 用户编号 */
	protected int id;
	/** 用户名称 */
	protected String userName;
	/** 用户密码 */
	protected String password;
	/** 用户类型 */
	protected String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
