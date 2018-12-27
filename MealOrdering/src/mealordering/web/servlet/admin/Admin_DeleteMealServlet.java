package mealordering.web.servlet.admin;

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
@WebServlet(name = "Admin_DeleteMealServlet", urlPatterns = "/mealordering/admin/delete-meal")
public class Admin_DeleteMealServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String id = req.getParameter("id").trim();

		try {
			//STEP 后台操作
			ServiceFactory.getMealSvc().doDeleteById(id);
			//STEP 设置转发属性与跳转
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/find-all-meals");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}

}
