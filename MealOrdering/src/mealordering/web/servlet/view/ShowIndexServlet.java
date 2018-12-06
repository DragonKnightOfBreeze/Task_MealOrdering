package mealordering.web.servlet.view;

import mealordering.domain.Notice;
import mealordering.service.MealService;
import mealordering.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet(name = "ShowIndexServlet", urlPatterns = {"/showIndex"})
public class ShowIndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询最近的一条公告
		NoticeService noticeService = new NoticeService();
		Notice notice = noticeService.findRecent(1);
		//查询本周热销的两条商品
		MealService mealService = new MealService();
		List<Object[]> weekHotMeals = mealService.getWeekHotMeals(2);

		request.setAttribute("notice", notice);
		request.setAttribute("weekHotMeals", weekHotMeals);
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}
}
