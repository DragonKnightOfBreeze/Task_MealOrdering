package task_itcaststore.web.servlet.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 模拟支付的Servlet
 */

@WebServlet(name = "PayServlet", urlPatterns = {"/pay"})
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到模拟支付所需的参数
		String orderId = request.getParameter("orderId").trim();
		String money = request.getParameter("money").trim();
		String bank = request.getParameter("bank").trim();

		request.setAttribute("orderId", orderId);
		request.setAttribute("bank", bank);
		request.setAttribute("money", money);
		request.getRequestDispatcher("/client/confirm.jsp").forward(request, response);
	}
}
