package mealordering.web.servlet.admin;

import mealordering.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除公告的Servlet
 */
@WebServlet(name = "DeleteNoticeServlet", urlPatterns = {"/mealordering/admin/deleteNotice"})
public class DeleteNoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		int id = Integer.parseInt(request.getParameter("id").trim());

		NoticeService service = new NoticeService();
		service.doDeleteById(id);

		response.sendRedirect(request.getContextPath() + "/admin/listNotices");
	}
}
