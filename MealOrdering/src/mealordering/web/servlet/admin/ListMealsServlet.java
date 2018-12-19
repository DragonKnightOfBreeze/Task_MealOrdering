package mealordering.web.servlet.admin;

import mealordering.domain.BeanPage;
import mealordering.domain.Meal;
import mealordering.domain.enums.EMeal_Category;
import mealordering.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查询所有餐品的Servlet
 */
@WebServlet(name = "ListMealsServlet", urlPatterns = {"/mealordering/admin/listMeals"})
public class ListMealsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MealService service = new MealService();
		//默认设置：默认分类，第1页，每页20条
		BeanPage<Meal> mealPage = service.searchByCategoryInPage(EMeal_Category.Default.toString(), 1, 20);

		request.setAttribute("mealPage", mealPage);
		request.getRequestDispatcher("/admin/meal/list.jsp").forward(request, response);
	}
}
