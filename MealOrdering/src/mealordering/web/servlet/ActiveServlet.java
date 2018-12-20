/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import mealordering.exception.ActiveException;
import mealordering.service.NormalUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户激活的Servlet
 */
@WebServlet(name = "ActiveServlet", urlPatterns = {"/mealordering/active"})
public class ActiveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String activeCode = request.getParameter("activeCode").trim();

		try {
			NormalUserService service = new NormalUserService();
			//默认设置：24小时的有效时间
			service.doActive(activeCode, 24);

			response.sendRedirect(request.getContextPath() + "/active-success.html");
		} catch(ActiveException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/active-fail.html");
		}
	}
}
