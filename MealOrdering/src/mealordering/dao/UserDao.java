package mealordering.dao;

import dk_breeze.exception.ToDoException;
import mealordering.domain.User;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户的Dao类
 */
public class UserDao {

	UserDao() {
	}

	/**
	 * 注册用户。
	 */
	public void doRegister(@NotNull User user) throws SQLException {
		String sql = "insert into User(userName,password,gender,email,phoneNum,introduce,activeCode)" +
				" value(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,
				user.getUserName(), user.getPassword(), user.getGender(), user.getEmail(),
				user.getPhoneNum(), user.getIntroduce(), user.getActiveCode()
		);
		if (row == 0)
			throw new RuntimeException();
	}

	/**
	 * 激活用户。
	 */
	public void doActive(@NotNull String activeCode) throws SQLException {
		String sql = "update User set activeState=? where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,
				1, activeCode);
		if (row == 0)
			throw new RuntimeException();
	}

	/**
	 * 编辑用户信息。
	 */
	public void doEdit(@NotNull User user) throws SQLException {
		String sql = "update User set userName=?,password=?,gender=?,email=?,phoneNum=?,introduce=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,
				user.getUserName(), user.getPassword(), user.getGender(), user.getEmail(),
				user.getPhoneNum(), user.getIntroduce(),
				user.getId()
		);
		if (row == 0)
			throw new RuntimeException();
	}

	/**
	 * 根据删除用户。
	 */
	public void doDeleteById(int id) throws SQLException {
		String sql = "delete from User where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, id);
		if (row == 0)
			throw new RuntimeException();
	}


	/**
	 * 根据用户名和密码登录用户。
	 */
	public User loginByUserNameAndPassword(@NotNull String userName, @NotNull String password) throws SQLException {
		String sql = "select * from User where userName=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(User.class), userName, password);
	}

	/**
	 * 根据用户邮箱和密码登录用户。
	 */
	public User loginByEmailAndPassword(@NotNull String email, @NotNull String password) throws SQLException {
		String sql = "select * from User where email=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(User.class), email, password);
	}

	/**
	 * TODO 根据用户手机号码和验证码登录用户。
	 */
	public User loginByPhoneNumAndCheckCode(int phoneNum) {
		throw new ToDoException();
	}

	/**
	 * 根据用户Id查询用户。
	 */
	public User findById(int id) throws SQLException {
		String sql = "select * from User where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(User.class), id);
	}

	/**
	 * 根据用户名称查询用户。
	 */
	public User findByUserName(@NotNull String userName) throws SQLException {
		String sql = "select * from User where userName=?";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(User.class), userName);
	}

	/**
	 * 根据激活码查询用户。
	 */
	public User findByActiveCode(@NotNull String activeCode) throws SQLException {
		String sql = "select * from User where activeCode=?";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(User.class), activeCode);
	}

	/**
	 * 根据用户名进行模糊查询。
	 * @param searchField 搜索域
	 */
	public List<User> searchByUserName(@NotNull String searchField) throws SQLException {
		String sql = "select * from User where userName like '%" + searchField + "%'";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(User.class), searchField);
	}

	/**
	 * 查询所有用户。
	 */
	public List<User> findAll() throws SQLException {
		String sql = "select id,userName,gender,email,phoneNum,type,activeState,registerTime from User";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<User> userList = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setGender(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setPhoneNum(rs.getString(5));
				user.setType(rs.getString(6));
				user.setActiveState(rs.getInt(7));
				user.setRegisterTime(rs.getDate(8));
				userList.add(user);
			}
			return userList;
		});
	}
}

