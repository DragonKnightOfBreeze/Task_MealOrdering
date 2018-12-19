package mealordering.web.servlet.client;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.User;
import mealordering.domain.enums.EUser_Type;
import mealordering.exception.LoginException;
import mealordering.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录的Servlet
 * TODO 完整的登录注册系统
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/mealordering/login"})
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();

		UserService service = new UserService();
		try {
			User user = service.loginByUserNameAndPassword(userName, password);
			//如果登录成功，则将用户信息存储到session中
			request.getSession().setAttribute("user", user);

			//如果是超级用户，就进入到网上书城的后台管理系统，否则进入我的账户页面
			if(StringExt.equalsE(user.getType(), EUser_Type.Admin)) {
				response.sendRedirect(request.getContextPath() + "/admin/home.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
			}
		} catch (LoginException e) {
			//如果出现问题，则跳转回登录页面，显示错误信息
			e.printStackTrace();
			String loginMsg = "<b>登录失败！</b><br/>";
			request.setAttribute("loginMsg", loginMsg + e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		}
	}
}
