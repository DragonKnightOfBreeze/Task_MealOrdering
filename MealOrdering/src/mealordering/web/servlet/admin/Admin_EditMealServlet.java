package mealordering.web.servlet.admin;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import dkbreeze.utils.FileUtils;
import dkbreeze.utils.ext.StringExt;
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
 */
@WebServlet(name = "Admin_EditMealServlet", urlPatterns = "/mealordering/admin/edit-meal")
public class Admin_EditMealServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//STEP 解析请求表单，得到文件和表单参数
			SmartUpload su = new SmartUpload();
			su.initialize(this.getServletConfig(), req, resp);
			su.setMaxFileSize(3 * 1024 * 1024);
			su.setAllowedFilesList("jpg,png,gif");
			su.upload();
			//STEP 得到传入参数
			String id = su.getRequest().getParameter("id");
			String name = su.getRequest().getParameter("name");
			double price = StringExt.toDouble(su.getRequest().getParameter("price"));
			String category = su.getRequest().getParameter("category");
			String description = su.getRequest().getParameter("description");
			int count = StringExt.toInt(su.getRequest().getParameter("count"));
			String imgUrl = "";
			//STEP 缓存上传图片（如果有的话）
			if(su.getFiles().getCount() > 0) {
				String fileName = su.getFiles().getFile(0).getFileName();
				imgUrl = "/mealordering/assets/image/meal_img" + FileUtils.getRandomFileName(fileName);
				su.getFiles().getFile(0).saveAs(getServletContext().getRealPath("/") + imgUrl);
			}
			//STEP 后台操作
			Meal meal = new Meal(name, price, category, imgUrl, description, count);
			meal.setId(id);
			ServiceFactory.getMealSvc().doEdit(meal);
			//STEP 设置转发属性与跳转
			req.setAttribute("id", id);
			req.getRequestDispatcher("/mealordering/admin/find-meal").forward(req, resp);
		} catch(SmartUploadException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/upload-error.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}

}