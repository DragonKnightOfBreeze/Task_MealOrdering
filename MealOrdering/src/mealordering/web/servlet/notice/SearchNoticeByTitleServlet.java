/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.notice;

import mealordering.annotations.UseAjax;
import mealordering.domain.Notice;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 根据名标题搜索公告的Servlet
 */
@UseAjax
@WebServlet(name = "SearchNoticeByTitleServlet", urlPatterns = {"/mealordering/admin/searchNoticeByTitle", "/mealordering/notice/search"})
public class SearchNoticeByTitleServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String title = req.getParameter("title").trim();
		//声明返回参数
		String status = "success";
		List<Notice> noticePage = null;
		String[] pageBtnText = null;

		try {
			PageGroup<Notice> pageGroup = new PageGroup<>(ServiceFactory.getNoticeSvc().searchByTitle(title), 1);
			req.getSession().setAttribute("pageGroup", pageGroup);
			noticePage = pageGroup.getPage(1);
			pageBtnText = pageGroup.getPageBtnText();
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		var data = new JSONObject().put("status", status).put("noticePage", noticePage).put("pageBtnText", pageBtnText);
		resp.getWriter().println(data);
	}
}
