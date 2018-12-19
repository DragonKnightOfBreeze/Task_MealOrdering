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
 * 编辑用户信息的Servlet
 */
@WebServlet(name = "EditUserServlet", urlPatterns = {"/mealordering/editUser"})
public class EditUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		int userId = Integer.parseInt(request.getParameter("userId").trim());
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String gender = request.getParameter("gender").trim();
		String email = request.getParameter("email").trim();
		String phoneNum = request.getParameter("phoneNum").trim();
		String introduce = request.getParameter("introduce").trim();

		User user = new User();
		user.setId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setGender(gender);
		user.setEmail(email);
		user.setPhoneNum(phoneNum);
		user.setIntroduce(introduce);
		UserService service = new UserService();
		service.doEdit(user);
		request.getSession().setAttribute("user", user);

		response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
	}


}
