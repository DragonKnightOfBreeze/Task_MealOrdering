/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.service;

import mealordering.dao.AdminDao;
import mealordering.dao.DaoFactory;
import mealordering.domain.Admin;
import mealordering.exception.LoginException;
import org.jetbrains.annotations.NotNull;

public class AdminService {
	private AdminDao dao = DaoFactory.getAdminDao();

	/**
	 * 登录管理员。
	 */
	public Admin loginAdmin(@NotNull String userName, @NotNull String password) {
		try {
			Admin admin = dao.loginAdmin(userName, password);
			if(admin == null)
				throw new LoginException("警告：名字或密码错误！");
			return admin;
		} catch(Exception e) {
			e.printStackTrace();
			throw new LoginException("登录失败！");
		}
	}
}
