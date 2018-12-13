# 如何配置webapp和Servlet

**准备工作**

1. 下载tomcat
2. 下载maven
3. 设置tomcat和maven的系统变量
4. 在IDEA中新建一个maven项目
5. 在IDEA中新建一个JAVA EE项目，或者添加一个JAVA EE模块，注意添加数据库连接、Servlet、JSP、WebApp库和框架
6. 在运行/调试配置中配置Tomcat服务器，包括tomcat路径、http端口、要打开的浏览器等
7. 注意：添加servlet-api.jar、tomcat-api.jar、catalina.jar到外部库
8. 注意：检查项目的Artifact，如果没有，则需要从模块创建（Web Application:Exploded/Achieve），以生成war包。
	* （war包可以在构建好项目后，在out目录下找到。本地只需要Exploded包）
9. 注意：在服务器启动前构建构件，添加刚才的war包；在服务器的部署（Deployment）中，也要添加刚才的war包。

**实现欢迎页、Servlet**

8. 在web目录下编辑index.html或index.asp，编译好后将class文件放到web/WEB-INF/classes目录下。
	* （class文件可以在构建好项目后，在out目录下找到）
9. 在src文件夹中新建java类，继承自servlet相关的类和接口(`GenericServlet`,`HttpServlet`)，重载实现相关方法。
10. 编辑web/WEB-INF目录下的web.xml文件，指定servlet映射和欢迎页，示例如下。

```xml
    <servlet>
        <!--Servlet名称-->
        <servlet-name>HelloWorld</servlet-name>
        <!--完整的类名-->
        <servlet-class>tests.part6_servlet.HelloWorld</servlet-class>
    </servlet>

    <servlet-mapping>
        <!--Servlet名称-->
        <servlet-name>HelloWorld</servlet-name>
        <!--对外访问的虚拟路径-->
        <url-pattern>/hello-world</url-pattern>
    </servlet-mapping>

    <!--首页列表-->
    <welcome-file-list>
        <welcome-file>index.htm</welcome-file>
        <welcome-file>index.html</welcome-file>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
```

**后续工作


11. 构建好项目后，直接运行index.html或index.jsp，
12. 不需要手动启动tomcat服务器，且需要确保是关闭的。停止运行则表示与服务器断开连接。
13. 如果设置了默认浏览器，此后会直接用默认浏览器打开欢迎页。


# Servlet

设置自动加载Servlet程序  
在servlet元素中加上一个load-on-startup子元素，其中的值为正数，表示优先级。

设置初始化参数（在Servlet标签里面，每个init-param标签只能写一对）
```xml
<init-param>
	<param-name>encoding</param-name>
	<param-value>utf-8</param-value>
</init-param>
```

一般来说，load-on-startup标签要放在前面。

`response.sendRedirect(getServletContext().getContextPath() + "/sampleUrl");`：重定向。
客户端跳转，地址改变。  
清空response，然后再将状态码302和地址返回到浏览器。意味着浏览器会自动地向服务器提出第二次请求。
参数：如果以`/`开头，则代表站点根目录。

`request.getRequestDispatcher("/sampleUrl.jsp").forward(request, response);`：转发。
服务器端跳转，地址不变。  
共享request和response，可以传递参数。  
参数：如果以`/`开头，则代表Web应用程序根目录。

链接地址和表单提交地址：都应该从站点根目录开始填写。

\<c:redirect>：同样是重定向。
\<jsp:forward>：同样是转发。


