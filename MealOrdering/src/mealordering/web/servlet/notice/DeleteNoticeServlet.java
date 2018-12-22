package mealordering.web.servlet.notice;

import dk_breeze.utils.JSONUtils;
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
@WebServlet(name = "DeleteNoticeServlet", urlPatterns = {"/mealordering/admin/deleteNotice"})
public class DeleteNoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		int id = StringExt.toInt(req.getParameter("id"));
		//声明返回参数
		String status = "success";

		try {
			ServiceFactory.getNoticeSvc().doDeleteById(id);
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status));
	}
}
