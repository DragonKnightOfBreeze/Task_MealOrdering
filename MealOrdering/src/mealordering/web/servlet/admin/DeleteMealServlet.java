package mealordering.web.servlet.admin;

import mealordering.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除餐品的Servlet
 */
@WebServlet(name = "DeleteMealServlet", urlPatterns = {"/mealordering/admin/deleteMeal"})
public class DeleteMealServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String id = request.getParameter("id").trim();

		MealService service = new MealService();
		service.doDeleteById(id);

		response.sendRedirect(request.getContextPath() + "/listMeals");
	}

}
