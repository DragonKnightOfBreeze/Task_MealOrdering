/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.notice;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Order;
import mealordering.domain.enums.EUser_Type;
import mealordering.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据Id查询订单的Servlet
 */
@WebServlet(name = "FindOrderByIdServlet", urlPatterns = {"/mealordering/findOrderById"})
public class FindOrderByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String id = request.getParameter("id").trim();
		String type = request.getParameter("type").trim();

		OrderService service = new OrderService();
		Order order = service.findById(id);

		request.setAttribute("order", order);
		if(StringExt.equalsE(type, EUser_Type.Admin)) {
			request.getRequestDispatcher("/admin/orderInfo.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/order/orderInfo.jsp").forward(request, response);
		}
	}
}
