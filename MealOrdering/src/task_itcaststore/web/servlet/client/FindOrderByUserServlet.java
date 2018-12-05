package task_itcaststore.web.servlet.client;

import task_itcaststore.domain.Order;
import task_itcaststore.domain.User;
import task_itcaststore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FindOrderByUserServlet", urlPatterns = {"/findOrderByUser"})
public class FindOrderByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取名为“user”的session
		User user = (User) request.getSession().getAttribute("user");

		//调用service中的方法,根据用户信息查找订单
		OrderService service = new OrderService();
		List<Order> orderList = service.findOrdersByUser(user);
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/client/orderList.jsp").forward(request, response);
	}
}
