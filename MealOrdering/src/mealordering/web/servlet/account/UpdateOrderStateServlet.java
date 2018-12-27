//package mealordering.web.servlet.account;
//
//import mealordering.service.ServiceFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//
///**
// * 更新订单状态的Servlet
// */
//@WebServlet(name = "UpdateOrderStateServlet", urlPatterns = "/mealordering/account/update-order-state")
//public class UpdateOrderStateServlet extends HttpServlet {
//	@Override
//	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doPost(req, resp);
//	}
//
//	@Override
//	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//STEP 得到传入参数（订单id）
//		String id = req.getParameter("id").trim();
//		try {
//			ServiceFactory.getOrderSvc().updatePayState(id);
//			resp.sendRedirect(req.getContextPath() + "/mealordering/account/pay-success.jsp");
//		} catch(SQLException e) {
//			e.printStackTrace();
//			resp.sendRedirect(req.getContextPath() + "/mealordering/account/pay-fail.jsp");
//		}
//	}
//}
