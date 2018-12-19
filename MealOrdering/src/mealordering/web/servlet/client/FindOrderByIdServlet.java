package mealordering.web.servlet.client;

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
		//如果是超级用户，则转发到view.jsp页面，否则直接跳转到orderInfo.jsp页面
		if(StringExt.equalsE(type, EUser_Type.Admin)) {

			request.getRequestDispatcher("/admin/order/view.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/client/orderInfo.jsp").forward(request, response);
		}
	}
}
