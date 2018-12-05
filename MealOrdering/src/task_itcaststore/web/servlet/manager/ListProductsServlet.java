package task_itcaststore.web.servlet.manager;

import task_itcaststore.domain.Product;
import task_itcaststore.exception.ListProductException;
import task_itcaststore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 后台查询所有商品信息的Servlet
 */
@WebServlet(name = "ListProductsServlet", urlPatterns = {"/manager/listProducts"})
public class ListProductsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		try {
			//2.调用service层用于查询所有商品的方法
			List<Product> productList = service.findAllProducts();

			//3.将查询出的所有商品放进request域中
			request.setAttribute("productList", productList);
			//4.重定向到list.jsp页面
			request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
		} catch (ListProductException e) {
			e.printStackTrace();
			response.getWriter().println("警告：查询所有商品信息失败！");
		}
	}
}
