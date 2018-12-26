# 建立数据库

```mysql
use mealordering;

create table Meal(
	id varchar(100) primary key not null comment '餐品编号',
	name varchar(16) default '未命名' comment '餐品名称',
	price double(11,2) default 0.00 comment '餐品价格',
	category varchar(12) default '所有分类' comment '餐品分类',
	imgUrl varchar(255) comment '餐品图片路径',
	description varchar(255) comment '餐品描述',
	count int(9) default 0 comment '餐品数量',
	soldCount int(9) default 0 comment '餐品售出数量'
);
alter table Meal comment = '餐品表';

create table Notice
(
	id int not null comment '通知编号' PRIMARY KEY auto_increment,
	title varchar(32) default '未命名' null comment '通知标题',
	details varchar(255) null comment '通知内容',
	time datetime null comment '通知时间'
);
alter table Notice comment '通知表';

create table `Order`
(
    id varchar(100) NOT NULL comment '订单编号' PRIMARY KEY ,
    totalPrice double COMMENT '订单价格',
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
    id int(11) PRIMARY KEY NOT NULL COMMENT '用户编号（系统自动编号）' AUTO_INCREMENT,
    userName varchar(20) NOT NULL COMMENT '用户姓名',
    password varchar(20) NOT NULL COMMENT '用户密码',
   	imgUrl varchar(255) comment '用户头像路径',
    type varchar(4) DEFAULT '普通用户' COMMENT '用户类型',
    
    gender varchar(2) COMMENT '用户性别',
    email varchar(50) COMMENT '用户邮箱地址',
    phoneNum varchar(20) COMMENT '用户电话号码',
    introduce varchar(255) COMMENT '用户介绍',
    activeCode varchar(50) COMMENT '注册激活码',
    activeState int(1) DEFAULT 0 COMMENT '用户激活状态 0：未激活，1：已激活',
    registerTime datetime COMMENT '注册时间'
);
ALTER TABLE User COMMENT = '用户表';

insert into User (userName,password,imgUrl,type) value('Windea','123456','D:\\My Documents\\My Projects\\Java Projects\\Task\\Task_MealOrdering\\MealOrdering\\web\\assets\\image\\user_img\\FoxSworder_4.png','管理员');



insert into Meal(id,name,price,category,description,count) values 
('11111111','素食1','10','素食','餐品描述餐品描述','100'),
('22222222','素食1','10','素食','餐品描述餐品描述','100'),
('11111112','素食1','10','素食','餐品描述餐品描述','100'),
('11111113','素食1','10','素食','餐品描述餐品描述','100'),
('11111144','荤食1','10','荤食','餐品描述餐品描述','100'),
('11111555','荤食1','10','荤食','餐品描述餐品描述','100'),
('11117777','荤食1','10','荤食','餐品描述餐品描述','100'),
('11113111','荤食1','10','荤食','餐品描述餐品描述','100'),
('11112111','荤食1','10','荤食','餐品描述餐品描述','100'),
('11123111','荤食1','10','荤食','餐品描述餐品描述','100'),
('11111233','荤食1','10','荤食','餐品描述餐品描述','100'),
('11111234','荤食1','10','荤食','餐品描述餐品描述','100'),
('11111235','荤食1','10','荤食','餐品描述餐品描述','100'),
('11111236','甜点1','10','甜点','餐品描述餐品描述','100'),
('11111116','甜点1','10','甜点','餐品描述餐品描述','100'),
('11111237','甜点1','10','甜点','餐品描述餐品描述','100'),
('11111238','甜点1','10','甜点','餐品描述餐品描述','100'),
('11141111','甜点1','10','甜点','餐品描述餐品描述','100'),
('af111111','甜点1','10','甜点','餐品描述餐品描述','100'),
('111a1111','甜点1','10','甜点','餐品描述餐品描述','100'),
('111fa111','甜点1','10','甜点','餐品描述餐品描述','100'),
('11aaa1111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100'),
('11f11111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100'),
('1111h111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100'),
('11j11111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100'),
('11l11111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100'),
('11o11111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100'),
('11w11111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100'),
('11x11111','黑暗料理1','10','黑暗料理','餐品描述餐品描述','100');

insert into Notice (title,details,time) values 
('公告标题1','公告内容公告内容','2017-08-31 00:00:00'), 
('公告标题2','公告内容公告内容','2017-08-31 01:00:00'), 
('公告标题3','公告内容公告内容','2017-08-31 01:00:00'), 
('公告标题4','公告内容公告内容','2017-08-31 01:00:00'), 
('公告标题5','公告内容公告内容','2017-08-31 01:00:00'), 
('公告标题1111','公告内容公告内容','2017-11-03 00:00:00'), 
('公告标题2222','公告内容公告内容','2017-11-03 00:00:00'), 
('公告标题3333','公告内容公告内容','2017-11-03 00:00:00'), 
('公告标题4444','公告内容公告内容','2017-11-03 00:00:00'), 
('公告标题5555','公告内容公告内容','2017-11-03 00:00:00'), 
('公告标题111','公告内容公告内容','2018-10-20 00:00:00'), 
('公告标题222','公告内容公告内容','2018-08-20 00:00:00'), 
('公告标题333','公告内容公告内容','2018-08-20 00:00:00'), 
('公告标题444','公告内容公告内容','2018-08-20 00:00:00'), 
('公告标题555','公告内容公告内容','2018-08-20 00:00:00'), 
('公告标题11','公告内容公告内容','2017-08-20 00:00:55'), 
('公告标题22','公告内容公告内容','2017-08-20 00:00:55'), 
('公告标题33','公告内容公告内容','2017-08-20 00:00:55'), 
('公告标题44','公告内容公告内容','2017-08-20 00:00:55'), 
('公告标题55','公告内容公告内容','2017-08-20 00:00:55'), 
('公告1','公告内容公告内容','2017-08-20 00:00:44'), 
('公告2','公告内容公告内容','2017-08-20 00:00:44'), 
('公告3','公告内容公告内容','2017-08-20 00:00:44'), 
('公告4','公告内容公告内容','2017-08-20 00:00:44'), 
('公告5','公告内容公告内容','2017-08-20 00:00:44'), 
('公告6','公告内容公告内容','2017-08-20 00:00:44');

insert into `Order` (id,totalPrice,receiverAddress,receiverName,receiverPhone,payState,user_id) values 
('11',100,'地球','地球人',12345,0,2),
('12',200,'地球','地球人',12345,0,3),
('13',300,'地球','地球人2',12345,0,4);

insert into OrderItem (order_id, meal_id, buyCount) values 
('11','11111111','10'),
('12','22222222','20'),
('13','33333333','30');

insert into User (userName,password,gender,email,phoneNum,introduce,activeState) values 
('张三','123456','男性','123@qq.com',23333,'个人介绍个人介绍',1),
('李四','123456','男性','233@qq.com',23333,'个人介绍个人介绍',1),
('王五','123456','男性','456@qq.com',23333,'个人介绍个人介绍',1);

```
