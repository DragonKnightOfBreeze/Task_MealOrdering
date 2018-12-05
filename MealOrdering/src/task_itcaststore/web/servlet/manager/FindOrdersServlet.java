package task_itcaststore.web.servlet.manager;

import task_itcaststore.domain.Order;
import task_itcaststore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查找所有订单的Servlet
 */
@WebServlet(name = "FindOrdersServlet", urlPatterns = {"/manager/findOrders"})
public class FindOrdersServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建Service层对象
		OrderService service = new OrderService();
		//调用Service层对象的findAllOrder()方法查询订单列表
		List<Order> orderList = service.findAllOrders();

		//将查询到的订单信息添加到request作用域
		request.setAttribute("orderList", orderList);
		//将请求转发到list.jsp页面
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);
	}
}
