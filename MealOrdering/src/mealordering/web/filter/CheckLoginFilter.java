package mealordering.web.filter;


import dk_breeze.utils.ext.StringExt;
import mealordering.domain.User;
import mealordering.enums.Identity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录验证的过滤器
 */
@WebFilter(filterName = "CheckLoginFilter", urlPatterns = {"/mealordering/account/*", "/mealordering/settings/*"})
public class CheckLoginFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		//强制转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//如果已登录，且不是管理员，则通过，否则重定向到错误页
		User user = (User) request.getSession().getAttribute("user");
		if(user != null && !StringExt.equalsE(user.getType(), Identity.Admin)) {
			chain.doFilter(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/error/login-state-error.html");
	}

	public void destroy() {
	}

}
