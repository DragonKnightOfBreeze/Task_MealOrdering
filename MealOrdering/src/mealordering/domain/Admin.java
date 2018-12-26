/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.domain;

import mealordering.enums.Identity;

import java.beans.JavaBean;

/**
 * 管理员的实体类
 */
@JavaBean
public class Admin extends User {
	public Admin(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.type = Identity.admin.toString();
	}
}
