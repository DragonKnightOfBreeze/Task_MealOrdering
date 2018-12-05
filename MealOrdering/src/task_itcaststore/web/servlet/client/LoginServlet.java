package task_itcaststore.web.servlet.client;

import task_itcaststore.domain.User;
import task_itcaststore.domain.enums.EUserType;
import task_itcaststore.service.UserService;
import task_itcaststore.utils.ext.StringExt;

import javax.security.auth.login.LoginException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取登录页面输入的用户名与密码
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();

		//2.调用service完成登录操作。
		UserService service = new UserService();
		try {
			User user = service.login(userName, password);
			//3.登录成功，将用户存储到session中.
			request.getSession().setAttribute("user", user);

			if (StringExt.equalsE(user.getType(), EUserType.Admin)) {
				//如果是超级用户，就进入到网上书城的后台管理系统
				response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
			} else {
				//如果是普通用户，则进入我的账户页面
				response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
			}
		} catch (LoginException e) {
			//如果出现问题，将错误信息存储到request范围，并跳转回登录页面显示错误信息
			e.printStackTrace();
			request.setAttribute("register_message", "<b>登录失败！</b><br/>" + e.getMessage());
			request.getRequestDispatcher("/client/login.jsp").forward(request, response);
		}
	}
}
