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
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/mealordering/logout"})
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//表单参数
		String flag = request.getParameter("flag");

		//销毁session
		request.getSession().invalidate();
		//如果标识不为空，则重定向到首页
		if(!flag.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/index.html");
		}
	}
}
