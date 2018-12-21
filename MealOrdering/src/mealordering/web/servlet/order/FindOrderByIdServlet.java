/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.order;

import mealordering.annotations.UseAjax;
import mealordering.domain.Order;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;
import org.json.JSONObject;

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
@UseAjax
@WebServlet(name = "FindOrderByIdServlet", urlPatterns = {"/mealordering/admin/findOrderById", "/mealordering/account/findOrderById"})
public class FindOrderByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数（订单id）
		String id = req.getParameter("id").trim();
		//声明返回参数
		String status = "success";
		Order order = null;

		try {
			order = ServiceFactory.getOrderSvc().findById(id);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			status = "error";
		}

		//打印返回参数
		var data = new JSONObject().put("status", status).put("order", order);
		resp.getWriter().println(data);
	}
}
