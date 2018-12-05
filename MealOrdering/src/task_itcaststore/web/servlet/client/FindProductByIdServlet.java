package task_itcaststore.web.servlet.client;

import task_itcaststore.domain.Product;
import task_itcaststore.domain.enums.EUserType;
import task_itcaststore.exception.FindProductByIdException;
import task_itcaststore.service.ProductService;
import task_itcaststore.utils.ext.StringExt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据商品id查找指定商品信息的servlet
 */
@WebServlet(name = "FindProductByIdServlet", urlPatterns = {"/findProductById"})
public class FindProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到商品的id
		String id = request.getParameter("id").trim();
		//获取得到用户类型
		String type = request.getParameter("type").trim();

		ProductService service = new ProductService();
		try {
			Product product = service.findProductById(id);

			if (StringExt.equalsE(type, EUserType.Admin)) {
				//如果是超级用户，则调用service层方法，根据id查找商品
				request.setAttribute("product", product);
				request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			} else {
				//如果是普通用户，则直接跳转到info.jsp页面
				request.getRequestDispatcher("/client/info.jsp").forward(request, response);
			}
		} catch (FindProductByIdException e) {
			e.printStackTrace();
			response.getWriter().println("警告：查找商品信息！");
		}
	}
}
