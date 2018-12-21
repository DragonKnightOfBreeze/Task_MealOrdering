/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.meal;

import mealordering.annotations.UseAjax;
import mealordering.domain.Meal;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 查询所有餐品信息的Servlet
 */
@UseAjax
@WebServlet(name = "FindAllMealsServlet", urlPatterns = {"/mealordering/admin/findAllMeals", "/mealordering/meal/findAll"})
public class FindAllMealsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		List<Meal> mealPage = null;
		String[] pageBtnText = null;

		try {
			PageGroup<Meal> pageGroup = new PageGroup<>(ServiceFactory.getMealSvc().findAll(), 1);
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

		var data = new JSONObject().put("status", status).put("mealPage", mealPage).put("pageBtnText", pageBtnText);
		resp.getWriter().println(data);
	}
}
