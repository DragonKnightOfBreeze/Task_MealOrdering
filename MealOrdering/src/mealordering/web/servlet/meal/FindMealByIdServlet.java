/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.meal;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Meal;
import mealordering.domain.enums.EUser_Type;
import mealordering.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据Id查询餐品信息的Servlet
 */
@WebServlet(name = "FindMealByIdServlet", urlPatterns = {"/mealordering/findMealById"})
public class FindMealByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String id = request.getParameter("id").trim();
		String type = request.getParameter("type").trim();

		MealService service = new MealService();
		Meal meal = service.findById(id);

		request.setAttribute("meal", meal);
		if(StringExt.equalsE(type, EUser_Type.Admin)) {
			request.getRequestDispatcher("/admin/mealInfo.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/meal/mealInfo.jsp").forward(request, response);
		}
	}
}

