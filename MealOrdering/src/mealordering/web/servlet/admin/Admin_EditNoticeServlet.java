package mealordering.web.servlet.admin;

import windea.utils.ext.StringExt;
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
 * 编辑公告信息的Servlet
 */
@WebServlet(name = "Admin_EditNoticeServlet", urlPatterns = "/mealordering/admin/edit-notice")
public class Admin_EditNoticeServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		int id = StringExt.toInt(req.getParameter("id"));
		String title = req.getParameter("title").trim();
		String details = req.getParameter("details").trim();

		try {
			//STEP 后台操作
			Notice notice = new Notice(title, details);
			notice.setId(id);
			ServiceFactory.getNoticeSvc().doEdit(notice);
			//STEP 设置转发属性与跳转
			req.setAttribute("id", id);
			req.getRequestDispatcher("/mealordering/admin/find-notice").forward(req, resp);
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
