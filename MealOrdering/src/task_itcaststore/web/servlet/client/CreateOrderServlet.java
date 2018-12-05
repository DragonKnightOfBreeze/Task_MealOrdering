package task_itcaststore.web.servlet.client;

import org.apache.commons.beanutils.BeanUtils;
import task_itcaststore.domain.Order;
import task_itcaststore.domain.OrderItem;
import task_itcaststore.domain.Product;
import task_itcaststore.domain.User;
import task_itcaststore.service.OrderService;
import task_itcaststore.utils.IdUtils;

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
		HttpSession session = request.getSession();
		//1.得到当前用户
		User user = (User) session.getAttribute("user");
		//2.从购物车中获取商品信息
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

		//3.将数据封装到订单对象中
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
		for (Product product : cart.keySet()) {
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setBuyNum(cart.get(product));
			item.setProduct(product);
			order.getOrderItems().add(item);
		}
		response.getWriter().println("信息：订单创建成功！");
		response.getWriter().println("订单详情：<br/>" + order);

		//4.调用service中添加订单操作
		OrderService service = new OrderService();
		service.addOrder(order);
		response.sendRedirect(request.getContextPath() + "/client/createOrderSuccess.jsp");
	}
}
