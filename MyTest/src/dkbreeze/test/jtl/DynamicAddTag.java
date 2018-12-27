package dkbreeze.test.jtl;

import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 多参数加法的标签
 * /* 使用：
 * <test:dynamicAdd num1="1" num2="2" num3="3"/>
 */
public class DynamicAddTag extends SimpleTagSupport implements DynamicAttributes {
	private Map<String, Integer> num = new HashMap<>();

	/**
	 * 设置标签
	 */
	@Override
	public void doTag() throws IOException {
		Integer sum = 0;
		for(var s : num.entrySet()) {
			sum += s.getValue();
		}
		super.getJspContext().getOut().write(sum);
	}


	/**
	 * 设置动态属性
	 */
	@Override
	public void setDynamicAttribute(String url, String localName, Object value) {
		//取出设置的动态属性的内容
		num.put(localName, Integer.parseInt(value.toString()));
	}
}
