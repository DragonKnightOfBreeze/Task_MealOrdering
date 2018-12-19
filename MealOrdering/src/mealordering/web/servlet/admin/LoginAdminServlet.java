/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.admin;

import mealordering.domain.User;
import mealordering.exception.LoginException;
import mealordering.service.AdminService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录的Servlet（Ajax）
 */
@WebServlet(name = "LoginAdminServlet", urlPatterns = {"/mealordering/admin/login"})
public class LoginAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//表单参数
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//回调数据
		User user = null;
		boolean status = false;

		AdminService service = new AdminService();
		try {
			user = service.loginAdmin(userName, password);
		} catch(LoginException e) {
			e.printStackTrace();
		}

		//如果查找到用户，则将用户信息存储到session中
		if(user != null) {
			request.getSession().setAttribute("user", user);
			status = true;
		}
		JSONObject data = new JSONObject().put("user", user).put("status", status);
		response.getWriter().println(data);
	}
}
