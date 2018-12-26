/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import mealordering.domain.User;
import mealordering.exception.UserNotFoundException;
import mealordering.service.ServiceFactory;

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
@WebServlet(name = "LoginAdminServlet", urlPatterns = {"/mealordering/login-admin"})
public class LoginAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String userName = req.getParameter("userName").trim();
		String password = req.getParameter("password").trim();

		try {
			//STEP 查询和更新
			User user = ServiceFactory.getAdminSvc().loginAdmin(userName, password);
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("onlineUser", user);
			req.setAttribute("msg_loginAdmin", "登录成功！");
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/welcome.jsp");
		} catch(UserNotFoundException e) {
			e.printStackTrace();
			req.setAttribute("msg_loginAdmin", "管理员不存在！");
			resp.sendRedirect(req.getContextPath() + "/mealordering/index");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
