package mealordering.web.servlet.admin;

import windea.utils.ext.StringExt;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 删除用户的Servlet
 */
@WebServlet(name = "Admin_DeleteUserServlet", urlPatterns = "/mealordering/admin/delete-user")
public class Admin_DeleteUserServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		int id = StringExt.toInt(req.getParameter("id"));

		try {
			//STEP 后台操作
			ServiceFactory.getNormalUserSvc().doDeleteById(id);
			//STEP 设置转发属性与跳转
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/find-all-users");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
