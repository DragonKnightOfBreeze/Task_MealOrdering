/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.account;

import windea.utils.UUIDUtils;
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
@WebServlet(name = "CreateOrderServlet", urlPatterns = "/mealordering/account/create-order")
public class CreateOrderServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数 （从session中得到当前用户和购物车图表）
		HttpSession session = req.getSession();
		NormalUser user = (NormalUser) session.getAttribute("onlineUser");
		Map<Meal, Integer> cart = (Map<Meal, Integer>) session.getAttribute("cart");

		try {
			//STEP 后台操作
			//将数据封装到订单对象中
			Order order = new Order();
			BeanUtils.populate(order, req.getParameterMap());
			//生成订单id
			order.setId(UUIDUtils.getUUID());
			//封装用户信息到订单
			order.setUser(user);
			//加入餐品信息
			for(Meal meal : cart.keySet()) {
				OrderItem item = new OrderItem();
				item.setOrder(order);
				item.setBuyCount(cart.get(meal));
				item.setMeal(meal);
				order.getOrderItemList().add(item);
			}
			ServiceFactory.getOrderSvc().doCreate(order);
			//STEP 设置转发属性与跳转
			req.setAttribute("order", order);
			req.getRequestDispatcher("/mealordering/account/order-info.jsp");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
