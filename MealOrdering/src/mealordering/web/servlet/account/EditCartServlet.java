/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.account;

import dk_breeze.utils.ext.StringExt;
import mealordering.annotations.UseAjax;
import mealordering.domain.Meal;
import mealordering.exception.ResultEmptyException;
import mealordering.service.MealService;
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
 * 更改购物车内容的Servlet
 */
@UseAjax
@WebServlet(name = "EditCartServlet", urlPatterns = {"/mealordering/account/editCart"})
public class EditCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数（餐品id，修改后的购买数量）
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
			MealService service = new MealService();
			Meal meal = service.findById(id);
			//更改餐品数量
			if(count > 0) {
				cart.put(meal, count);
			} else {
				cart.remove(meal);
			}
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
