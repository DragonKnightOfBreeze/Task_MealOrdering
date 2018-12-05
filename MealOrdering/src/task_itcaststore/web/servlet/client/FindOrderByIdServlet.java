package task_itcaststore.web.servlet.client;

import task_itcaststore.domain.Order;
import task_itcaststore.domain.enums.EUserType;
import task_itcaststore.service.OrderService;
import task_itcaststore.utils.ext.StringExt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据id查询订单
 */
@WebServlet(name = "FindOrderByIdServlet", urlPatterns = {"/findById"})
public class FindOrderByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.得到要查询的订单的id
		String id = request.getParameter("id").trim();
		//2.获取用户类型
		String type = request.getParameter("type").trim();

		//3.根据id查找订单
		OrderService service = new OrderService();
		Order order = service.findOrderById(id);
		//4.将查询出的订单信息添加到request作用域中
		request.setAttribute("order", order);

		if (StringExt.equalsE(type, EUserType.Admin)) {
			//如果是超级用户，则请求转发到view.jsp页面
			request.getRequestDispatcher("/admin/orders/view.jsp").forward(request, response);
		} else {
			//否则转发到orderInfo.jsp页面
			request.getRequestDispatcher("/client/orderInfo.jsp").forward(request, response);
		}
	}
}
