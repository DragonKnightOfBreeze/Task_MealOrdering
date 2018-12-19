/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import mealordering.domain.NormalUser;
import mealordering.domain.Order;
import mealordering.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindOrderByUserServlet", urlPatterns = {"/mealordering/findOrderByUser"})
public class FindOrderByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中得到用户信息
		NormalUser user = (NormalUser) request.getSession().getAttribute("user");

		OrderService service = new OrderService();
		List<Order> orderList = service.findByUser(user);

		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/account/orderList.jsp").forward(request, response);
	}
}