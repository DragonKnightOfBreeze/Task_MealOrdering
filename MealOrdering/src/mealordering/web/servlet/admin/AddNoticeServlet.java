package mealordering.web.servlet.admin;


import mealordering.domain.Notice;
import mealordering.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

		Notice notice = new Notice(title, details);
		NoticeService service = new NoticeService();
		service.doAdd(notice);

		resp.sendRedirect(req.getContextPath() + "/mealordering/admin/noticeList.jsp");
	}
}
