package mealordering.web.servlet.client;

import mealordering.domain.User;
import mealordering.exceptions.RegisterException;
import mealordering.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册的Servlet
 */
@WebServlet(name = "registerServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String gender = request.getParameter("gender").trim();
		String email = request.getParameter("email").trim();
		String phoneNum = request.getParameter("phoneNum").trim();
		String introduce = request.getParameter("introduce");

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setGender(gender);
		user.setEmail(email);
		user.setPhoneNum(phoneNum);
		user.setIntroduce(introduce);

		UserService service = new UserService();
		try {
			service.doRegister(user);

			request.setAttribute("user", user);
			request.getRequestDispatcher("/client/registerSuccess.jsp").forward(request, response);
		} catch(RegisterException e) {
			//如果出现问题，则跳转回注册页面，显示错误信息
			e.printStackTrace();
			String registerMsg = "<b>注册失败！</b><br/>";
			request.setAttribute("registerMsg", registerMsg + e.getMessage());
			request.getRequestDispatcher("/client/register.jsp").forward(request, response);
		}
	}
}
