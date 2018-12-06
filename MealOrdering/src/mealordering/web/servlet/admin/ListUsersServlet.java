package mealordering.web.servlet.admin;

import mealordering.domain.BeanPage;
import mealordering.domain.User;
import mealordering.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台查询所有用户的Servlet
 */
@WebServlet(name = "ListUsersServlet", urlPatterns = {"/admin/listUsers"})
public class ListUsersServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserService();
		//默认设置：第1页，每页20条
		BeanPage<User> userPage = service.findAllInPage(1, 20);

		request.setAttribute("userPage", userPage);
		request.getRequestDispatcher("/admin/user/list.jsp").forward(request, response);
	}
}
