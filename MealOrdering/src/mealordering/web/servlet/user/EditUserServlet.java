/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.user;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import dk_breeze.utils.FileUtils;
import mealordering.domain.NormalUser;
import mealordering.service.NormalUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编辑用户信息的Servlet
 * <br>INFO 不使用Ajax。
 */
@WebServlet(name = "EditUserServlet", urlPatterns = {"/mealordering/settings/editInformation"})
public class EditUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//解析请求表单，得到表单参数和文件地址
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(), req, resp);
		su.setMaxFileSize(3 * 1024 * 1024);
		su.setAllowedFilesList("jpg,png,gif");
		try {
			su.upload();

			//得到表单参数
			int id = Integer.parseInt(req.getParameter("userId").trim());
			String userName = req.getParameter("userName").trim();
			String password = req.getParameter("password").trim();
			String gender = req.getParameter("gender").trim();
			String email = req.getParameter("email").trim();
			String phoneNum = req.getParameter("phoneNum").trim();
			String introduce = req.getParameter("introduce").trim();

			//缓存上传图片
			String fileName = su.getFiles().getFile(0).getFileName();
			String ext = su.getFiles().getFile(0).getFileExt();
			String imgUrl = FileUtils.join(getServletContext().getRealPath("/mealordering/_upload"),
					FileUtils.getRandomFileName(fileName));
			su.getFiles().getFile(0).saveAs(imgUrl);

			NormalUser user = new NormalUser(userName, password, imgUrl, gender, email, phoneNum, introduce);
			user.setId(id);
			NormalUserService service = new NormalUserService();
			service.doEdit(user);

			req.getSession().setAttribute("user", user);
			resp.sendRedirect(req.getContextPath() + "/mealordering/settings/information.html");
		} catch(SmartUploadException e) {
			e.printStackTrace();
			//如果出现错误则跳转到错误页
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/upload-error.html");
		} catch(Exception e) {
			e.printStackTrace();
			//如果出现错误则跳转到错误页
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpectedError.html");
		}
	}
}
