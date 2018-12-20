/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.meal;

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
 * 多条件查询产品的Servlet
 */
@WebServlet(name = "SearchMealsByConditionsServlet", urlPatterns = {"/mealordering/searchMealsByConditions"})
public class SearchMealsByConditionsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String id = req.getParameter("id").trim();
		String name = req.getParameter("name").trim();
		String category = req.getParameter("category");
		String minPrice = req.getParameter("minPrice").trim();
		String maxPrice = req.getParameter("maxPrice").trim();
		int pageIndex = StringExt.toInt(req.getParameter("pageIndex"), 1);

		MealService service = new MealService();
		//默认设置：第1页，每页10条
		BeanPage<Meal> mealPage = service.searchByConditionsInPage(id, name, category, minPrice, maxPrice, pageIndex, 10);

		req.setAttribute("mealPage", mealPage);
		req.getRequestDispatcher("/meal/mealSearchList.jsp").forward(req, resp);
	}
}
