package dk_breeze.test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestUseBeanServlet", urlPatterns = {"/test/testUseBean"})
public class TestUseBeanServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		TestAdmin admin = new TestAdmin();
		admin.setName("123");
		admin.setAd("456");

		req.getSession().setAttribute("user", admin);
		req.getSession().setAttribute("ses", "SESSION");

		req.setAttribute("fox", "MyFox");
//		req.getRequestDispatcher("jsp/testUseBean.jsp").forward(req,resp);
		req.getRequestDispatcher("/test/test1/servlet").forward(req, resp);
	}
}
