/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import mealordering.annotations.UseAjax;
import mealordering.domain.Notice;
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
 * 前台页面，显示主页的servlet
 * <ol>
 * <li>展示最新添加或修改的一条公告。</li>
 * <li>展示本周热销商品。</li>
 * </ol>
 */
@UseAjax
@WebServlet(name = "IndexServlet", urlPatterns = {"/mealordering/index"})
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		Notice notice = null;
		List<Object[]> weekHotMeals = null;

		try {
			//查询最近的一条公告
			notice = ServiceFactory.getNoticeSvc().findRecent(1).get(0);
			//查询本周热销的两条商品
			weekHotMeals = ServiceFactory.getMealSvc().getWeekHotMeals(2);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		JSONObject data = new JSONObject().put("status", status).put("notice", notice)
				.put("weekHotMeals", weekHotMeals);
		resp.getWriter().println(data);
	}
}
