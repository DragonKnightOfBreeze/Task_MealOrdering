package mealordering.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 编码过滤器
 * <br> 统一全站编码，防止项目中的请求和响应出现乱码情况。
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
	public void init(FilterConfig config) {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		chain.doFilter(req, resp);
	}

	public void destroy() {
	}
}
