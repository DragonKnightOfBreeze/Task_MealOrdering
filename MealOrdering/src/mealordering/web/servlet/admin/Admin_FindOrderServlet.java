/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.admin;

import mealordering.domain.Order;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 根据Id查询订单的Servlet
 */
@WebServlet(name = "Admin_FindOrderServlet", urlPatterns = "/mealordering/admin/find-order")
public class Admin_FindOrderServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//STEP 得到传入参数
		String id = req.getParameter("id").trim();

		try {
			//STEP 后台操作
			Order order = ServiceFactory.getOrderSvc().findById(id);
			//STEP 设置转发属性与跳转
			req.setAttribute("order", order);
			req.getRequestDispatcher("/mealordering/admin/order-info.jsp").forward(req, resp);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/empty-result.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
