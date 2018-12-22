/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import dk_breeze.utils.FileUtils;
import dk_breeze.utils.UUIDUtils;
import mealordering.domain.NormalUser;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注册的Servlet
 * <br>如果成功，则跳转到注册成功页，如果出现问题，则跳转回注册页面，显示错误信息。
 * <br>INFO 不使用Ajax。
 */
@WebServlet(name = "registerServlet", urlPatterns = {"/mealordering/register"})
public class RegisterServlet extends HttpServlet {
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

			String userName = su.getRequest().getParameter("userName");
			String password = su.getRequest().getParameter("password");
			String gender = su.getRequest().getParameter("gender");
			String email = su.getRequest().getParameter("email");
			String phoneNum = su.getRequest().getParameter("phoneNum");
			String introduce = su.getRequest().getParameter("introduce");

			//缓存上传图片
			String fileName = su.getFiles().getFile(0).getFileName();
			String ext = su.getFiles().getFile(0).getFileExt();
			String imgUrl = FileUtils.join(getServletContext().getRealPath("/mealordering/images/user_img"),
					FileUtils.getRandomFileName(fileName));
			su.getFiles().getFile(0).saveAs(imgUrl);

			NormalUser user = new NormalUser(userName, password, imgUrl, gender, email, phoneNum, introduce);
			user.setActiveCode(UUIDUtils.getUUID());

			ServiceFactory.getNormalUserSvc().doRegister(user);
			//NOTE 测试用代码
			ServiceFactory.getNormalUserSvc().doActive(user.getActiveCode(), 1);
			resp.sendRedirect(req.getContextPath() + "/mealordering/register-success.html");
		} catch(SmartUploadException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/upload-error.html");
		} catch(Exception e) {
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/register-fail.html");
		}
	}
}
