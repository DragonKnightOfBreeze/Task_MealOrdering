package mealordering.web.servlet.order;

import dk_breeze.utils.JSONUtils;
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
 * 后台查询所有订单的Servlet
 */
@WebServlet(name = "FindAllOrdersServlet", urlPatterns = {"/mealordering/admin/findAllOrders"})
public class FindAllOrdersServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		List<Order> page = null;
		List<String> pageBtnText = null;
		int pageIndex = 1;
		int pageCount = 1;

		try {
			PageGroup<Order> pageGroup = new PageGroup<>(ServiceFactory.getOrderSvc().findAll(), 1);
			page = pageGroup.getPage(1);
			pageCount = pageGroup.getPageCount();
			pageBtnText = pageGroup.getPageBtnText();
			req.getSession().setAttribute("pageGroup", pageGroup);
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

