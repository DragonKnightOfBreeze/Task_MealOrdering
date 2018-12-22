package mealordering.web.servlet.user;

import dk_breeze.utils.JSONUtils;
import mealordering.domain.NormalUser;
import mealordering.domain.PageGroup;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 后台查询所有用户的Servlet
 */
@WebServlet(name = "FindAllUsersServlet", urlPatterns = {"/mealordering/admin/findAllUsers"})
public class FindAllUsersServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//声明返回参数
		String status = "success";
		List<NormalUser> userPage = null;
		String[] pageBtnText = null;

		try {
			PageGroup<NormalUser> pageGroup = new PageGroup<>(ServiceFactory.getNormalUserSvc().findAll(), 1);
			req.getSession().setAttribute("pageGroup", pageGroup);
			userPage = pageGroup.getPage(1);
			pageBtnText = pageGroup.getPageBtnText();
		} catch(ResultEmptyException e) {
			e.printStackTrace();
			status = "empty";
		} catch(SQLException e) {
			e.printStackTrace();
			status = "error";
		}

		resp.getWriter().println(JSONUtils.of("status", status, "userPage", userPage, "pageBtnText", pageBtnText));
	}

}
