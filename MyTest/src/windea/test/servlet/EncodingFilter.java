package windea.test.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 编码过滤器
 * <br> 统一全站编码，防止项目中的请求和响应出现乱码情况。
 */
@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/test/*"})
public class EncodingFilter implements Filter {
	public void init(FilterConfig config) {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
	throws ServletException, IOException {
//		HttpServletRequest myRequest = new MyRequest((HttpServletRequest) req);
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		chain.doFilter(req, resp);
	}

	public void destroy() {
	}
}
//}
//
///**
// * 自定义的request对象
// * @noinspection Duplicates
// */
//class MyRequest extends HttpServletRequestWrapper {
//	private HttpServletRequest request;
//	private boolean hasEncoding;
//
//	public MyRequest(HttpServletRequest req) {
//		//必须写
//		super(req);
//		this.request = req;
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override
//	public Map<String, String[]> getParameterMap() {
//		//获得请求方式
//		String method = request.getMethod();
//		//处理get和post的乱码
//		if (method.equalsIgnoreCase("post")) {
//			try {
//				request.setCharacterEncoding("utf-8");
//				return request.getParameterMap();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
////		else if (method.equalsIgnoreCase("get")) {
////			Map<String, String[]> parameterMap = new HashMap<>();
////			//确保get手动编码逻辑只运行一次
////			if (!hasEncoding) {
////				for (String parameterName : request.getParameterMap().keySet()) {
////					String[] values = request.getParameterMap().get(parameterName);
////					if (values != null) {
////						for (int i = 0; i < values.length; i++) {
////							values[i] = new String(values[i].getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
////						}
////					}
////					parameterMap.put(parameterName,values);
////				}
////				hasEncoding = true;
////			}else{
////				parameterMap = request.getParameterMap();
////			}
////			return parameterMap;
////		}
//		return super.getParameterMap();
//	}
//
//	@Override
//	public String getParameter(@NotNull String name) {
//		Map<String, String[]> parameterMap = getParameterMap();
//		String[] values = parameterMap.get(name);
//		if (values == null)
//			return null;
//		return values[0];
//	}
//
//	@Override
//	public String[] getParameterValues(@NotNull String name) {
//		Map<String, String[]> parameterMap = getParameterMap();
//		return parameterMap.get(name);
//	}
//}
