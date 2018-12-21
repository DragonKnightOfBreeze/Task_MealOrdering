package mealordering.web.servlet.notice;


import mealordering.domain.Notice;
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
 * 添加公告的Servlet
 */
@WebServlet(name = "AddNoticeServlet", urlPatterns = {"/mealordering/admin/addNotice"})
public class AddNoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String title = req.getParameter("title").trim();
		String details = req.getParameter("details").trim();
		//声明返回参数
		String status = "success";

		try {
			Notice notice = new Notice(title, details);
			ServiceFactory.getNoticeSvc().doAdd(notice);
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		var data = new JSONObject().put("status", status);
		resp.getWriter().println(data);
	}
}
