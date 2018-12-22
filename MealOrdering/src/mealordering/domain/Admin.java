/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.domain;

import mealordering.enums.Identity;

/**
 * 管理员的实体类
 */
public class Admin extends User {
	public Admin(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.type = Identity.admin.toString();
	}
}
