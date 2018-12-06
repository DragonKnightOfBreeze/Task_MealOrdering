* src
	* .orderDao：						与数据库进行交互的类
		* DaoFactory.java：			Dao的工厂类
	* .domain：					实体类
	* .exceptions：				自定义异常
	* .service：					编写业务逻辑，并调用dao操作数据库
	* .utils：					工具类
		* ext：						拓展类
	* .web：
		* filter：					过滤器类，分别用于过滤全站编码和判断用户权限
		* servlet：					项目的Servlet类
			* admin：					管理员相关
			* store：					店铺相关
			* customer：					顾客相关
		* listener：
	* tags：						自定义的标签类
	* c3p0-config.xml：			数据库连接配置文件
	* merchantInfo.properties：	支付信息属性文件
* .web：
	* jsp：					jsp文件
	* js：					js文件
	* css：					css文件
	* images：				images文件
	* temp：					缓存文件
	* WEB-INF：
		* lib：				项目库
		* classes：
		* tags：				自定义标签配置
		- .web.xml：
	* index.jsp：				首页
