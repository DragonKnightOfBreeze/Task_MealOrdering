/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.meal;

import dk_breeze.utils.JSONUtils;
import mealordering.annotations.UseAjax;
import mealordering.domain.Meal;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 多条件查询产品的Servlet
 */
@UseAjax
@WebServlet(name = "SearchMealByConditionsServlet", urlPatterns = {"/mealordering/meal/searchByConditions"})
public class SearchMealByConditionsServlet extends HttpServlet {
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
		//声明返回参数
		String status = "success";
		List<Meal> mealPage = null;
		String[] pageBtnText = null;

		try {
			PageGroup<Meal> pageGroup = new PageGroup<>(
					ServiceFactory.getMealSvc().searchByConditions(id, name, category, minPrice, maxPrice), 1);
			req.getSession().setAttribute("pageGroup", pageGroup);
			mealPage = pageGroup.getPage(1);
			pageBtnText = pageGroup.getPageBtnText();
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status, "mealPage", mealPage, "pageBtnText", pageBtnText));
	}
}
