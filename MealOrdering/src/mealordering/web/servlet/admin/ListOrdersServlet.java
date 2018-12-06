package mealordering.web.servlet.admin;

import mealordering.domain.BeanPage;
import mealordering.domain.Order;
import mealordering.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台查询所有公告的Servlet
 */
@WebServlet(name = "ListOrdersServlet", urlPatterns = {"/admin/listOrders"})
public class ListOrdersServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderService service = new OrderService();
		//默认设置：第1页，每页20条
		BeanPage<Order> orderPage = service.findAllInPage(1, 20);

		request.setAttribute("orderPage", orderPage);
		request.getRequestDispatcher("/admin/order/list.jsp").forward(request, response);
	}
}

