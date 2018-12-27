package mealordering.web.servlet.admin;

import dk_breeze.utils.ext.StringExt;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 删除公告的Servlet
 */
@WebServlet(name = "Admin_DeleteNoticeServlet", urlPatterns = "/mealordering/admin/delete-notice")
public class Admin_DeleteNoticeServlet extends HttpServlet {
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
			ServiceFactory.getNoticeSvc().doDeleteById(id);
			//STEP 设置转发属性与跳转
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/find-all-notices");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
