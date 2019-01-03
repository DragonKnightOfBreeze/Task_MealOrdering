package mealordering.web.servlet;

import windea.utils.ext.ArrayExt;
import windea.utils.ext.StringExt;
import mealordering.domain.Notice;
import mealordering.domain.User;
import mealordering.domain.WeekHotMeal;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 前台页面，显示主页的servlet
 * <ol>
 * <li>展示最新添加或修改的一条公告。</li>
 * <li>展示本周热销商品。</li>
 * </ol>
 */
@WebServlet(name = "IndexServlet", urlPatterns = "/mealordering/index")
public class IndexServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 后台操作
		//查询最近的一条公告
		Notice notice = null;
		try {
			notice = ServiceFactory.getNoticeSvc().findRecent(1).get(0);
		} catch(Exception e) {
			e.printStackTrace();
		}
		//查询本周热销的两条商品
		List<WeekHotMeal> weekHotMeals = null;
		try {
			weekHotMeals = ServiceFactory.getMealSvc().getWeekHotMeals(2);
		} catch(Exception e) {
			e.printStackTrace();
		}
		//STEP 检查cookie，设置session.onlineUser
		int userId = -1;
		Cookie[] cookies = req.getCookies();
		if(!ArrayExt.orLessE(cookies, 1)) {
			for(Cookie cookie : cookies) {
				if(StringExt.equals(cookie.getName(), "userId")) {
					userId = StringExt.toInt(cookie.getValue(), -1);
				}
			}
		}
		if(userId != -1) {
			try {
				User user = ServiceFactory.getNormalUserSvc().findById(userId);
				req.getSession().setAttribute("onlineUser", user);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		//STEP 设置转发属性与跳转
		req.setAttribute("notice", notice);
		req.setAttribute("weekHotMeals", weekHotMeals);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
