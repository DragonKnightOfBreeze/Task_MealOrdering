package mealordering.web.servlet.admin;

import dkbreeze.utils.ext.StringExt;
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 根据搜索类型查询订单信息。
 */
@WebServlet(name = "Admin_SearchOrderServlet", urlPatterns = "/mealordering/admin/search-order")
public class Admin_SearchOrderServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String searchType = req.getParameter("searchType");
		String userName = req.getParameter("userName");

		try {
			// STEP 后台操作
			List<Order> orderList = null;
			if(searchType == null || StringExt.equals(searchType, "byUserName")) {
				NormalUser user = ServiceFactory.getNormalUserSvc().findByUserName(userName);
				orderList = ServiceFactory.getOrderSvc().findByUser(user);
			}
			PageGroup<Order> pageGroup = new PageGroup<>(orderList);
			List<Order> page = pageGroup.getPage(1);
			String[] pageBtnText = pageGroup.getPageBtnText();
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("pageGroup", pageGroup);
			req.setAttribute("page", page);
			req.setAttribute("pageBtnText", pageBtnText);
			req.getRequestDispatcher("/mealordering/admin/order-list.jsp").forward(req, resp);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/empty-result.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
