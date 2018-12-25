/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.meal;

import dk_breeze.utils.JSONUtils;
import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Meal;
import mealordering.domain.PageGroup;
import mealordering.enums.Category;
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
 * 多条件搜索餐品的Servlet
 */
@WebServlet(name = "SearchMealByCategoryServlet", urlPatterns = "/mealordering/meal/searchByCategory")
public class SearchMealByCategoryServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String category = req.getParameter("category").trim();
		//声明返回参数
		String status = "success";
		List<Meal> page = null;
		List<String> pageBtnText = null;
		int pageIndex = 1;
		int pageCount = 1;

		try {
			//如果是选择的是“所有分类”，则查询全部餐品，否则分类查询。
			PageGroup<Meal> pageGroup;
			if(StringExt.equalsE(category, Category.all)) {
				pageGroup = new PageGroup<>(ServiceFactory.getMealSvc().findAll(), 1);
			} else {
				pageGroup = new PageGroup<>(ServiceFactory.getMealSvc().searchByCategory(category), 1);
			}
			page = pageGroup.getPage(1);
			pageBtnText = pageGroup.getPageBtnText();
			pageCount = pageGroup.getPageCount();
			req.getSession().setAttribute("pageGroup", pageGroup);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status, "page", page, "pageBtnText", pageBtnText)
				.put("pageIndex", pageIndex).put("pageCount", pageCount).put("category", category));
	}
}
