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
 * 根据商品id查找指定商品信息的servlet
 */
@WebServlet(name = "FindMealByIdServlet", urlPatterns = {"/mealordering/findMealById"})
public class FindMealByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String mealId = request.getParameter("mealId").trim();
		String type = request.getParameter("type").trim();

		OrderService service = new OrderService();
		Order order = service.findById(mealId);

		request.setAttribute("order", order);
		//如果是超级用户，则转发到meal/edit.jsp页面，否则直接跳转到mealInfo.jsp页面
		if(StringExt.equalsE(type, EUser_Type.Admin)) {
			request.getRequestDispatcher("/admin/meal/edit.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/client/mealInfo.jsp").forward(request, response);
		}
	}
}
