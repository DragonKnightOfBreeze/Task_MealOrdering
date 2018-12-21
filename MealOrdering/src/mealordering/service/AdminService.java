package mealordering.service;

import mealordering.dao.AdminDao;
import mealordering.dao.DaoFactory;
import mealordering.domain.Admin;
import mealordering.exception.UserNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class AdminService {
	private AdminDao dao = DaoFactory.getAdminDao();

	/**
	 * 登录管理员。
	 */
	public Admin loginAdmin(@NotNull String userName, @NotNull String password)
	throws SQLException, UserNotFoundException {
		Admin admin = dao.loginAdmin(userName, password);
		if(admin == null)
			throw new UserNotFoundException();
		return admin;
	}
}
