package mealordering.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Set;
import java.util.TreeSet;

/**
 * 在线用户监听器
 * TODO
 */
@WebListener
public class OnlineUserListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	//用于application属性操作
	private ServletContext app = null;

	//上下文初始化
	public void contextInitialized(ServletContextEvent event) {
		//取得ServletContext实例
		this.app = event.getServletContext();
		//设置空集合
		this.app.setAttribute("online", new TreeSet());
	}

	//销毁session
	public void sessionDestroyed(HttpSessionEvent event) {
		//取出已有列表
		Set all = (Set) this.app.getAttribute("online");
		//取出设置的内容
		all.remove(event.getSession().getAttribute("userId"));
		//重新保存
		this.app.setAttribute("online", all);
	}

	//增加session属性
	public void attributeAdded(HttpSessionBindingEvent event) {
		//取出已有列表
		Set all = (Set) this.app.getAttribute("online");
		//增加新用户
		all.add(event.getValue());
		//重新保存
		this.app.setAttribute("online", all);
	}

	//删除session属性
	public void attributeRemoved(HttpSessionBindingEvent event) {
		//取出已有列表
		Set all = (Set) this.app.getAttribute("online");
		//删除离开的用户
		all.remove(event.getValue());
		//重新保存
		this.app.setAttribute("online", all);
	}
}
