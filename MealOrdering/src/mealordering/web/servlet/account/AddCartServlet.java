/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.account;

import mealordering.domain.Meal;
import mealordering.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 向购物车中添加商品的Servlet
 */
@WebServlet(name = "AddCartServlet", urlPatterns = {"/mealordering/addCart"})
public class AddCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String mealId = request.getParameter("mealId").trim();
		//从session中得到购物车对象
		HttpSession session = request.getSession();
		Map<Meal, Integer> cart = (Map<Meal, Integer>) session.getAttribute("cart");

		MealService service = new MealService();
		Meal meal = service.findById(mealId);
		//3如果购物车为null，说明没有商品存储在购物车中，则创建出购物车
		if(cart == null) {
			cart = new HashMap<>();
		}
		//向购物车中添加商品，如果没有，则设置数量为1，否则设置数量为+1
		cart.merge(meal, 1, (a, b) -> a + b);

		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
	}
}
