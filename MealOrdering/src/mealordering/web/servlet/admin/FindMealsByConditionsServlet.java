package mealordering.web.servlet.admin;

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
 * 多条件查询产品的Servlet
 */
@WebServlet(name = "FindMealsByConditionsServlet", urlPatterns = {"/client/findMealsByConditions"})
public class FindMealsByConditionsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String id = request.getParameter("id").trim();
		String name = request.getParameter("name").trim();
		String category = request.getParameter("category");
		String minPrice = request.getParameter("minPrice").trim();
		String maxPrice = request.getParameter("maxPrice").trim();

		MealService service = new MealService();
		//默认设置：第1页，每页20条
		BeanPage<Meal> mealPage = service.searchByConditionsInPage(id, name,
				category, minPrice, maxPrice, 1, 10);

		request.setAttribute("mealPage", mealPage);
		request.getRequestDispatcher("/admin/meal/list.jsp").forward(request, response);
	}
}
