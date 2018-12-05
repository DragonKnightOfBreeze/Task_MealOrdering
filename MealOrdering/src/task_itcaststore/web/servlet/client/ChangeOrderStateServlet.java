package task_itcaststore.web.servlet.client;

import task_itcaststore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更改订单状态的Servlet
 */
@WebServlet(name = "ChangeOrderStateServlet", urlPatterns = {"/changeOrderState"})
public class ChangeOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得订单号数据
		String orderId = request.getParameter("orderId").trim();

		try {
			//根据订单号修改订单状态
			OrderService service = new OrderService();
			service.updateOrderState(orderId);
			request.setAttribute("INFO_paySuccess", "信息：恭喜，支付成功！");
			request.getRequestDispatcher("/findByUser").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("警告：支付失败！");
		}
	}
}
