package mealordering.web.servlet.admin;


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
 * 查询所有订单信息的Servlet
 */
@WebServlet(name = "Admin_FindAllOrdersServlet", urlPatterns = "/mealordering/admin/find-all-orders")
public class Admin_FindAllOrdersServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// STEP 后台操作
			PageGroup<Order> pageGroup = new PageGroup<>(ServiceFactory.getOrderSvc().findAll());
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

