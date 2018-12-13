package mealordering.web.servlet.client;

import mealordering.domain.Meal;
import mealordering.domain.Order;
import mealordering.domain.OrderItem;
import mealordering.domain.User;
import mealordering.service.OrderService;
import mealordering.utils.IdUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 生成订单的Servlet
 */
@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/createOrder"})
public class CreateOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中得到当前用户和购物车图表
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map<Meal, Integer> cart = (Map<Meal, Integer>) session.getAttribute("cart");

		//将数据封装到订单对象中
		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			response.getWriter().println("警告：订单创建失败！");
		}
		//封装订单id
		order.setId(IdUtils.getUUID());
		//封装用户信息到订单
		order.setUser(user);
		for(Meal meal : cart.keySet()) {
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setBuyCount(cart.get(meal));
			item.setMeal(meal);
			order.getOrderItemList().add(item);
		}
		response.getWriter().println("信息：订单创建成功！");
		response.getWriter().println("订单详情：<br/>" + order);
		//添加订单
		OrderService service = new OrderService();
		service.doCreate(order);

		response.sendRedirect(request.getContextPath() + "/client/createOrderSuccess.jsp");
	}
}
