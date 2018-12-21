/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.meal;

import mealordering.domain.Meal;
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

/**
 * 根据id查询餐品信息的Servlet
 */
@WebServlet(name = "FindMealByIdServlet", urlPatterns = {"/mealordering/admin/findMealById", "/mealordering/meal/findById"})
public class FindMealByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String id = req.getParameter("id").trim();
		//声明返回参数
		String status = "success";
		Meal meal = null;

		try {
			meal = ServiceFactory.getMealSvc().findById(id);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		var data = new JSONObject().put("status", status).put("meal", meal);
		resp.getWriter().println(data);
	}
}

