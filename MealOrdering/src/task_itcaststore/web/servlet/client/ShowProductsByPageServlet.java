package task_itcaststore.web.servlet.client;

import task_itcaststore.domain.PageBean;
import task_itcaststore.service.ProductService;
import task_itcaststore.utils.ext.StringExt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 分页显示数据的Servlet
 */
@WebServlet(name = "ShowProductsByPageServlet", urlPatterns = {"/showProductsByPage"})
public class ShowProductsByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.定义当前页码，默认为1
		int currentPage;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage").trim());
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		//2.定义每页显示条数,默认为4
		int currentCount;
		try {
			currentCount = Integer.parseInt(request.getParameter("currentCount").trim());
		} catch (NumberFormatException e) {
			currentCount = 4;
		}
		//3.获取查找的分类
		String category = request.getParameter("category").trim();
		if (StringExt.isEmpty(category))
			category = "全部商品";

		//4.调用service，完成当前页的分页数据
		ProductService service = new ProductService();
		PageBean pageBean = service.findProductsByPage(currentPage, currentCount, category);
		//将数据存储到request范围，跳转到product_list.jsp页面展示
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/client/productList.jsp").forward(request, response);
	}
}
