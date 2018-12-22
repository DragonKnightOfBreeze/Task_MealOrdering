/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import dk_breeze.utils.JSONUtils;
import dk_breeze.utils.ext.ArrayExt;
import dk_breeze.utils.ext.StringExt;
import mealordering.domain.NormalUser;
import mealordering.domain.User;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 得到已登录用户信息的Servlet
 * <br>思路：尝试先从cookie中得到用户id，并查询得到完整用户信息。如果为空，则尝试从session获取。
 */
@WebServlet(name = "GetUserServlet", urlPatterns = "/mealordering/getUser")
public class GetUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		User user = null;

		int id = -1;
		//尝试得到cookie
		Cookie[] cookies = req.getCookies();
		if(!ArrayExt.orEmpty(cookies)) {
			for(Cookie cookie : cookies) {
				if(StringExt.equals(cookie.getName(), "userId")) {
					id = StringExt.toInt(cookie.getValue(), -1);
					break;
				}
			}
		}
		//尝试根据cookie得到用户信息
		if(id != -1) {
			try {
				user = ServiceFactory.getNormalUserSvc().findById(id);
			} catch(Exception ignored) {
			}
		}
		//尝试从session中得到用户信息
		if(user == null) {
			user = (NormalUser) req.getSession().getAttribute("user");
		}
		if(user == null) {
			status = "notFound";
		}

		resp.getWriter().println(JSONUtils.of("status", status, "user", user));
	}
}
