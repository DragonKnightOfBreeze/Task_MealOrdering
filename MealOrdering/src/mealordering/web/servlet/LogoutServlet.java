/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出的Servlet
 * <br>INFO 不使用Ajax。
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/mealordering/logout"})
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//销毁session
		req.getSession().invalidate();
		//重定向到首页
		resp.sendRedirect(req.getContextPath() + "/index.html");
	}
}
