/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet.settings;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import dk_breeze.utils.FileUtils;
import mealordering.domain.NormalUser;
import mealordering.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编辑用户信息的Servlet
 */
@WebServlet(name = "EditUserServlet", urlPatterns = "/mealordering/settings/edit-information")
public class EditUserServlet extends HttpServlet {
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
			int id = Integer.parseInt(req.getParameter("userId").trim());
			String userName = req.getParameter("userName").trim();
			String password = req.getParameter("password").trim();
			String gender = req.getParameter("gender").trim();
			String email = req.getParameter("email").trim();
			String phoneNum = req.getParameter("phoneNum").trim();
			String introduce = req.getParameter("introduce").trim();
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
			user.setId(id);
			ServiceFactory.getNormalUserSvc().doEdit(user);
			//STEP 设置转发属性与跳转
			req.getSession().setAttribute("onlineUser", user);
			resp.sendRedirect(req.getContextPath() + "/mealordering/settings/information.jsp");
		} catch(SmartUploadException e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/upload-error.jsp");
		} catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/mealordering/error/unexpected-error.jsp");
		}
	}
}
