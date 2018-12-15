package dk_breeze.test.jtl;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 显示格式化后的当前时间的标签
 * /* 使用：
 * <test:dateFormat format="yyyy-MM-dd HH-mm-ss"/>
 */
public class SimpleDateTag extends SimpleTagSupport {
	/** 格式化模版 */
	private String format;

	@Override
	public void doTag() {
		if(getFormat() == null)
			setFormat("yyyy-MM-dd HH-mm-ss");
		var sdf = new SimpleDateFormat(this.getFormat());
		try {
			super.getJspContext().getOut().write(sdf.format(new Date()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
