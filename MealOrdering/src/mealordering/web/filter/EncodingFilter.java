package mealordering.web.filter;

import org.jetbrains.annotations.NotNull;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 编码过滤器<br/>
 * 统一全站编码，防止项目中的请求和响应出现乱码情况。
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
	public void init(FilterConfig config) {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		//处理请求乱码
		HttpServletRequest myRequest = new MyRequest((HttpServletRequest) request);
		//处理响应乱码
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(myRequest, response);
	}

	public void destroy() {
	}
}

/**
 * 自定义的request对象
 */
class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	private boolean hasEncoding;

	MyRequest(HttpServletRequest request) {
		//必须写
		super(request);
		this.request = request;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		//获得请求方式
		String method = request.getMethod();
		//处理get和post的乱码
		if (method.equalsIgnoreCase("post")) {
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else if (method.equalsIgnoreCase("get")) {
			Map<String, String[]> parameterMap = request.getParameterMap();
			//确保get手动编码逻辑只运行一次
			if (!hasEncoding) {
				for (var parameterName : parameterMap.keySet()) {
					String[] values = parameterMap.get(parameterName);
					if (values != null) {
						for (int i = 0; i < values.length; i++) {
							values[i] = new String(values[i].getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
						}
					}
				}
				hasEncoding = true;
			}
			return parameterMap;
		}
		return super.getParameterMap();
	}

	@Override
	public String getParameter(@NotNull String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		if (values == null)
			return null;
		return values[0];
	}

	@Override
	public String[] getParameterValues(@NotNull String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		return values;
	}
}
