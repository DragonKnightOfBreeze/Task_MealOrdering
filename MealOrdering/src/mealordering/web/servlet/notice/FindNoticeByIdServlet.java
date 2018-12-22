/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.notice;

import dk_breeze.utils.JSONUtils;
import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Notice;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 根据id查询公告的Servlet
 */
@WebServlet(name = "FindNoticeByIdServlet", urlPatterns = {"/mealordering/admin/findNoticeById", "/mealordering/notice/findById"})
public class FindNoticeByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数
		int id = StringExt.toInt(req.getParameter("id"));
		String type = req.getParameter("type").trim();
		//声明返回参数
		String status = "success";
		Notice notice = null;

		try {
			notice = ServiceFactory.getNoticeSvc().findById(id);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status, "notice", notice));
	}
}
