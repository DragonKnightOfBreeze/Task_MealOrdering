package mealordering.web.servlet.admin;

import dk_breeze.utils.JSONUtils;
import dk_breeze.utils.ext.StringExt;
import mealordering.domain.PageGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 得到指定内容分页的Servlet
 */
@WebServlet(name = "GetPageServlet", urlPatterns = {"/mealordering/getPage"})
public class GetPageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数，默认每页15条记录
		int pageIndex = StringExt.toInt(req.getParameter("pageIndex"), 1);
		int count = StringExt.toInt(req.getParameter("count"), 15);
		//声明输出参数
		String status = "success";
		List page = null;
		List pageBtnText = null;
		int pageCount = 1;

		HttpSession session = req.getSession();
		PageGroup pageGroup = (PageGroup) session.getAttribute("pageGroup");
		//空引用检查（非空时，pageGroup.list不可能为空，或者长度为空）
		if(pageGroup == null) {
			status = "empty";
		} else {
			page = pageGroup.getPage(pageIndex, count);
//			pageBtnText = pageGroup.getPageBtnText();
			pageCount = pageGroup.getPageCount();
			session.setAttribute("pageGroup", pageGroup);
		}

		resp.getWriter().println(JSONUtils.of("status", status, "page", page, "pageBtnText", pageBtnText)
				.put("pageIndex", pageIndex).put("pageCount", pageCount));
	}
}

