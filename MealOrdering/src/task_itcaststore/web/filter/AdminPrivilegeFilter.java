package task_itcaststore.web.filter;

import task_itcaststore.domain.User;
import task_itcaststore.domain.enums.EUserType;
import task_itcaststore.utils.ext.StringExt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断用户权限的过滤器
 */
@WebFilter(filterName = "AdminPrivilegeFilter", urlPatterns = {"/admin/*"})
public class AdminPrivilegeFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//强制转换
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//判断是否具有权限
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		//如果有权限，则放行
		if (user != null && StringExt.equalsE(user.getType(), EUserType.Admin)) {
			chain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		//否则重定向到登录失败页
		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/error/privilege.jsp");
	}

	public void destroy() {
	}

}
