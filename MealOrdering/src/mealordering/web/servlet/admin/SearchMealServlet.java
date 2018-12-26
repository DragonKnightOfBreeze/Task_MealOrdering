package mealordering.web.servlet.admin;

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
 * 根据搜索类型查询餐品信息。
 */
@WebServlet(name = "SearchMealServlet", urlPatterns = "/mealordering/admin/search-meal")
public class SearchMealServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String searchType = req.getParameter("searchType");
		String name = req.getParameter("name");
		String category = req.getParameter("category");

		try {
			// STEP 后台操作
			List<Meal> mealList = null;
			if(searchType == null || StringExt.equals(searchType, "byName")) {
				mealList = ServiceFactory.getMealSvc().searchByName(name);
			} else if(StringExt.equals(searchType, "byCategory")) {
				mealList = ServiceFactory.getMealSvc().searchByCategory(category);
			}
			PageGroup<Meal> pageGroup = new PageGroup<>(mealList);
			List<Meal> page = pageGroup.getPage(1);
			String[] pageBtnText = pageGroup.getPageBtnText();
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("pageGroup", pageGroup);
			req.setAttribute("page", page);
			req.setAttribute("pageBtnText", pageBtnText);
			req.getRequestDispatcher("/mealordering/admin/meal-list.jsp").forward(req, resp);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/empty-result.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
