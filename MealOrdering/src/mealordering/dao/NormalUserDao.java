package mealordering.dao;

import dk_breeze.exception.NotImplementedException;
import mealordering.domain.NormalUser;
import mealordering.exception.UserNotFoundException;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

import static dk_breeze.utils.ext.StringExt.f;

/**
 * 普通用户的Dao类
 */
public class NormalUserDao {

	NormalUserDao() {
	}

	/**
	 * 注册用户。
	 */
	public void doRegister(@NotNull NormalUser user) throws SQLException, UserNotFoundException {
		String sql = "insert into User(userName,password,imgUrl,gender,email,phoneNum,introduce,activeCode)" +
				" value(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,
				user.getUserName(), user.getPassword(), user.getImgUrl(), user.getGender(), user.getEmail(),
				user.getPhoneNum(), user.getIntroduce(), user.getActiveCode()
		);
		if(row == 0)
			throw new UserNotFoundException();
	}

	/**
	 * 激活用户。
	 */
	public void doActive(@NotNull String activeCode) throws SQLException, UserNotFoundException {
		String sql = "update User set activeState=? where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql,
				1, activeCode);
		if(row == 0)
			throw new UserNotFoundException();
	}

	/**
	 * 编辑用户信息。
	 */
	public void doEdit(@NotNull NormalUser user) throws SQLException {
		String sql = "update User set userName=?,password=?,gender=?,imgUrl=?,email=?,phoneNum=?,introduce=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql,
				user.getUserName(), user.getPassword(), user.getGender(), user.getImgUrl(), user.getEmail(),
				user.getPhoneNum(), user.getIntroduce(),
				user.getId()
		);
	}

	/**
	 * 编辑用户密码。
	 */
	public void doEditPassword(int id, @NotNull String password) throws SQLException {
		String sql = "update User set password=? where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, password, id);
	}

	/**
	 * 根据Id删除用户。
	 */
	public void doDeleteById(int id) throws SQLException {
		String sql = "delete from User where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		int row = runner.update(sql, id);
		if(row == 0)
			throw new RuntimeException();
	}

	/**
	 * 根据用户名和密码登录用户。
	 */
	public NormalUser loginByUserNameAndPassword(@NotNull String userName, @NotNull String password) throws SQLException {
		String sql = "select * from User where userName=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(NormalUser.class), userName, password);
	}

	/**
	 * 根据用户邮箱和密码登录用户。
	 */
	public NormalUser loginByEmailAndPassword(@NotNull String email, @NotNull String password) throws SQLException {
		String sql = "select * from User where email=? and password=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(NormalUser.class), email, password);
	}

	/**
	 * TODO 根据用户手机号码和验证码登录用户。
	 */
	public NormalUser loginByPhoneNumAndCheckCode(int phoneNum) {
		throw new NotImplementedException();
	}

	/**
	 * 根据用户Id查询用户。
	 */
	public NormalUser findById(int id) throws SQLException {
		String sql = "select * from User where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(NormalUser.class), id);
	}

	/**
	 * 根据用户名称查询用户。
	 */
	public NormalUser findByUserName(@NotNull String userName) throws SQLException {
		String sql = "select * from User where userName=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(NormalUser.class), userName);
	}

	/**
	 * 根据激活码查询用户。
	 */
	public NormalUser findByActiveCode(@NotNull String activeCode) throws SQLException {
		String sql = "select * from User where activeCode=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(NormalUser.class), activeCode);
	}

	/**
	 * 查询所有用户。
	 */
	public List<NormalUser> findAll() throws SQLException {
		String sql = "select * from User";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(NormalUser.class));
	}

	/**
	 * 根据用户名进行模糊搜索。
	 * @param userName 用户名
	 */
	public List<NormalUser> searchByUserName(@NotNull String userName) throws SQLException {
		String sql = f("select * from User where userName like '%{0}%'", userName);
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(NormalUser.class));
	}
}

