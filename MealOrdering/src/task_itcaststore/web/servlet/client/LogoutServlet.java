package task_itcaststore.web.servlet.client;

import task_itcaststore.utils.ext.StringExt;

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
@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//flag标识
		String flag = request.getParameter("flag").trim();
		//获取session对象.
		HttpSession session = request.getSession();

		//销毁session
		session.invalidate();
		//如果标识不为空，则重定向到首页
		if (!StringExt.isEmpty(flag)) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
}
