package mealordering.web.filter;


import dkbreeze.utils.ext.StringExt;
import mealordering.domain.User;
import mealordering.enums.Identity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户权限验证的过滤器<br>
 * 判断用户权限，如果不是管理员，则重定向到错误页。
 */
@WebFilter(filterName = "CheckAdminFilter", urlPatterns = "/mealordering/admin/*")
public class CheckAdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	throws IOException, ServletException {
		//强制转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//判断是否具有权限
		User user = (User) request.getSession().getAttribute("onlineUser");
		//如果用户是管理员则通过，否则重定向到登录失败页
		if(user != null && StringExt.equalsE(user.getType(), Identity.admin)) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/mealordering/error/identity-error.jsp");
		}
	}

	@Override
	public void destroy() {
	}

}
