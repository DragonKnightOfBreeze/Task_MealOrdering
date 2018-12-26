package mealordering.web.servlet;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.User;
import mealordering.exception.UserNotActiveException;
import mealordering.exception.UserNotFoundException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 登录的Servlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/mealordering/login"})
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String rememberLogin = req.getParameter("rememberLogin");

		try {
			//STEP 后台操作
			User user = ServiceFactory.getNormalUserSvc().loginByUserNameAndPassword(userName, password);
			if(StringExt.equals(rememberLogin, "true")) {
				resp.addCookie(new Cookie("userId", Integer.toString(user.getId())));
			}
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("onlineUser", user);
			req.setAttribute("msg_login", "登录成功！");
			resp.sendRedirect(req.getContextPath() + "/mealordering/index");
		} catch(UserNotFoundException e) {
			e.printStackTrace();
			req.setAttribute("msg_login", "用户不存在！");
			resp.sendRedirect(req.getContextPath() + "/mealordering/index");
		} catch(UserNotActiveException e) {
			e.printStackTrace();
			req.setAttribute("msg_login", "用户未激活！");
			resp.sendRedirect(req.getContextPath() + "/mealordering/index");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
