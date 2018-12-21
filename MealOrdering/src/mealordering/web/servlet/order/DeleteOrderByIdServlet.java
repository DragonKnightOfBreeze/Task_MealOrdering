/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.order;

import mealordering.annotations.UseAjax;
import mealordering.service.ServiceFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除订单的Servlet。
 */
@UseAjax
@WebServlet(name = "DeleteOrderByIdServlet", urlPatterns = {"/mealordering/deleteOrderById"})
public class DeleteOrderByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数
		String orderId = req.getParameter("orderId");
		//声明返回参数
		String status = "success";

		ServiceFactory.getOrderSvc().doDeleteById(orderId);

		//打印返回参数
		var data = new JSONObject().put("status", status);
		resp.getWriter().println(data);
	}
}
