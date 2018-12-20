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
 * 前台页面，用于菜单栏搜索的Servlet
 */
@WebServlet(name = "SearchMealsServlet", urlPatterns = {"/mealordering/searchMeals"})
public class SearchMealsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String name = req.getParameter("name").trim();
		int pageIndex = StringExt.toInt(req.getParameter("pageIndex"), 1);

		MealService service = new MealService();
		BeanPage<Meal> mealPage;
		if(name.isEmpty()) {
			mealPage = service.findAllInPage(pageIndex, 10);
		} else {
			//默认设置：第1页，每页10条
			mealPage = service.searchByNameInPage(name, pageIndex, 10);
		}

		req.setAttribute("mealPage", mealPage);
		req.getRequestDispatcher("/meal/mealSearchList.jsp").forward(req, resp);
	}
}
