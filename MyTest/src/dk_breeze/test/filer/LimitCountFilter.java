/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */
package dk_breeze.test.filer;/*
 * Copyright (c) 2018.  @DragonKnightOfBreeze / @微风的龙骑士 风游迩
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "dk_breeze.test.filer.LimitCountFilter")
public class LimitCountFilter implements Filter {
	public void init(FilterConfig config) {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		var text = req.getParameter("text");
		if(text.length() > 10) {
			resp.getWriter().println("文件长度超过限制！");
		} else {
			resp.getWriter().println("文件长度没有超过限制！");
		}
		chain.doFilter(req, resp);
	}

	public void destroy() {
	}
}
