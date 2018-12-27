package mealordering.web.servlet;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 得到指定内容分页的Servlet
 */
@WebServlet(name = "GetPageServlet", urlPatterns = "/mealordering/get-page")
public class GetPageServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		int pageIndex = StringExt.toInt(req.getParameter("pageIndex"), 1);
		int count = StringExt.toInt(req.getParameter("count"), 15);
		String item = req.getParameter("item");

		try {
			//STEP 后台操作
			PageGroup pageGroup = (PageGroup) req.getSession().getAttribute("pageGroup");
			if(pageGroup == null || pageGroup.getList() == null || pageGroup.getList().isEmpty())
				throw new ResultEmptyException();
			List page = pageGroup.getPage(pageIndex);
			String[] pageBtnText = pageGroup.getPageBtnText();
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("pageGroup", pageGroup);
			req.setAttribute("page", page);
			req.setAttribute("pageBtnText", pageBtnText);
			String url = null;
			switch(item) {
				case "meal":
					url = "/mealordering/meal/meal-list.jsp";
					break;
				case "order":
					url = "/mealordering/account/my-order-list.jsp";
					break;
				case "notice":
					url = "/mealordering/meal/notice-list.jsp";
					break;
				default:
					resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
					break;
			}
			req.getRequestDispatcher(url).forward(req, resp);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/empty-result.jsp");
		}
	}
}

