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
 * 后台添加公告的Servlet
 */
@WebServlet(name = "AddNoticeServlet", urlPatterns = {"/manager/doAdd"})
public class AddNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService nService = new NoticeService();
		Notice bean = new Notice();
		//获取表单参数
		String title = request.getParameter("title").trim();
		String details = request.getParameter("details").trim();
		//将当前时间设为添加公告的时间
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		bean.setTitle(title);
		bean.setDetails(details);
		bean.setN_time(time);
		//调用addNotice方法
		nService.addNotice(bean);

		response.sendRedirect(request.getContextPath() + "/manager/listNotice");
	}
}
