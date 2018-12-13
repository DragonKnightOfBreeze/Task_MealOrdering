package mealordering.web.servlet.client;

import mealordering.domain.Notice;
import mealordering.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据Id查询公告的Servlet
 */
@WebServlet(name = "FindNoticeByIdServlet", urlPatterns = {"/client/findNoticeById"})
public class FindNoticeByIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		int id = Integer.parseInt(request.getParameter("id").trim());

		NoticeService service = new NoticeService();
		Notice notice = service.findById(id);

		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/admin/notice/doEdit.jsp").forward(request, response);
	}
}
