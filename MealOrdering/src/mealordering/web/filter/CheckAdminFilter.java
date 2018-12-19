package mealordering.web.filter;


import dk_breeze.utils.ext.StringExt;
import mealordering.domain.NormalUser;
import mealordering.domain.enums.EUser_Type;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户权限验证的过滤器<br/>
 * 判断用户权限，如果不是管理员，则重定向到错误页。
 */
@WebFilter(filterName = "CheckAdminFilter", urlPatterns = {"/mealordering/admin/*"})
public class CheckAdminFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		//强制转换
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
		//判断是否具有权限
		NormalUser user = (NormalUser) httpServletRequest.getSession().getAttribute("user");
		if(user != null && StringExt.equalsE(user.getType(), EUser_Type.Admin)) {
			chain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		//否则重定向到登录失败页
		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error/identityError.jsp");
	}

	public void destroy() {
	}

}
