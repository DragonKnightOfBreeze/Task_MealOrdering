package dk_breeze.test.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestEncoding3Servlet", urlPatterns = "/test/testEncoding3")
public class TestEncoding3Servlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		var name = req.getParameter("name");
		resp.getWriter().println(name == null);
		//编码有问题
		resp.getWriter().println(name);
		resp.getWriter().println("发放给奉古国过过");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		var name = req.getParameter("name");
		resp.getWriter().println(name);
		resp.getWriter().println("发放给奉古国过过");
		var out = resp.getWriter();
		out.println(req.getRequestURI() + "<br>");
		out.println(req.getRequestURL() + "<br>");
		out.println(req.getContextPath() + "<br>");
		out.println(req.getRemoteHost() + "<br>");
		out.println(resp.getStatus() + "<br>");
	}
}
