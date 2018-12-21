package mealordering.web.servlet.meal;

import mealordering.service.ServiceFactory;

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
@WebServlet(name = "DownloadSalesListServlet", urlPatterns = {"/mealordering/admin/downloadSalesList"})
public class DownloadSalesListServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//得到传入参数
		String year = req.getParameter("year").trim();
		String month = req.getParameter("month").trim();

		//设置下载链接
		String fileName = year + "年" + month + "月销售榜单.csv";
		fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "iso8859-1");
		resp.setContentType(this.getServletContext().getMimeType(fileName));
		resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		try {
			List<Object[]> salesList = ServiceFactory.getMealSvc().getSalesList(year, month);
			out.println("商品名称,销售数量");
			for(Object[] arr : salesList) {
				out.println(arr[0] + "," + arr[1]);
			}
			out.flush();
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
			out.println("获取销售表单时发生了错误。");
		}
	}
}
