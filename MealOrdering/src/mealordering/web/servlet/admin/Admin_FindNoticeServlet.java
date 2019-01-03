/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package mealordering.web.servlet.admin;

import mealordering.domain.Notice;
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

/**
 * 根据id查询公告的Servlet
 */
@WebServlet(name = "Admin_FindNoticeServlet", urlPatterns = "/mealordering/admin/find-notice")
public class Admin_FindNoticeServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		int id = StringExt.toInt(req.getParameter("id"));

		try {
			//STEP 后台操作
			Notice notice = ServiceFactory.getNoticeSvc().findById(id);
			//STEP 设置转发属性与跳转
			req.setAttribute("notice", notice);
			req.getRequestDispatcher("/mealordering/admin/notice-info.jsp").forward(req, resp);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/empty-result.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
