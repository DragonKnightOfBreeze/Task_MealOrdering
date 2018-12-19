package mealordering.web.servlet.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登出的Servlet
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/mealordering/logout"})
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String flag = request.getParameter("flag").trim();

		//销毁session
		HttpSession session = request.getSession();
		session.invalidate();

		//如果标识不为空，则重定向到首页
		if(!flag.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
}
