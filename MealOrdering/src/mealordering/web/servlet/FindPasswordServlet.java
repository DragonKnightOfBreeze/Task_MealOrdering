package mealordering.web.servlet;

import dk_breeze.utils.JSONUtils;
import dk_breeze.utils.UUIDUtils;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 生成resetCode，并发送找回密码邮件的Servlet
 */
@WebServlet(name = "FindPasswordServlet", urlPatterns = "/mealordering/sendResetPswEmail")
public class FindPasswordServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数
		String email = req.getParameter("email");
		//声明返回参数
		String status = "success";

		//生成resetCode
		String resetCode = UUIDUtils.getUUID();
		req.getSession().setAttribute("resetCode", resetCode);
		//发送重置密码邮件
		ServiceFactory.getNormalUserSvc().sendFindPswEmail(email, resetCode);

		var data = JSONUtils.of("status", status);
		resp.getWriter().println(data);
	}
}
