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
 * 添加公告的Servlet
 */
@WebServlet(name = "AddNoticeServlet", urlPatterns = {"/mealordering/admin/addNotice"})
public class AddNoticeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String title = request.getParameter("title").trim();
		String details = request.getParameter("details").trim();
		//将当前时间设为添加公告的时间
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		Notice notice = new Notice();
		NoticeService service = new NoticeService();
		notice.setTitle(title);
		notice.setDetails(details);
		notice.setTime(time);
		service.doAdd(notice);

		//地址重定向
		response.sendRedirect(request.getContextPath() + "/admin/listNotices");
	}
}
