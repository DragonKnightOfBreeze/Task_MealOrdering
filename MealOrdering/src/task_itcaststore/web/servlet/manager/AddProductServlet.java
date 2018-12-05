package task_itcaststore.web.servlet.manager;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import task_itcaststore.domain.Product;
import task_itcaststore.exception.AddProductException;
import task_itcaststore.service.ProductService;
import task_itcaststore.utils.FileUploadUtils;
import task_itcaststore.utils.IdUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台添加商品的Servlet
 * @noinspection ResultOfMethodCallIgnored
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/manager/doGenerate"})
public class AddProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建产品对象
		Product product = new Product();
		//封装需要的参数
		Map<String, String> map = new HashMap<>();
		map.put("id", IdUtils.getUUID());

		//TODO 重构
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		//设置临时文件存储位置
		dfif.setRepository(new File(this.getServletContext().getRealPath("/temp")));
		//设置上传文件的缓存大小为10m
		dfif.setSizeThreshold(1024 * 1024 * 10);
		//创建上传组件
		ServletFileUpload upload = new ServletFileUpload(dfif);
		//处理上传文件中文乱码
		upload.setHeaderEncoding("utf-8");

		//复制缓存到本地
		try {
			//解析request得到所有的FileItem
			List<FileItem> items = upload.parseRequest(request);
			//遍历所有FileItem
			for (FileItem item : items) {
				if (item.isFormField()) {
					//如果不是上传组件
					//获取组件名称
					String fieldName = item.getFieldName();
					//解决乱码问题
					String value = item.getString("utf-8");
					map.put(fieldName, value);
				} else {
					//如果是上传组件
					//得到上传文件真实名称
					String fileName = FileUploadUtils.getFileName(item.getName());
					//得到随机名称
					String randomName = FileUploadUtils.generateRandomFileName(fileName);
					//得到随机目录
					String randomDir = FileUploadUtils.generateRandomDir(randomName);
					//得到相对的图片存储父目录
					String imgUrl_parent = "/productImg" + randomDir;
					//得到绝对的存储父目录对应的文件对象
					File parentDir = new File(this.getServletContext().getRealPath(imgUrl_parent));
					//验证目录是否存在，如果不存在，创建出来
					if (!parentDir.exists())
						parentDir.mkdirs();

					String imgUrl = imgUrl_parent + "/" + randomName;
					map.put("imgUrl", imgUrl);

					IOUtils.copy(item.getInputStream(), new FileOutputStream(new File(parentDir, randomName)));
					item.delete();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			response.getWriter().println("警告：添加商品信息！");
		}

		//将数据封装到产品对象中
		try {
			BeanUtils.populate(product, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			response.getWriter().println("警告：添加商品信息！");
		}

		//添加产品并重定向地址
		ProductService service = new ProductService();
		try {
			//调用service完成添加商品操作
			service.addProduct(product);

			response.sendRedirect(request.getContextPath() + "/listProduct");
		} catch (AddProductException e) {
			e.printStackTrace();
			response.getWriter().println("警告：添加商品信息！");
		}
	}
}
