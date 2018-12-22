/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.order;

import dk_breeze.utils.JSONUtils;
import dk_breeze.utils.UUIDUtils;
import mealordering.domain.Meal;
import mealordering.domain.NormalUser;
import mealordering.domain.Order;
import mealordering.domain.OrderItem;
import mealordering.service.ServiceFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 生成订单的Servlet
 * <br>传入：...，返回：id,status，使用AJax。
 */
@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/mealordering/account/createOrder"})
public class CreateOrderServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//从session中得到当前用户和购物车图表
		HttpSession session = req.getSession();
		NormalUser user = (NormalUser) session.getAttribute("user");
		Map<Meal, Integer> cart = (Map<Meal, Integer>) session.getAttribute("cart");
		//声明返回参数
		String status = "success";
		String id = UUIDUtils.getUUID();

		try {
			//将数据封装到订单对象中
			Order order = new Order();
			BeanUtils.populate(order, req.getParameterMap());
			//生成订单id
			order.setId(id);
			//封装用户信息到订单
			order.setUser(user);
			for(Meal meal : cart.keySet()) {
				OrderItem item = new OrderItem();
				item.setOrder(order);
				item.setBuyCount(cart.get(meal));
				item.setMeal(meal);
				order.getOrderItemList().add(item);
			}
			ServiceFactory.getOrderSvc().doCreate(order);
		} catch(Exception e) {
			e.printStackTrace();
			status = "fail";
		}

		//打印返回参数
		resp.getWriter().println(JSONUtils.of("status", status, "id", id));
	}
}
