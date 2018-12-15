package dk_breeze.test.jtl;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;


/**
 * 遍历列表元素的标签
 * /*
 * 使用：
 * <test:forEach id="url" name="all" scope="request">
 * <h2>网站：${url}</h2>
 * </test:forEach>
 */
public class SimpleForEachTag extends SimpleTagSupport {
	/** 属性名称 */
	private String id;
	/** 属性内容 */
	private String name;
	/** 属性范围 */
	private String scope;

	@Override
	public void doTag() throws JspException, IOException {
		Object value = null;
		if("request".equals(this.getScope())) {
			value = super.getJspContext().getAttribute("name", PageContext.REQUEST_SCOPE);
		}
		if(value instanceof List<?>) {
			for(var elem : (List<?>) value) {
				//取出每一个集合内容
				super.getJspContext().setAttribute("id", elem);
				//显示内容
				super.getJspBody().invoke(null);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
