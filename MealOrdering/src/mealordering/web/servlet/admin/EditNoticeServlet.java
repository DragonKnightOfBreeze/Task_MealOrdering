package mealordering.web.servlet.admin;

import mealordering.domain.Notice;
import mealordering.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 编辑公告信息的Servlet
 */
@WebServlet(name = "EditNoticeServlet", urlPatterns = {"/mealordering/admin/editNotice"})
public class EditNoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		int id = Integer.parseInt(request.getParameter("id").trim());
		String title = request.getParameter("title").trim();
		String details = request.getParameter("details").trim();
		//将当前时间设为添加公告的时间
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		Notice notice = new Notice();
		notice.setId(id);
		notice.setTitle(title);
		notice.setDetails(details);
		notice.setTime(time);
		NoticeService service = new NoticeService();
		service.doEdit(notice);

		response.sendRedirect(getServletContext().getContextPath() + "/admin/listNotices");
	}
}
