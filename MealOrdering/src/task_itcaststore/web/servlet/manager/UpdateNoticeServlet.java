package task_itcaststore.web.servlet.manager;

import task_itcaststore.domain.Notice;
import task_itcaststore.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 后台修改公告的Servlet
 */
@WebServlet(name = "UpdateNoticeServlet", urlPatterns = {"/manager/doEdit"})
public class UpdateNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService service = new NoticeService();
		Notice notice = new Notice();
		//获取表单参数
		String title = request.getParameter("title").trim();
		String details = request.getParameter("details").trim();

		//将当前时间设为添加公告的时间
		String t = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		notice.setTitle(title);
		notice.setDetails(details);
		notice.setN_time(t);
		//调用dao层方法
		service.addNotice(notice);

		response.sendRedirect(getServletContext().getContextPath() + "/manager/listNotice");
	}
}
