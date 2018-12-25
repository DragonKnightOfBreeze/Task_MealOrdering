package mealordering.web.servlet.order;

import dk_breeze.utils.JSONUtils;
import mealordering.domain.NormalUser;
import mealordering.domain.Order;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 根据用户查询订单的Servlet
 */
@WebServlet(name = "FindOrderByUserServlet", urlPatterns = {"/mealordering/admin/findOrderByUser", "/mealordering/account/findOrderByUser"})
public class FindOrderByUserServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//从session中得到用户信息
		HttpSession session = req.getSession();
		NormalUser user = (NormalUser) session.getAttribute("user");
		//声明返回参数
		String status = "success";
		List<Order> page = null;
		List<String> pageBtnText = null;
		int pageIndex = 1;
		int pageCount = 1;

		try {
			PageGroup<Order> pageGroup = new PageGroup<>(ServiceFactory.getOrderSvc().findByUser(user), 1);
			page = pageGroup.getPage(1);
			pageBtnText = pageGroup.getPageBtnText();
			pageCount = pageGroup.getPageCount();
			session.setAttribute("pageGroup", pageGroup);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(
				JSONUtils.of("status", status, "page", page, "pageBtnText", pageBtnText).put("pageIndex", pageIndex)
						.put("pageCount", pageCount));
	}
}
