# 项目概述

## 需求分析

## 功能结构

- [ ] 项目结构图

* 网上书城前台网站
	* 用户操作
		* 用户注册
		* 用户登录
		* 用户信息修改
		* 用户购买流程
		* 订单查询
	* 图书浏览
		* 按类别浏览图书
		* 按图片名称搜索图书
		* 本周热卖［可选］
		* 图书公告栏［拓展］
		* 首页轮播图［拓展］
&emsp;
* 网上书城后台管理系统
	* 商品管理
		* 添加商品
		* 编辑商品
		* 删除商品
		* 查询商品
	* 销售榜单
		* 下载销售榜单［可选］
	* 公告管理
		* 添加公告［拓展］
		* 编辑公告［拓展］
		* 删除公告［拓展］
		* 查询公告［拓展］
	* 订单管理
		* 删除订单
		* 查询订单

## 项目预览

&emsp;

# 数据库设计

## ER图设计

- [] ER图

## 数据表结构

**数据库创建脚本**
```mysql
# create database itcaststore;
# use itcaststore;

create TABLE users
(
    id int(11) PRIMARY KEY NOT NULL COMMENT '系统自动编号、自增' AUTO_INCREMENT,
    userName varchar(20) NOT NULL COMMENT '用户名称',
    password varchar(20) NOT NULL COMMENT '用户密码',
    gender varchar(2) DEFAULT '男' COMMENT '性别',
    email varchar(50) COMMENT '邮箱地址',
    telephone varchar(20) COMMENT '电话号码',
    introduce varchar(100) COMMENT '用户简介',
    activeCode varchar(50) COMMENT '注册激活码',
    state int(11) DEFAULT 0 COMMENT '用户状态，1：激活，0：未激活',
    type varchar(10) DEFAULT '普通用户' COMMENT '用户角色：普通用户、超级用户',
    registTime timestamp NOT NULL COMMENT '注册时间'
);
ALTER TABLE users COMMENT = '用户表';

create TABLE products
(
    id varchar(100) PRIMARY KEY NOT NULL COMMENT '商品ID',
    name varchar(40) COMMENT '商品名称',
    price double COMMENT '商品价格',
    category varchar(40) COMMENT '商品分类',
    pnum int(11) COMMENT '商品库存量',
    imgUrl varchar(100) COMMENT '商品图片地址',
    description varchar(255) COMMENT '商品描述'
);
ALTER TABLE products COMMENT = '产品表';

create TABLE orders
(
    id varchar(100) NOT NULL COMMENT '订单ID',
    money double COMMENT '订单价格',
    receiverAddress varchar(255) COMMENT '收货地址',
    receiverName varchar(20) COMMENT '收货人姓名',
    receiverPhone varchar(20) COMMENT '收货人电话',
    payState int(11) DEFAULT 0 COMMENT '订单支付状态，1：已支付，2：未支付',
    orderTime timestamp COMMENT '订单生成时间',
    user_id int(11) COMMENT '用户ID，关联users表中的主键'
);
ALTER TABLE orders COMMENT = '订单表';

create TABLE orderItems
(
    order_id varchar(100) NOT NULL COMMENT '订单ID，关联orders表中的主键',
    prduct_id varchar(100) PRIMARY KEY NOT NULL COMMENT '商品ID，关联products表中的主键',
    buyNum int(11) COMMENT '单个商品的购买数量'
);
ALTER TABLE orderItems COMMENT = '订单条目表';

create TABLE notice
(
    n_id int PRIMARY KEY NOT NULL COMMENT '消息ID',
    title varchar(10) COMMENT '公告标题',
    details varchar(255) COMMENT '公告内容',
    n_time varchar(18) COMMENT '公告的创建时间'
);
ALTER TABLE notice COMMENT = '公告栏信息';

```

# 项目结构

## 前台

### 用户注册功能

* task_itcaststore.web/servlet.client/RegisterServlet.java
* task_itcaststore.service/UserService.java
* task_itcaststore.utils/MailUtils.java
&emsp;
* client/doRegister.jsp
+ client/register_success.jsp

### 用户登录功能

* task_itcaststore.web/servlet/client/LoginServlet.java
* task_itcaststore.service/UserService.java
* task_itcaststore.orderDao/UserDao.java
&emsp;
* client/login.jsp

### 购物车模块

* task_itcaststore.web/servlet/client/AddCartServlet.java
* task_itcaststore.web/servlet/client/ChangeCartServlet.java
&emsp;
* client/cart.jsp

### 订单相关模块

* task_itcaststore.web/servlet/client/CreateOrderServlet.java
* task_itcaststore.web/servlet/client/ShowProductByPage.java
&emsp;
* client/order.jsp
* menu_search.jsp

### 图书搜索功能

* task_itcaststore.web/servlet/client/MenuSearchServlet.java
&emsp;
* menu_search.jsp
* product_search_list.jsp

### 公告板和本周热卖功能

* task_itcaststore.web/servlet/client/ShowIndexServlet.java
* task_itcaststore.orderDao/???Dao.java
&emsp;
* index.jsp
