package task_itcaststore.web.servlet.client;

import task_itcaststore.domain.Product;
import task_itcaststore.exception.FindProductByIdException;
import task_itcaststore.service.ProductService;

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
@WebServlet(name = "AddCartServlet", urlPatterns = {"/addCart"})
public class AddCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.得到商品id
		String id = request.getParameter("id").trim();

		try {
			ProductService service = new ProductService();
			//2.调用service层方法，根据id查找商品
			Product product = service.findProductById(id);
			//3.将商品添加到购物车
			//3.1获得session对象
			HttpSession session = request.getSession();
			//3.2从session中获取购物车对象
			Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
			//3.3如果购物车为null,说明没有商品存储在购物车中，则创建出购物车
			if (cart == null) {
				cart = new HashMap<>();
			}
			//3.4向购物车中添加商品，如果没有，则设置商品数量为1，否则设置商品数量为+1
			cart.merge(product, 1, (a, b) -> a + b);

			session.setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		} catch (FindProductByIdException e) {
			e.printStackTrace();
			response.getWriter().println("警告：向购物车中添加商品信息！");
		}
	}
}
