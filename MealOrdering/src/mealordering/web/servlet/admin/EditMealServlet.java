package mealordering.web.servlet.admin;

import mealordering.domain.Meal;
import mealordering.service.MealService;
import mealordering.utils.FileUploadUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台用于编辑商品信息的Servlet
 * @noinspection ResultOfMethodCallIgnored
 */
@WebServlet(name = "EditMealServlet", urlPatterns = {"/mealordering/admin/editMeal"})
public class EditMealServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取表单参数
		Map<String, String> map = new HashMap<>();


		//解析请求表单，得到参数图表和文件地址
		FileUploadUtils fuUtils;
		try {
			fuUtils = new FileUploadUtils(request);
			map.putAll(fuUtils.getParamMapByOne());
			String imgUrl = fuUtils.saveAll("/image/mealImage", true).get(0);
			map.put("imgUrl", imgUrl);
		} catch(Exception e) {
			e.printStackTrace();
			response.getWriter().println("警告：编辑餐品信息失败！");
		}

		//将数据封装到产品对象中
		Meal meal = new Meal();
		try {
			BeanUtils.populate(meal, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			response.getWriter().println("警告：编辑商品信息失败！");
		}
		MealService service = new MealService();
		service.doEdit(meal);

		response.sendRedirect(request.getContextPath() + "/listMeals");
	}

}
