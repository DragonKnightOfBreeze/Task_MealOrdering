package mealordering.web.servlet;

import dk_breeze.exception.NotImplementedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		throw new NotImplementedException();
	}
}
