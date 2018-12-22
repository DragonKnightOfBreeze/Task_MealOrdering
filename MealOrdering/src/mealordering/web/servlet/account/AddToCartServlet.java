/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.account;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Meal;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 向购物车中添加商品的Servlet
 * <br>传入：id,count，返回status，使用Ajax。
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/mealordering/account/addToCart"})
public class AddToCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数（餐品id，购买数量）
		String id = req.getParameter("id");
		int count = StringExt.toInt(req.getParameter("count"), 1);
		//声明返回参数
		String status = "success";

		//从session中得到购物车对象
		HttpSession session = req.getSession();
		Map<Meal, Integer> cart = (Map<Meal, Integer>) session.getAttribute("cart");
		//如果购物车为null，说明没有商品存储在购物车中，则创建出购物车
		if(cart == null) {
			cart = new HashMap<>();
		}
		try {
			//从数据库中查找
			Meal meal = ServiceFactory.getMealSvc().findById(id);
			//向购物车中添加商品，如果没有，则设置数量为n，否则设置数量为+n
			cart.merge(meal, count, (a, b) -> a + b);
			session.setAttribute("cart", cart);
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		}

		//打印返回参数
		var data = new JSONObject().put("status", status);
		resp.getWriter().println(data);
	}
}
