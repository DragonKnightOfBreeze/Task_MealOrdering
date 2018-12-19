/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import mealordering.domain.NormalUser;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 得到已登录用户信息的Servlet
 */
@WebServlet(name = "GetUserServlet", urlPatterns = "/mealordering/getUser")
public class GetUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NormalUser user = (NormalUser) req.getSession().getAttribute("user");
		JSONObject data = new JSONObject(user);
		resp.getWriter().println(data);
	}
}
