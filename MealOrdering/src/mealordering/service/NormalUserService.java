package mealordering.service;

import dk_breeze.exception.NotImplementedException;
import mealordering.dao.DaoFactory;
import mealordering.dao.NormalUserDao;
import mealordering.domain.BeanPage;
import mealordering.domain.NormalUser;
import mealordering.exception.ActiveException;
import mealordering.exception.LoginException;
import mealordering.exception.RegisterException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户的服务类
 */
public class NormalUserService {
	private NormalUserDao dao = DaoFactory.getNormalUserDao();

	/**
	 * 注册用户。
	 * @param user 用户信息
	 */
	public void doRegister(@NotNull NormalUser user) throws RegisterException {
		try {
			dao.doRegister(user);

//			//TODO 提取参数
//			String href = "http://localhost:8080/mealordering/doActive?activeCode=" + user.getActiveCode();
//			String fromEmail = "dk_breeze@qq.com";
//			String toEmail = user.getEmail();
//			String subject = "用户注册";
//			String content = f("<b>这是一封激活邮件</b>\n<p>感谢您注册网上书城，请点击：\n<a href='{0}'>&nbsp;激活&nbsp;</a>后使用。\n<br/>为保障您的账户安全，请在24小时内完成激活操作。</p>", href);
//			String[] authInfo = new String[]{"dk_breeze@qq.com","......"};
//
//			dk_breeze.utils.MailUtils.sendHTML(fromEmail,toEmail,subject,content,authInfo,null);
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
			NormalUser user = dao.findByActiveCode(activeCode);
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
	public void doEdit(@NotNull NormalUser user) {
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
	public NormalUser loginByUserNameAndPassword(String userName, String password) throws LoginException {
		try {
			//根据输入的用户名和密码查询用户
			NormalUser user = dao.loginByUserNameAndPassword(userName, password);
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
	public NormalUser loginByEmailAndPassword(String email, String password) throws LoginException {
		try {
			//根据输入的邮箱地址和密码查询用户
			NormalUser user = dao.loginByEmailAndPassword(email, password);
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
	public NormalUser loginByPhoneNumAndCheckCode(int phoneNum) {
		throw new NotImplementedException();
	}


	/**
	 * 根据用户Id查询用户。
	 * @param id 用户Id
	 */
	public NormalUser findById(int id) {
		NormalUser user = null;
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
	public NormalUser findByUserName(@NotNull String userName) {
		NormalUser user = null;
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
	public BeanPage<NormalUser> findAllInPage(int pageIndex, int count) {
		BeanPage<NormalUser> userPage = null;
		try {
			List<NormalUser> userList = dao.findAll();
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
	public BeanPage<NormalUser> searchByUserNameInPage(@NotNull String searchField, int pageIndex, int count) {
		BeanPage<NormalUser> userPage = null;
		try {
			List<NormalUser> userList = dao.searchByUserName(searchField);
			userPage = new BeanPage<>(pageIndex, count, userList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userPage;
	}
}
