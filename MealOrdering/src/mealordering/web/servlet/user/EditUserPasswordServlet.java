package mealordering.web.servlet.user;

import dk_breeze.utils.ext.StringExt;
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
 * 编辑用户密码的Servlet
 */
@WebServlet(name = "EditUserPasswordServlet", urlPatterns = {"/mealordering/resetPassword"})
public class EditUserPasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到表单参数
		int id = StringExt.toInt(req.getParameter("id"));
		String password = req.getParameter("password").trim();
		//声明返回参数
		String status = "success";

		try {
			//更改用户密码，并清空resetCode属性
			ServiceFactory.getNormalUserSvc().doEditPassword(id, password);
			req.getSession().setAttribute("resetCode", null);
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		var data = new JSONObject().put("status", status);
		resp.getWriter().println(data);
	}
}
