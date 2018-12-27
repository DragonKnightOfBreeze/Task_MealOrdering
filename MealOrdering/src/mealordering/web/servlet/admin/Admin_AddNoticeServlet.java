package mealordering.web.servlet.admin;


import mealordering.domain.Notice;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 添加公告的Servlet
 */
@WebServlet(name = "Admin_AddNoticeServlet", urlPatterns = {"/mealordering/admin/add-notice"})
public class Admin_AddNoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String title = req.getParameter("title").trim();
		String details = req.getParameter("details").trim();

		try {
			//STEP 后台操作
			Notice notice = new Notice(title, details);
			ServiceFactory.getNoticeSvc().doAdd(notice);
			//STEP 设置转发属性与跳转
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/find-all-notices");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
