package mealordering.web.servlet.client;

import mealordering.domain.Meal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 更改购物车内容的Servlet
 */
@WebServlet(name = "ChangeCartServlet", urlPatterns = {"/changeCart"})
public class ChangeCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String mealId = request.getParameter("mealId").trim();
		int count = Integer.parseInt(request.getParameter("count").trim());
		//从session中得到购物车对象
		HttpSession session = request.getSession();
		Map<Meal, Integer> cart = (Map<Meal, Integer>) session.getAttribute("cart");

		Meal meal = new Meal();
		meal.setId(mealId);
		//更改餐品数量
		if(count > 0) {
			cart.put(meal, count);
		} else {
			cart.remove(meal);
		}

		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
	}
}
