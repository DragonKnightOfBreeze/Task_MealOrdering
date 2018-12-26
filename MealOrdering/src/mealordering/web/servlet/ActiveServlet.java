/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户激活的Servlet
 * <br>STEP 不使用Ajax。
 */
@WebServlet(name = "ActiveServlet", urlPatterns = {"/mealordering/active"})
public class ActiveServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String activeCode = req.getParameter("activeCode").trim();

		try {
			//STEP 后台操作
			//默认设置：24小时的有效时间
			ServiceFactory.getNormalUserSvc().doActive(activeCode, 24);
			//STEP 设置转发属性与跳转
			resp.sendRedirect(req.getContextPath() + "/mealordering/active-success.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/active-fail.jsp");
		}
	}
}
