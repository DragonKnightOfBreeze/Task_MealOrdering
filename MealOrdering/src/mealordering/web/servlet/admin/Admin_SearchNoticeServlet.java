/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package mealordering.web.servlet.admin;


import mealordering.domain.Notice;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;
import windea.ext.StringExt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 根据搜索类型查询公告信息。
 */
@WebServlet(name = "Admin_SearchNoticeServlet", urlPatterns = "/mealordering/admin/search-notice")
public class Admin_SearchNoticeServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String searchType = req.getParameter("searchType");
		String title = req.getParameter("title");

		try {
			// STEP 后台操作
			List<Notice> noticeList = null;
			if(searchType == null || StringExt.equals(searchType, "byTitle")) {
				noticeList = ServiceFactory.getNoticeSvc().searchByTitle(title);
			}
			PageGroup<Notice> pageGroup = new PageGroup<>(noticeList);
			List<Notice> page = pageGroup.getPage(1);
			String[] pageBtnText = pageGroup.getPageBtnText();
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("pageGroup", pageGroup);
			req.setAttribute("page", page);
			req.setAttribute("pageBtnText", pageBtnText);
			req.getRequestDispatcher("/mealordering/admin/notice-list.jsp").forward(req, resp);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/empty-result.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
