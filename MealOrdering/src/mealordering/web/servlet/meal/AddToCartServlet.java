package mealordering.web.servlet.meal;

import windea.utils.ext.StringExt;
import mealordering.domain.Meal;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

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
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = "/mealordering/meal/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数（餐品id，修改后的购买数量）
		String id = req.getParameter("id");
		int count = StringExt.toInt(req.getParameter("count"), 1);

		//STEP 从session中得到购物车对象
		HttpSession session = req.getSession();
		Map<Meal, Integer> cart = (Map<Meal, Integer>) session.getAttribute("cart");
		//如果购物车为null，说明没有商品存储在购物车中，则创建出购物车
		if(cart == null) {
			cart = new HashMap<>();
		}
		try {
			//STEP 后台操作
			//从数据库中查找
			Meal meal = ServiceFactory.getMealSvc().findById(id);
			//向购物车中添加商品，如果没有，则设置数量为n，否则设置数量为+n
			cart.merge(meal, count, (a, b) -> a + b);
			//STEP 设置转发属性与跳转
			session.setAttribute("cart", cart);
			resp.sendRedirect(req.getContextPath() + "/mealordering/account/my-cart.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/empty-result.jsp");
		}
	}
}
