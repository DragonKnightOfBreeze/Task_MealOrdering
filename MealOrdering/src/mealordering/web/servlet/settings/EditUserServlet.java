/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.settings;

import mealordering.domain.NormalUser;
import mealordering.service.NormalUserService;

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

		NormalUser user = new NormalUser();
		user.setId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setGender(gender);
		user.setEmail(email);
		user.setPhoneNum(phoneNum);
		user.setIntroduce(introduce);
		NormalUserService service = new NormalUserService();
		service.doEdit(user);
		request.getSession().setAttribute("user", user);

		response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
	}


}
