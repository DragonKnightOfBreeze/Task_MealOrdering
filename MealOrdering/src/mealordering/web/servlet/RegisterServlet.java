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
import java.util.Date;

/**
 * 注册的Servlet
 * <br>如果成功，则跳转到注册成功页，如果出现问题，则跳转回注册页面，显示错误信息。
 */
@WebServlet(name = "registerServlet", urlPatterns = "/mealordering/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//STEP 解析请求表单，得到文件和表单参数
			SmartUpload su = new SmartUpload();
			su.initialize(this.getServletConfig(), req, resp);
			su.setMaxFileSize(3 * 1024 * 1024);
			su.setAllowedFilesList("jpg,png,gif");
			su.upload();
			//STEP 得到传入参数
			String userName = su.getRequest().getParameter("userName");
			String password = su.getRequest().getParameter("password");
			String gender = su.getRequest().getParameter("gender");
			String email = su.getRequest().getParameter("email");
			String phoneNum = su.getRequest().getParameter("phoneNum");
			String introduce = su.getRequest().getParameter("introduce");
			String imgUrl = "";
			//STEP 缓存上传图片（如果有的话）
			if(su.getFiles().getCount() > 0) {
				String fileName = su.getFiles().getFile(0).getFileName();
				String ext = su.getFiles().getFile(0).getFileExt();
				imgUrl = FileUtils.join(getServletContext().getRealPath("/mealordering/assets/image/user_img"),
						FileUtils.getRandomFileName(fileName));
				su.getFiles().getFile(0).saveAs(imgUrl);
			}
			//STEP 后台操作
			NormalUser user = new NormalUser(userName, password, imgUrl, gender, email, phoneNum, introduce);
			user.setRegisterTime(new Date());
			user.setActiveCode(UUIDUtils.getUUID());
			ServiceFactory.getNormalUserSvc().doRegister(user);
			//NOTE 测试用代码
			ServiceFactory.getNormalUserSvc().doActive(user.getActiveCode(), 1);
			//STEP 设置转发属性与跳转
			resp.sendRedirect(req.getContextPath() + "/mealordering/register-success.jsp");
		} catch(SmartUploadException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/upload-error.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/register-fail.jsp");
		}
	}
}
