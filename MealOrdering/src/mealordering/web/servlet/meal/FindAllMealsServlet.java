package mealordering.web.servlet.meal;

import dk_breeze.utils.JSONUtils;
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
 * 查询所有餐品信息的Servlet
 */
@WebServlet(name = "FindAllMealsServlet", urlPatterns = {"/mealordering/admin/findAllMeals", "/mealordering/meal/findAll"})
public class FindAllMealsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		List<Meal> page = null;
		List<String> pageBtnText = null;
		int pageIndex = 1;
		int pageCount = 1;

		try {
			//如果索引为0，则表面要重新冲数据库中读取，否则从session中读取，并换页。
			PageGroup<Meal> pageGroup = new PageGroup<>(ServiceFactory.getMealSvc().findAll());
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
				.put("pageIndex", pageIndex).put("pageCount", pageCount));
	}
}
