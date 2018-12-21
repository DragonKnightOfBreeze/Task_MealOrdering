/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.user;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.NormalUser;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 根据id查询用户信息的Servlet
 */
@WebServlet(name = "FindUserByIdServlet", urlPatterns = {"/mealordering/admin/findUserById"})
public class FindUserByIdServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		int id = StringExt.toInt(req.getParameter("id"));
		//声明返回参数
		String status = "success";
		NormalUser user = null;

		try {
			user = ServiceFactory.getNormalUserSvc().findById(id);
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		var data = new JSONObject().put("status", status).put("user", user);
		resp.getWriter().println(data);
	}
}
