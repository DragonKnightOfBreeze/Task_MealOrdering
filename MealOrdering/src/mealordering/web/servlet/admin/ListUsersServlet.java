package mealordering.web.servlet.admin;

import mealordering.domain.BeanPage;
import mealordering.domain.NormalUser;
import mealordering.service.NormalUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台查询所有用户的Servlet
 */
@WebServlet(name = "ListUsersServlet", urlPatterns = {"/mealordering/admin/listUsers"})
public class ListUsersServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NormalUserService service = new NormalUserService();
		//默认设置：第1页，每页20条
		BeanPage<NormalUser> userPage = service.findAllInPage(1, 20);

		req.setAttribute("userPage", userPage);
		req.getRequestDispatcher("/admin/user/list.jsp").forward(req, resp);
	}

}
