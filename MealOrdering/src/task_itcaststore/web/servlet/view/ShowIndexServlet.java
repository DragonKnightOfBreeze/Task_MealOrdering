package task_itcaststore.web.servlet.view;

import task_itcaststore.domain.Notice;
import task_itcaststore.service.NoticeService;
import task_itcaststore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 前台页面展示的servlet
 * <ol>
 * <li>展示最新添加或修改的一条公告。</li>
 * <li>展示本周热销商品。</li>
 * </ol>
 */

@WebServlet(name = "ShowIndexServlet", urlPatterns = {"/showIndex"})
public class ShowIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//查询最近一条公告，传递到index.jsp页面进行展示
		NoticeService nService = new NoticeService();
		Notice notice = nService.getRecentNotice();
		request.setAttribute("notice", notice);

		//查询本周热销的两条商品，传递到index.jsp页面进行展示
		ProductService pService = new ProductService();
		List<Object[]> productList = pService.getWeekHotProducts();
		request.setAttribute("productList", productList);

		//请求转发
		request.getRequestDispatcher("/client/index.jsp").forward(request, response);
	}
}
