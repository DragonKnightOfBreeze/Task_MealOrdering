package mealordering.web.servlet.meal;

import dk_breeze.utils.JSONUtils;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 删除餐品的Servlet
 */
@WebServlet(name = "DeleteMealServlet", urlPatterns = {"/mealordering/admin/deleteMeal"})
public class DeleteMealServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String id = req.getParameter("id").trim();
		//声明返回参数
		String status = "success";

		try {
			ServiceFactory.getMealSvc().doDeleteById(id);
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status));
	}

}
