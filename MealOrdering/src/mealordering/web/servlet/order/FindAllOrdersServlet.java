package mealordering.web.servlet.order;

import mealordering.annotations.UseAjax;
import mealordering.domain.Order;
import mealordering.domain.PageGroup;
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
import java.util.List;

/**
 * 后台查询所有订单的Servlet
 */
@UseAjax
@WebServlet(name = "FindAllOrdersServlet", urlPatterns = {"/mealordering/admin/findAllOrders"})
public class FindAllOrdersServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		List<Order> orderPage = null;
		String[] pageBtnText = null;

		try {
			PageGroup<Order> pageGroup = new PageGroup<>(ServiceFactory.getOrderSvc().findAll(), 1);
			req.getSession().setAttribute("pageGroup", pageGroup);
			orderPage = pageGroup.getPage(1);
			pageBtnText = pageGroup.getPageBtnText();
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		var data = new JSONObject().put("status", status).put("orderPage", orderPage).put("pageBtnText", pageBtnText);
		resp.getWriter().println(data);
	}
}

