/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.dao;

import mealordering.domain.Admin;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class AdminDao {
	AdminDao() {}

	/**
	 * 登录管理员。
	 */
	public Admin loginAdmin(@NotNull String userName, @NotNull String password) throws SQLException {
		String sql = "select (id,userName,password) from User where userName=? and password=? and type='管理员'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			Admin admin = new Admin(rs.getString(2), rs.getString(3));
			admin.setId(rs.getInt(1));
			return admin;
		}, userName, password);
	}

	/**
	 * 根据用户名称查询管理员。
	 */
	public Admin findByUserName(@NotNull String userName) throws SQLException {
		String sql = "select (id,userName,password,type) from User where userName=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			Admin admin = new Admin(rs.getString(2), rs.getString(3));
			admin.setId(rs.getInt(1));
			return admin;
		}, userName);
	}
}
