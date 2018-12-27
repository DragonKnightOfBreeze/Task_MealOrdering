package mealordering.web.servlet.settings;

import dk_breeze.utils.ext.StringExt;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 编辑用户密码的Servlet
 */
@WebServlet(name = "EditUserPasswordServlet", urlPatterns = "/mealordering/resetPassword")
public class EditUserPasswordServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 得到传入参数
		int id = StringExt.toInt(req.getParameter("id"));
		String password = req.getParameter("password").trim();

		try {
			//STEP 后台操作
			//更改用户密码，并清空resetCode属性
			ServiceFactory.getNormalUserSvc().doEditPassword(id, password);
			req.getSession().setAttribute("resetCode", null);
			//STEP 设置转发属性与跳转
			resp.sendRedirect(req.getContextPath() + "/mealordering/reset-password-success.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");

		}
	}
}
