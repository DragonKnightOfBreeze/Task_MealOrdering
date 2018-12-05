package task_itcaststore.web.servlet.manager;

import task_itcaststore.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台删除公告的Servlet
 */
@WebServlet(name = "DelNoticeServlet", urlPatterns = {"/manager/delNotice"})
public class DelNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService service = new NoticeService();
		//获取请求参数，公告id
		String n_id = request.getParameter("id").trim();
		//调用dao层方法
		service.deleteNoticeById(n_id);
		response.sendRedirect(request.getContextPath() + "/manager/listNotice");
	}
}
