/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.order;

import mealordering.annotations.UseAjax;
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
 * 更新订单状态的Servlet
 */
@UseAjax
@WebServlet(name = "UpdateOrderStateServlet", urlPatterns = {"/mealordering/account/updateOrderState"})
public class UpdateOrderStateServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数（订单id）
		String id = req.getParameter("id").trim();
		//声明返回参数
		String status = "success";

		try {
			ServiceFactory.getOrderSvc().updatePayState(id);
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		var data = new JSONObject().put("status", status);
		resp.getWriter().println(data);
	}
}
