package dkbreeze.test.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestDispatchServlet", urlPatterns = "/test/test1/servlet")
public class ServletSevlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		resp.getWriter().println("Hello world");
	}
}
