/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.notice;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Notice;
import mealordering.domain.enums.EUser_Type;
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
		int id = StringExt.toInt(request.getParameter("id").trim());
		String type = request.getParameter("type").trim();

		NoticeService service = new NoticeService();
		Notice notice = service.findById(id);

		request.setAttribute("notice", notice);
		if(StringExt.equalsE(type, EUser_Type.Admin)) {
			request.getRequestDispatcher("/admin/notice.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/notice/noticeInfo.jsp").forward(request, response);
		}
	}
}
