/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import mealordering.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更新订单状态的Servlet
 */
@WebServlet(name = "UpdateOrderStateServlet", urlPatterns = {"/mealordering/updateOrderState"})
public class UpdateOrderStateServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//表单参数
		String orderId = req.getParameter("orderId").trim();

		try {
			//根据订单号修改订单状态
			OrderService service = new OrderService();
			service.updatePayState(orderId);
			req.setAttribute("paySuccessMsg", "信息：恭喜，支付成功！");
			req.getRequestDispatcher("/findByUser").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("警告：支付失败！");
		}
	}
}
