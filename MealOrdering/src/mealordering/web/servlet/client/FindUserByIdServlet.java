package mealordering.web.servlet.client;

import mealordering.domain.User;
import mealordering.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据Id查询订单的Servlet
 */
@WebServlet(name = "FindUserByIdServlet", urlPatterns = {"/findUserById"})
public class FindUserByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		int userId = Integer.parseInt(request.getParameter("userId").trim());
		String type = request.getParameter("type").trim();

		UserService service = new UserService();
		User user = service.findById(userId);

		request.setAttribute("user", user);
		request.getRequestDispatcher("/client/editUserInfo.jsp").forward(request, response);
	}
}
