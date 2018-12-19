/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import dk_breeze.utils.UUIDUtils;
import mealordering.domain.NormalUser;
import mealordering.exception.RegisterException;
import mealordering.service.NormalUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册的Servlet
 * <br>如果成功，则跳转到注册成功页，如果出现问题，则跳转回注册页面，显示错误信息
 */
@WebServlet(name = "registerServlet", urlPatterns = {"/mealordering/register"})
public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String imgUrl = request.getParameter("imgUrl");
		String email = request.getParameter("email");
		String phoneNum = request.getParameter("phoneNum");
		String introduce = request.getParameter("introduce");

		NormalUser user = new NormalUser(userName, password, imgUrl, gender, email, phoneNum, introduce);
		user.setActiveCode(UUIDUtils.getUUID());
		NormalUserService service = new NormalUserService();

		try {
			service.doRegister(user);
			//NOTE 测试用代码
			service.doActive(user.getActiveCode(), 1);
			response.sendRedirect(request.getContextPath() + "/mealordering/registerSuccess.jsp");
		} catch(RegisterException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/mealordering/registerFail.jsp");
		}
	}
}
