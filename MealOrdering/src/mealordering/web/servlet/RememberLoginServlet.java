package mealordering.web.servlet;

import dk_breeze.utils.JSONUtils;
import dk_breeze.utils.ext.StringExt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 记住登录状态的Servlet（使用Cookie）
 * <br>思路得到用户id，存储到cookie中。
 */
@WebServlet(name = "RememberLoginServlet", urlPatterns = {"/mealordering/rememberLogin"})
public class RememberLoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		//得到传入参数（用户id）
		int id = StringExt.toInt(req.getParameter("id"), -1);
		//声明返回参数
		String status = "success";

		if(id != -1) {
			resp.addCookie(new Cookie("userId", Integer.toString(id)));
		} else {
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status));
	}
}
