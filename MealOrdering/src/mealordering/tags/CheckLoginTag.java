package mealordering.tags;

import mealordering.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 用户登录验证的标签 <br/>
 * 尝试从session得到用户，如果失败，则重定向到错误页。
 */
public class CheckLoginTag extends SimpleTagSupport {
	@Override
	public void doTag() throws IOException {
		PageContext context = (PageContext) this.getJspContext();
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getResponse();

		User user = (User) context.getSession().getAttribute("user");
		if (user == null)
			response.sendRedirect(request.getContextPath() + "/client/error/privilege.jsp");
	}
}
