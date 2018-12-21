package mealordering.web.servlet.meal;

import mealordering.annotations.UseAjax;
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
 * 删除餐品的Servlet
 */
@UseAjax
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

		var data = new JSONObject().put("status", status);
		resp.getWriter().println(data);
	}

}
