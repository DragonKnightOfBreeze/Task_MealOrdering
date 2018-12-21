package mealordering.web.servlet.meal;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import dk_breeze.utils.FileUtils;
import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Meal;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 后台用于编辑商品信息的Servlet
 * <br>INFO 不使用Ajax。
 */
@WebServlet(name = "EditMealServlet", urlPatterns = {"/mealordering/admin/editMeal"})
public class EditMealServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解析请求表单，得到表单参数和文件地址
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(), req, resp);
		su.setMaxFileSize(3 * 1024 * 1024);
		su.setAllowedFilesList("jpg,png,gif");

		//如果没有出错，则跳转到餐品列表页，否则跳转到对应的错误页
		try {
			su.upload();

			String name = su.getRequest().getParameter("name");
			double price = StringExt.toDouble(su.getRequest().getParameter("price"));
			String category = su.getRequest().getParameter("category");
			String description = su.getRequest().getParameter("description");
			int count = StringExt.toInt(su.getRequest().getParameter("count"));

			String fileName = su.getFiles().getFile(0).getFileName();
			String ext = su.getFiles().getFile(0).getFileExt();
			String imgUrl = FileUtils.join(getServletContext().getRealPath("/mealordering/assets/image/meal_img"),
					FileUtils.getRandomFileName(fileName));
			su.getFiles().getFile(0).saveAs(imgUrl);

			Meal meal = new Meal(name, price, category, imgUrl, description, count);
			ServiceFactory.getMealSvc().doEdit(meal);

			resp.sendRedirect(req.getContextPath() + "/mealordering/admin/meal-list.html");
		} catch(SmartUploadException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/upload-error.html");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.html");
		}
	}

}
