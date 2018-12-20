package mealordering.web.servlet;

import dk_breeze.exception.NotImplementedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO 重置密码的Servlet
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = "/mealordering/resetPassword")
public class ResetPasswordServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		throw new NotImplementedException();
	}
}
