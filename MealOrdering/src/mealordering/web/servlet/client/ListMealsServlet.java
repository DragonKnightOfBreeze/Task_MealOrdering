package mealordering.web.servlet.client;

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
 * 分页显示餐品信息的Servlet
 */
@WebServlet(name = "ListMealsServlet", urlPatterns = {"/mealordering/client/listMeals"})
public class ListMealsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义当前页面索引，默认为1
		int pageIndex = StringExt.toInt(request.getParameter("pageIndex"), 1);
		//定义每页显示条数，默认为10
		int count = StringExt.toInt(request.getParameter("count"), 10);
		//得到餐品分类
		String category = request.getParameter("category").trim();

		MealService service = new MealService();
		//默认设置：默认分类，第1页，每页10条
		BeanPage<Meal> mealPage = service.searchByCategoryInPage(category, pageIndex, count);

		request.setAttribute("mealPage", mealPage);
		request.getRequestDispatcher("/client/mealList.jsp").forward(request, response);
	}
}
