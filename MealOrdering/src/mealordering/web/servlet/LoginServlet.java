package mealordering.web.servlet;

import mealordering.domain.User;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录的Servlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/mealordering/login"})
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		//声明返回验证参数
		boolean validateStatus = true;

		//如果密码为空，则只验证用户名是否存在，否则验证密码是否正确
		try {
			if(password == null) {
				ServiceFactory.getNormalUserSvc().findByUserName(userName);
			} else {
				User user = ServiceFactory.getNormalUserSvc().loginByUserNameAndPassword(userName, password);
				//如果查找到用户，则将用户信息存储到session中
				req.getSession().setAttribute("user", user);
			}
		} catch(Exception e) {
			e.printStackTrace();
			validateStatus = false;
		}

		resp.getWriter().println(validateStatus);
	}
}
