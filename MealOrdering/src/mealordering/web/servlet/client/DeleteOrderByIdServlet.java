package mealordering.web.servlet.client;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.enums.EUser_Type;
import mealordering.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除订单
 */
@WebServlet(name = "DeleteOrderByIdServlet", urlPatterns = {"/deleteOrderById"})
public class DeleteOrderByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String orderId = request.getParameter("orderId").trim();
		String type = request.getParameter("type").trim();

		//根据用户类型进行不同的操作
		OrderService service = new OrderService();
		if(StringExt.equalsE(type, EUser_Type.Admin)) {
			service.doDeleteById(orderId);
			request.getRequestDispatcher("/admin/listOrders").forward(request, response);
		} else {
			service.doCancelById(orderId);
			response.sendRedirect(request.getContextPath() + "/client/deleteOrderSuccess.jsp");
		}
	}
}
