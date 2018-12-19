package mealordering.service;

import dk_breeze.exception.NotImplementedException;
import mealordering.dao.DaoFactory;
import mealordering.dao.UserDao;
import mealordering.domain.BeanPage;
import mealordering.domain.User;
import mealordering.exception.ActiveException;
import mealordering.exception.LoginException;
import mealordering.exception.RegisterException;
import mealordering.utils.MailUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户的服务类
 */
public class UserService {
	private UserDao dao = DaoFactory.getUserDao();

	/**
	 * 注册用户。
	 * @param user 用户信息
	 */
	public void doRegister(@NotNull User user) throws RegisterException {
		try {
			dao.doRegister(user);

			//必要的参数
			String href = "http://localhost:8080/mealordering/doActive?activeCode=" + user.getActiveCode();
			String emailFrom = "dk_breeze@qq.com";
			String emailTo = user.getEmail();
			String subject = "用户注册";
			String content = "<b>这是一封激活邮件</b>" +
					"<p>" +
					"感谢您注册网上书城，请点击：<a href='" + href + "'>&nbsp;激活&nbsp;</a>后使用。<br/>" +
					"为保障您的账户安全，请在24小时内完成激活操作。" +
					"</p>";

			MailUtils.sendMail(emailFrom, emailTo, subject, content);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RegisterException("警告：注册失败！");
		}
	}

	/**
	 * 激活用户。
	 * @param activeCode 激活码
	 * @param validHours 有效时间（小时）
	 */
	public void doActive(@NotNull String activeCode, int validHours) throws ActiveException {
		try {
			//根据激活码查询用户
			User user = dao.findByActiveCode(activeCode);
			if(user == null)
				throw new ActiveException("警告：激活失败！");

			//判断激活码是否过期
			long time = System.currentTimeMillis() - user.getRegisterTime().getTime();
			if(time / 1000 / 60 / 60 > 24)
				throw new ActiveException("警告：激活码过期！");

			//激活用户（即修改用户表的activeState字段）
			dao.doActive(activeCode);
		} catch(Exception e) {
			e.printStackTrace();
			throw new ActiveException("警告：激活失败！");
		}
	}

	/**
	 * 编辑用户信息。
	 * @param user 用户信息
	 */
	public void doEdit(@NotNull User user) {
		try {
			dao.doEdit(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户信息。
	 * @param id 用户Id
	 */
	public void doDelete(int id) {
		try {
			dao.doDeleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 根据用户名和密码登录用户。
	 * @param userName 用户名
	 * @param password 密码
	 */
	public User loginByUserNameAndPassword(String userName, String password) throws LoginException {
		try {
			//根据输入的用户名和密码查询用户
			User user = dao.loginByUserNameAndPassword(userName, password);
			if(user == null)
				throw new LoginException("警告：用户名或密码错误！");
				//判断用户是否已激活
			else if(user.getActiveState() == 0)
				throw new LoginException("警告：用户未激活！");

			return user;
		} catch(SQLException e) {
			e.printStackTrace();
			throw new LoginException("警告：登录失败！");
		}
	}

	/**
	 * 根据用户邮箱和密码登录用户。
	 * @param email 用户邮箱地址
	 * @param password 密码
	 */
	public User loginByEmailAndPassword(String email, String password) throws LoginException {
		try {
			//根据输入的邮箱地址和密码查询用户
			User user = dao.loginByEmailAndPassword(email, password);
			if(user == null)
				throw new LoginException("警告：用户邮箱地址或密码错误！");
				//判断用户是否已激活
			else if(user.getActiveState() == 0)
				throw new LoginException("警告：用户未激活！");

			return user;
		} catch(SQLException e) {
			e.printStackTrace();
			throw new LoginException("警告：登录失败！");
		}
	}

	/**
	 * TODO 根据用户手机号码和验证码登录用户。
	 * @param phoneNum 用户手机号码
	 */
	public User loginByPhoneNumAndCheckCode(int phoneNum) {
		throw new NotImplementedException();
	}


	/**
	 * 根据用户Id查询用户。
	 * @param id 用户Id
	 */
	public User findById(int id) {
		User user = null;
		try {
			user = dao.findById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 根据用户名查询用户。
	 * @param userName 用户名
	 */
	public User findByUserName(@NotNull String userName) {
		User user = null;
		try {
			user = dao.findByUserName(userName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 查询所有用户。
	 */
	public BeanPage<User> findAllInPage(int pageIndex, int count) {
		BeanPage<User> userPage = null;
		try {
			List<User> userList = dao.findAll();
			userPage = new BeanPage<>(pageIndex, count, userList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userPage;
	}

	/**
	 * 根据用户名进行模糊查询。
	 * @param searchField 搜索域
	 */
	public BeanPage<User> searchByUserNameInPage(@NotNull String searchField, int pageIndex, int count) {
		BeanPage<User> userPage = null;
		try {
			List<User> userList = dao.searchByUserName(searchField);
			userPage = new BeanPage<>(pageIndex, count, userList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userPage;
	}
}
