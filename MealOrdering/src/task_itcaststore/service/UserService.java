package task_itcaststore.service;

import org.jetbrains.annotations.NotNull;
import task_itcaststore.dao.UserDao;
import task_itcaststore.domain.User;
import task_itcaststore.exception.ActiveUserException;
import task_itcaststore.exception.RegisterException;
import task_itcaststore.utils.MailUtils;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.Date;

/**
 * 用户的服务类
 */
public class UserService {
	private UserDao dao = new UserDao();

	/**
	 * 用户注册。
	 * TODO 提取常量
	 */
	public void register(User user) throws RegisterException {
		//调用dao完成注册操作
		try {
			dao.addUser(user);
			//发送激活邮件
			String href = "http://localhost:8080/bookstore/doActive?activeCode=" + user.getActiveCode();
			String emailFrom = "itcast_duhong@sohu.com";
			String emailTo = user.getEmail();
			String subject = "用户注册";
			String content = "<b>这是一封激活邮件</b>" +
					"<p>" +
					"感谢您注册网上书城，请点击：<a href='" + href + "'>&nbsp;激活&nbsp;</a>后使用。<br/>" +
					"为保障您的账户安全，请在24小时内完成激活操作。" +
					"</p>";

			MailUtils.sendMail(emailFrom, emailTo, subject, content);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("警告：注册失败！");
		}
	}

	/**
	 * 激活用户。
	 */
	public void activeUser(String activeCode) throws ActiveUserException {
		try {
			//根据激活码查找用户
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null)
				throw new ActiveUserException("警告：激活用户失败！");

			//判断激活码是否过期 24小时内激活有效.
			//1.得到注册时间
			Date registTime = user.getRegistTime();
			//2.判断是否超时
			long time = System.currentTimeMillis() - registTime.getTime();
			if (time / 1000 / 60 / 60 > 24)
				throw new ActiveUserException("警告：激活码过期！");

			//激活用户，也就是修改用户的state状态
			dao.activeUser(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("警告：激活用户失败！");
		}
	}

	/**
	 * 用户登录。
	 */
	public User login(String userName, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUserNameAndPassword(userName, password);
			//如果找到，还需要确定用户是否为激活用户
			if (user == null) {
				throw new LoginException("警告：用户名或密码错误！");
			} else {
				//只有是激活才能登录成功，否则提示“用户未激活”
				if (user.getState() == 1) {
					return user;
				}
				throw new LoginException("警告：用户未激活！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("警告：登录失败！");
		}
	}

	/**
	 * 编辑用户信息。
	 */
	public void editUser(@NotNull User user) {
		//TODO

	}
}
