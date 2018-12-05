package task_itcaststore.web.servlet.view;


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
 * 前台页面，用于菜单栏下面搜索功能的Servlet
 */

@WebServlet(name = "MenuSearchServlet", urlPatterns = {"/menuSearch"})
public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
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
		int currentCount = 4;
		//获取前台页面搜索框输入的值
		String searchField = request.getParameter("textField");

		//如果搜索框中没有输入值，则表单传递的为默认值，此时默认查询全部商品目录
		if (StringExt.equals(searchField, "请输入...")) {
			request.getRequestDispatcher("/showProductsByPage").forward(request, response);
			return;
		}

		//调用service层的方法，根据书名模糊查询，查找相应的图书
		ProductService service = new ProductService();
		PageBean page = service.findBooksByName(currentPage, currentCount, searchField);
		//将数据存储到request范围，跳转到product_search_list.jsp页面展示
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/productSearchList.jsp").forward(request, response);
	}
}
