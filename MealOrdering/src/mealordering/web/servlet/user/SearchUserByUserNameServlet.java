/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.user;

import dk_breeze.utils.JSONUtils;
import mealordering.domain.NormalUser;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 根据名字搜索用户的Servlet
 */
@WebServlet(name = "SearchUserByUserNameServlet", urlPatterns = {"/mealordering/admin/searchUserByUserName"})
public class SearchUserByUserNameServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		String name = req.getParameter("name").trim();
		//声明返回参数
		String status = "success";
		List<NormalUser> page = null;
		List<String> pageBtnText = null;
		int pageIndex = 1;
		int pageCount = 1;

		try {
			PageGroup<NormalUser> pageGroup = new PageGroup<>(ServiceFactory.getNormalUserSvc().searchByUserName(name),
					1);
			page = pageGroup.getPage(1);
			pageBtnText = pageGroup.getPageBtnText();
			pageCount = pageGroup.getPageCount();
			req.getSession().setAttribute("pageGroup", pageGroup);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status, "page", page, "pageBtnText", pageBtnText)
				.put("pageIndex", pageIndex).put("pageCount", pageCount));
	}
}
