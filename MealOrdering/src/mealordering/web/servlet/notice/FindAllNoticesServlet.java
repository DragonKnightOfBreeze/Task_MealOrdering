package mealordering.web.servlet.notice;

import dk_breeze.utils.JSONUtils;
import mealordering.domain.Notice;
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
 * 后台查询所有公告的Servlet
 */
@WebServlet(name = "FindAllNoticesServlet", urlPatterns = {"/mealordering/admin/findAllNotices", "/mealordering/notice/findAll"})
public class FindAllNoticesServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		List<Notice> noticePage = null;
		String[] pageBtnText = null;

		try {
			PageGroup<Notice> pageGroup = new PageGroup<>(ServiceFactory.getNoticeSvc().findAll(), 1);
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

		resp.getWriter().println(JSONUtils.of("status", status, "noticePage", noticePage, "pageBtnText", pageBtnText));
	}
}
