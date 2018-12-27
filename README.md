# 网上订餐系统

学期项目设计。

# 概述

## 功能实现：

**已实现的基本功能：**

基本的主页。

基本的用户登录注册功能（还没有实现登录注册验证功能）。

餐品和公告的查询功能。

管理员的登录功能，管理页面的增删查改功能（只是必要的增删查改，不是所有的数据都能增删查改）。

用户资料编辑功能。

用户权限验证。分为登陆状态验证和管理员权限验证。

**已有相关代码，未实现/未测试的功能：**

<u>“记住登录”功能。没有测试。</u>

<u>图片上传功能。允许在用户资料和餐品资料中上传图片。使用smartUpload（修改版）实现。没有测试。</u>

<u>用户注册成功后发送验证邮件，并在点击邮件中的地址后才能激活，之后才能登陆的功能。测试时遇到问题。</u>

*用户登录注册验证功能。套用jquery validation插件。除了基本格式外，也包括通过ajax从数据库查询相关数据，验证“存在性”和“正确性”的验证。*

*用户账户页面的相关功能。用户的订单列表、用户购物车、模拟支付功能。*

*“找回密码”功能。需要发送邮件到用户指定的邮箱，并做好错误处理。*

**展望：**

*真正的支付功能。对接到支付宝、微信接口等，并能通过测试。*

*套用SSH框架/SSM框架。还有许多知识需要学习。*

## 前端页面：

**已实现：**

使用bootstrap4+font-awesome+jquery实现。由于打算全部套用ajax时遇到诸多问题（尤其是在异步加载这一块），暂时弃用了ajax，前端页面全部使用jsp实现。这样一来代码看起来就更清晰了，并且也可以明确变量的类型和动态插入代码的位置。

**未实现：**

*套用jquery validation插件。在套用这个插件时遇到了一些问题，暂时没有实现“登录与注册验证的功能”。但是html5本身也可以限制用户输入，比如，要求必须输入，要求符合某一正则表达式等。*

*部分套用ajax。在不会因为异步加载出现问题的情况下，套用ajax。比如，点击提交登录表单时，在提交之前由ajax POST到Servlet，查询数据库，如果没有用户，则显示信息“用户不存在”，如果密码错误，则显示信息“密码错误”。*

*更加漂亮美观的前端页面。目前的前端页面只使用了bootstrap4和font-awesome，由于之前打算全部套用ajax时遇到了诸多问题，没有时间再深入调整页面格式。*

**展望：**

更加漂亮美观的前端页面。

## 后台代码（分层，数据传递，错误处理，设计模式，命名规范）

**已实现：**

代码分层。后台代码层级有：domain（实体类），dao（只处理数据库操作），service（对dao的代理），servlet（接受参数，根据参数调用对应的service方法，集中操作）。额外的层级有：utils（工具类），exception（自定义异常）。

错误处理：dao层不处理异常，可能引发自定义的ResultEmptyException。service层抛出和引发异常，抛出多种自定义异常。servlet层捕捉异常，调整到相关的错误页。

设计模式：工厂模式，不能直接对到层、service层的类访问，只能通过工厂类。代理模式，service层是对dao层的代理。

命名规范：service、servlet、utils层的类带有相应后缀。

**额外的特点：**

使用了C3P0数据源。dao层操作可能只需要3行代码。

编写了许多工具类和拓展类，方便代码编写。例如，对String的拓展类，与枚举比较，转化成整型且设定默认值，多情况判断为null、为null或空、为null或空格。

使用了一些注解。`@NotNull`标识参数不为空，`@Language("MySQL")`使字符串启用语法高亮。`@WebServlet`替换了在`web.xml`中的配置。

使用了自定义TODO高亮。`TODO`标识待办项，`STEP`标识代码逻辑层次/编写步骤。

## 数据库（设计，命名规范）

**已实现：**

命名规范：数据库名誉项目包名一致，表名和JavaBean类名一致，字段名与JavaBean属性名一致。

# 一些问题

## SmartUpload中文乱码问题解决：

使用原版的SmartUpload插件时会出现中文乱码问题。解决方法：[360doc](http://www.360doc.com/content/08/1218/14/16915_2150839.shtml)

## 路径问题：

`redirect`：只能传入绝对地址  
示例：`reg.getContextPath() + /mealordering/index.jsp`

`requestDispatcher`：可以传入相对于contextPath的以`/`开头的地址，也可以传入相对于当前路径的不以`/`开头的地址  
示例`/mealordering/index.jsp`，`error/unexpected-error.jsp`

`url、href`：同`redirect`

`c:url`：同`requestDispatcher`

`jsp:formward`：同`requestDispatcher`

`location.href`：可以是绝对地址，相对于contextPath的以`/`开头的地址。

## 编码问题：

html、jsp、servlet都使用utf-8时，只需要设置:
```
req.setCharacterEncoding("utf-8");
resp.setCharacterEncoding("utf-8");
resp.setContentType("text/html;charset=utf-8");
```

## 参数传递问题：

只要存在对应参数，并且没有被设置为disable，也没有出现单选/多选但没有选择任一项的情况，  在servlet中得到的参数（字符串）就不为null，至多也是空字符串。
