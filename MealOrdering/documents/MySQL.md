# 建立数据库

```mysql
use mealordering;

create table Meal(
	id varchar(8) primary key not null comment '餐品编号',
	name varchar(16) default '未命名' comment '餐品名称',
	price double(11,2) default 0.00 comment '餐品价格',
	category varchar(12) default '未知' comment '餐品分类',
	imgUrl varchar(50) comment '餐品图片路径',
	description varchar(255) comment '餐品描述',
	count int(9) default 0 comment '餐品数量',
	soldCount int(9) default 0 comment '餐品售出数量'
);
alter table Meal comment = '餐品表';

create table Notice
(
	id int not null comment '通知编号' PRIMARY KEY ,
	title varchar(32) default '未命名' null comment '通知标题',
	details varchar(255) null comment '通知内容',
	time datetime null comment '通知时间'
);
alter table Notice comment '通知表';

create table `Order`
(
    id varchar(100) NOT NULL comment '订单编号' PRIMARY KEY ,
    money double COMMENT '订单价格',
    receiverAddress varchar(255) COMMENT '收货地址',
    receiverName varchar(20) COMMENT '收货人姓名',
    receiverPhone varchar(20) COMMENT '收货人电话',
    payState int(1) DEFAULT 0 COMMENT '订单支付状态 0：未支付，1：已支付',
    orderTime datetime COMMENT '订单生成时间',
    user_id int(11) COMMENT '订单所属用户的Id'
);
ALTER TABLE `Order` COMMENT = '订单表';

create TABLE OrderItem
(
    order_id varchar(100) NOT NULL COMMENT '订单的ID',
    meal_id varchar(100) PRIMARY KEY NOT NULL COMMENT '餐品的ID',
    buyCount int(11) COMMENT '购买数量'
);
ALTER TABLE OrderItem COMMENT = '订单物品表';


create TABLE User
(
    id int(8) PRIMARY KEY NOT NULL COMMENT '用户编号（系统自动编号）' AUTO_INCREMENT,
    userName varchar(20) NOT NULL COMMENT '用户姓名',
    password varchar(20) NOT NULL COMMENT '用户密码',
    gender varchar(2) DEFAULT '男' COMMENT '用户性别',
    email varchar(50) COMMENT '用户邮箱地址',
    phoneNum varchar(20) COMMENT '用户电话号码',
    introduce varchar(100) COMMENT '用户介绍',
    
    type varchar(4) DEFAULT '普通用户' COMMENT '用户类型',
    activeCode varchar(50) COMMENT '注册激活码',
    activeState int(11) DEFAULT 0 COMMENT '用户激活状态 0：未激活，1：已激活',
    registerTime datetime NOT NULL COMMENT '注册时间'
);
ALTER TABLE User COMMENT = '用户表';
```
