package mealordering.web.servlet;

import windea.utils.UUIDUtils;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 生成resetCode，并发送找回密码邮件的Servlet
 */
@WebServlet(name = "FindPasswordServlet", urlPatterns = "/mealordering/send-reset-psw-email")
public class FindPasswordServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		String email = req.getParameter("email");

		//STEP 后台操作
		//生成resetCode
		String resetCode = UUIDUtils.getUUID();
		//发送重置密码邮件
		ServiceFactory.getNormalUserSvc().sendFindPswEmail(email, resetCode);
		//STEP 设置转发属性与跳转
		req.getSession().setAttribute("resetCode", resetCode);
		resp.sendRedirect(req.getContextPath() + "/mealordering/reset-password.jsp");
	}
}
