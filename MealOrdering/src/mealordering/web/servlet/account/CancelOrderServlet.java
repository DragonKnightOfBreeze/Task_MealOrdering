/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.account;

import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除订单的Servlet。
 */
@WebServlet(name = "CancelOrderServlet", urlPatterns = {"/mealordering/account/cancel-order"})
public class CancelOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String id = req.getParameter("id").trim();

		//STEP 后台操作，设置转发属性与跳转
		ServiceFactory.getOrderSvc().doCancelById(id);
		resp.sendRedirect(req.getContextPath() + "/mealordering/account/find-order-by-user");
	}
}
