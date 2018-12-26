/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.meal;

import dk_breeze.utils.ext.StringExt;
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
 * 根据名字搜索餐品的Servlet
 */
@WebServlet(name = "SearchMealServlet", urlPatterns = {"/mealordering/meal/search", "/mealordering/meal/search"})
public class SearchMealServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String searchType = req.getParameter("searchType");
		String name = req.getParameter("name");
		String category = req.getParameter("category");
		String minPrice = req.getParameter("minPrice");
		String maxPrice = req.getParameter("maxPrice");

		try {
			// STEP 后台操作
			List<Meal> mealList = null;
			if(searchType == null || StringExt.equals(searchType, "byName")) {
				mealList = ServiceFactory.getMealSvc().searchByName(name);
			} else if(StringExt.equals(searchType, "byCategory")) {
				mealList = ServiceFactory.getMealSvc().searchByCategory(category);
			} else {
				mealList = ServiceFactory.getMealSvc().searchByConditions(name, category, minPrice, maxPrice);
			}
			PageGroup<Meal> pageGroup = new PageGroup<>(mealList);
			List<Meal> page = pageGroup.getPage(1);
			String[] pageBtnText = pageGroup.getPageBtnText();
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("pageGroup", pageGroup);
			req.setAttribute("page", page);
			req.setAttribute("pageBtnText", pageBtnText);
			req.getRequestDispatcher("/mealordering/meal/meal-list.jsp").forward(req, resp);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/empty-result.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
