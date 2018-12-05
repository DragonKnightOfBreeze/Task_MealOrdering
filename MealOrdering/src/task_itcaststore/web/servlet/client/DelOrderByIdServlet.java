package task_itcaststore.web.servlet.client;

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
 * 删除订单
 */
@WebServlet(name = "DelOrderByIdServlet", urlPatterns = {"/doCancel"})
public class DelOrderByIdServlet extends HttpServlet {
	private static final long serialVersionUID = -742965707205621644L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//订单id
		String id = request.getParameter("id").trim();
		//已支付的订单带有type值为client的参数
		String type = request.getParameter("type").trim();

		OrderService service = new OrderService();

		if (StringExt.equalsE(type, EUserType.Admin)) {
			//如果是超级用户
			service.delOrderById_Admin(id);
			request.getRequestDispatcher("/findOrders").forward(request, response);
		} else {
			//如果是普通用户
			service.delOrderById_Client(id);
			response.sendRedirect(request.getContextPath() + "/client/delOrderSuccess.jsp");
			//request.getRequestDispatcher("/findByUser").forward(request, response);
		}
	}
}
