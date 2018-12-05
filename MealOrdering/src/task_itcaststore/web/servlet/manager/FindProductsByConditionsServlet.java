package task_itcaststore.web.servlet.manager;

import task_itcaststore.domain.Product;
import task_itcaststore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 多条件查询产品的Servlet
 */
@WebServlet(name = "FindProductsByConditionsServlet", urlPatterns = {"/manager/findProductsByConditions"})
public class FindProductsByConditionsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//2.创建ProductService对象
		ProductService service = new ProductService();
		//1.获取表单数据
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String category = request.getParameter("category");
		String minPrice = request.getParameter("minPrice").trim();
		String maxPrice = request.getParameter("maxPrice").trim();

		//3.调用service层用于条件查询的方法
		List<Product> productList = service.findProductsByConditions(id, name,
				category, minPrice, maxPrice);

		//4.将条件查询的结果放进request域中
		request.setAttribute("productList", productList);
		//5.请求重定向到商品管理首页list.jsp页面
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response);
	}
}
