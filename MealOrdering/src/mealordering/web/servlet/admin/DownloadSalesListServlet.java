package mealordering.web.servlet.admin;

import mealordering.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 下载销售榜单的Servlet
 */
@WebServlet(name = "DownloadSalesListServlet", urlPatterns = {"/admin/downloadSalesList"})
public class DownloadSalesListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到表单参数
		String year = request.getParameter("year").trim();
		String month = request.getParameter("month").trim();

		//设置下载链接
		String fileName = year + "年" + month + "月销售榜单.csv";
		response.setContentType(this.getServletContext().getMimeType(fileName));
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), "iso8859-1"));
		response.setCharacterEncoding("utf-8");

		//写入文件内容
		MealService service = new MealService();
		List<Object[]> ps = service.getSalesList(year, month);
		PrintWriter out = response.getWriter();
		out.println("商品名称,销售数量");
		for (Object[] arr : ps) {
			out.println(arr[0] + "," + arr[1]);
		}
		out.flush();
		out.close();
	}
}
