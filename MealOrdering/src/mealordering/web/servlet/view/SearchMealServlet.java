package mealordering.web.servlet.view;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.BeanPage;
import mealordering.domain.Meal;
import mealordering.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前台页面，用于菜单栏搜索的Servlet
 */
@WebServlet(name = "SearchMealServlet", urlPatterns = {"/mealordering/searchMeal"})
public class SearchMealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义当前页面索引，默认为1
		int pageIndex = StringExt.toInt(request.getParameter("pageIndex"), 1);
		//定义每页显示条数，默认为10
		int count = 10;
		//得到前台页面搜索框输入的值
		String searchField = request.getParameter("searchField").trim();

		//如果搜索框中没有输入值，则表单传递的为默认值，此时默认查询全部商品目录
		if(StringExt.equals(searchField, "请输入...")) {
			request.getRequestDispatcher("/client/showProductsByPage").forward(request, response);
			return;
		}
		MealService service = new MealService();
		BeanPage<Meal> mealPage = service.searchByNameInPage(searchField, pageIndex, pageIndex);

		request.setAttribute("mealPage", mealPage);
		request.getRequestDispatcher("/client/productSearchList.jsp").forward(request, response);
	}
}
