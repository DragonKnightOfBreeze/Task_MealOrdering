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
 * 后台编辑公告的Servlet
 */
@WebServlet(name = "EditNoticeServlet", urlPatterns = {"/manager/editNotice"})
public class EditNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService service = new NoticeService();
		Notice notice = new Notice();

		//获取表单参数
		int n_id = Integer.parseInt(request.getParameter("id").trim());
		String title = request.getParameter("title").trim();
		String details = request.getParameter("details").trim();

		//将当前时间设为添加公告的时间
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		notice.setN_id(n_id);
		notice.setTitle(title);
		notice.setDetails(details);
		notice.setN_time(time);
		//调用dao层方法
		service.updateNotice(notice);

		response.sendRedirect(getServletContext().getContextPath() + "/manager/listNotice");
	}
}
