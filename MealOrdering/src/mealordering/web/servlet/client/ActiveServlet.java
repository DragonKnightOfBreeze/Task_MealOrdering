package mealordering.web.servlet.client;

import mealordering.exceptions.ActiveException;
import mealordering.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户激活的Servlet
 */
@WebServlet(name = "ActiveServlet", urlPatterns = {"/active"})
public class ActiveServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String activeCode = request.getParameter("activeCode").trim();

		try {
			UserService service = new UserService();
			//默认设置：24小时的有效时间
			service.doActive(activeCode, 24);

			response.sendRedirect(request.getContextPath() + "/client/activeSuccess.jsp");
		} catch(ActiveException e) {
			e.printStackTrace();
			response.getWriter().println("警告：用户激活失败！");
		}
	}
}
