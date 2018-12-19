package mealordering.web.servlet.admin;

import com.jspsmart.upload.SmartUpload;
import dk_breeze.utils.FileUtils;
import dk_breeze.utils.UUIDUtils;
import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Meal;
import mealordering.service.MealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加餐品的Servlet
 */
@WebServlet(name = "AddMealServlet", urlPatterns = {"/mealordering/admin/addMeal"})
public class AddMealServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解析请求表单，得到表单参数和文件地址
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(), req, resp);
		su.setMaxFileSize(3 * 1024 * 1024);
		su.setAllowedFilesList("jpg,png,gif");
		try {
			su.upload();

			String id = UUIDUtils.getUUID();
			String name = su.getRequest().getParameter("name");
			double price = StringExt.toDouble(su.getRequest().getParameter("price"));
			String category = su.getRequest().getParameter("category");
			String description = su.getRequest().getParameter("description");
			int count = StringExt.toInt(su.getRequest().getParameter("count"));

			String fileName = su.getFiles().getFile(0).getFileName();
			String ext = su.getFiles().getFile(0).getFileExt();
			String imgUrl = FileUtils.join(getServletContext().getRealPath("/mealordering/_upload"),
					FileUtils.getRandomFileName(fileName));
			su.getFiles().getFile(0).saveAs(imgUrl);

			Meal meal = new Meal(id, name, price, category, imgUrl, description, count);
			MealService service = new MealService();
			service.doAdd(meal);

			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/mealList.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			//如果出现错误则跳转到错误页
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/uploadError.jsp");
		}
	}
}
