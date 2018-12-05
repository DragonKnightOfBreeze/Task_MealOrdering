package task_itcaststore.web.servlet.client;

import task_itcaststore.domain.Product;

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
		//1.得到商品id
		String id = request.getParameter("id").trim();
		//2.得到要修改的数量
		int count = Integer.parseInt(request.getParameter("count").trim());
		HttpSession session = request.getSession();
		//3.从session中获取购物车
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");

		try {
			Product product = new Product();
			product.setId(id);
			//更改商品数量
			if (count > 0) {
				cart.put(product, count);
			} else {
				cart.remove(product);
			}
			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("警告：更改购物车内容失败！");
		}
	}
}
