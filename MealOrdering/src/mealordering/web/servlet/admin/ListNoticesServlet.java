package mealordering.web.servlet.admin;

import mealordering.domain.BeanPage;
import mealordering.domain.Notice;
import mealordering.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台查询所有公告的Servlet
 */
@WebServlet(name = "ListNoticesServlet", urlPatterns = {"/mealordering/admin/listNotices"})
public class ListNoticesServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService service = new NoticeService();
		//默认设置：第1页，每页20条
		BeanPage<Notice> noticePage = service.findAllInPage(1, 20);

		request.setAttribute("noticePage", noticePage);
		request.getRequestDispatcher("/admin/notice/list.jsp").forward(request, response);
	}
}
