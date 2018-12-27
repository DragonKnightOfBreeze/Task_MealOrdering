/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package mealordering.web.servlet;

import dkbreeze.utils.CheckUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 得到验证码的Servlet
 */
@WebServlet(name = "GetCheckImgServlet", urlPatterns = "/mealordering/get-check-img")
public class GetCheckImgServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//STEP 首先就要得到session对象
		HttpSession session = req.getSession();
		//STEP 设置内容类型为图片，设置浏览器不要缓存此图片
		resp.setContentType("image/jpeg");
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		//STEP 将图像输出到客户端
		char[] checkCode = CheckUtils.getCheckCode(4);
		ImageIO.write(CheckUtils.getCheckImg(checkCode), "jpeg", resp.getOutputStream());
		//STEP 将当前验证码存入到Session中
		session.setAttribute("checkCode", checkCode);
	}
}
