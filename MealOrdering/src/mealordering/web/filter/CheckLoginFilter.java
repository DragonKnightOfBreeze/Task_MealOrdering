/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package mealordering.web.filter;


import mealordering.domain.User;
import mealordering.enums.Identity;
import windea.ext.StringExt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录验证的过滤器
 * NOTE 不过启用与否，都仍然存在乱码问题
 */
@WebFilter(filterName = "CheckLoginFilter", urlPatterns = {"/mealordering/account/*", "/mealordering/settings/*"})
public class CheckLoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	throws IOException, ServletException {
		//强制转换
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//如果已登录且不是管理员则通过，如果是管理员则重定向到管理员页，否则重定向到错误页
		User user = (User) request.getSession().getAttribute("onlineUser");
		if(user != null && !StringExt.equalsE(user.getType(), Identity.admin)) {
			chain.doFilter(request, response);
		} else if(user != null && StringExt.equalsE(user.getType(), Identity.admin)) {
			response.sendRedirect(request.getContextPath() + "/mealordering/admin/welcome.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/mealordering/error/login-state-error.jsp");
		}
	}

	@Override
	public void destroy() {
	}

}
