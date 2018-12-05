package task_itcaststore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jetbrains.annotations.NotNull;
import task_itcaststore.domain.User;
import task_itcaststore.utils.DataSourceUtils;

import java.sql.SQLException;

public class UserDao {
	/**
	 * 添加用户。
	 */
	public void addUser(@NotNull User user) throws SQLException {
		String sql = "insert into users(userName,password,gender,email,telephone,introduce,activeCode) values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, user.getUserName(), user.getPassword(),
				user.getGender(), user.getEmail(), user.getTelephone(),
				user.getIntroduce(), user.getActiveCode());
		if (row == 0) {
			throw new RuntimeException();
		}
	}

	/**
	 * 根据激活码查找用户。
	 */
	public User findUserByActiveCode(@NotNull String activeCode) throws SQLException {
		String sql = "select * from users where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(User.class), activeCode);

	}

	/**
	 * 根据用户名与密码查找用户。
	 */
	public User findUserByUserNameAndPassword(@NotNull String userName, @NotNull String password) throws SQLException {
		String sql = "select * from users where userName=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(User.class), userName, password);
	}

	/**
	 * 根据激活码，激活对应的用户。
	 */
	public void activeUser(@NotNull String activeCode) throws SQLException {
		String sql = "update users set state=? where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, 1, activeCode);
	}

	/**
	 * 编辑用户信息。
	 */
	public void updateUser(@NotNull User user) {
		//TODO

	}
}
