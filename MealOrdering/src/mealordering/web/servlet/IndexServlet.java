package mealordering.web.servlet;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Notice;
import mealordering.domain.User;
import mealordering.domain.WeekHotMeal;
import mealordering.exception.ResultEmptyException;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 前台页面，显示主页的servlet
 * <ol>
 * <li>展示最新添加或修改的一条公告。</li>
 * <li>展示本周热销商品。</li>
 * </ol>
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/mealordering/index"})
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//STEP 后台操作
			//查询最近的一条公告
			Notice notice = ServiceFactory.getNoticeSvc().findRecent(1).get(0);
			//查询本周热销的两条商品
			List<WeekHotMeal> weekHotMeals = ServiceFactory.getMealSvc().getWeekHotMeals(2);
			//检查cookie，设置session.onlineUser
			int userId = -1;
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie : cookies) {
				if(StringExt.equals(cookie.getName(), "userId")) {
					userId = StringExt.toInt(cookie.getValue(), -1);
				}
			}
			if(userId != -1) {
				User user = ServiceFactory.getNormalUserSvc().findById(userId);
				req.getSession().setAttribute("onlineUser", user);
			}
			//STEP 设置转发属性与跳转
			req.setAttribute("notice", notice);
			req.setAttribute("weekHotMeals", weekHotMeals);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		} catch(ResultEmptyException | SQLException e) {
			e.printStackTrace();
//			resp.sendRedirect(req.getContextPath()+"/mealordering/error/empty-result.jsp");
		}
	}
}
