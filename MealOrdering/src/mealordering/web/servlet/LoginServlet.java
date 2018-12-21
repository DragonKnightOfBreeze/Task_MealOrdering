package mealordering.web.servlet;

import mealordering.annotations.UseAjax;
import mealordering.domain.User;
import mealordering.exception.UserNotActiveException;
import mealordering.exception.UserNotFoundException;
import mealordering.service.ServiceFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 登录的Servlet
 */
@UseAjax
@WebServlet(name = "LoginServlet", urlPatterns = {"/mealordering/login"})
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		//声明返回参数
		User user = null;
		String status = "success";

		try {
			user = ServiceFactory.getNormalUserSvc().loginByUserNameAndPassword(userName, password);
			//如果查找到用户，则将用户信息存储到session中
			req.getSession().setAttribute("user", user);
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		} catch(UserNotFoundException e) {
			e.printStackTrace();
			status = "notFound";
		} catch(UserNotActiveException e) {
			e.printStackTrace();
			status = "notActive";
		}

		var data = new JSONObject().put("status", status).put("user", user);
		resp.getWriter().println(data);
	}
}
