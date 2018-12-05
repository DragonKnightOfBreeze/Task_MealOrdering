package task_itcaststore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import task_itcaststore.domain.Order;
import task_itcaststore.domain.User;
import task_itcaststore.utils.DataSourceUtils;
import task_itcaststore.utils.ext.StringExt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单的DAO类
 * @noinspection Duplicates
 */
public class OrderDao {
	/**
	 * 生成订单。
	 */
	public void addProduct(@NotNull Order order) throws SQLException {
		//1.生成Sql语句
		String sql = "insert into orders values(?,?,?,?,?,0,null,?)";
		//2.生成执行sql语句的QueryRunner,不传递参数
		QueryRunner runner = new QueryRunner();
		//3.执行update()方法插入数据
		runner.update(DataSourceUtils.getConnection(), sql, order.getId(), order.getMoney(), order.getReceiverAddress(), order.getReceiverName(), order.getReceiverPhone(), order.getUser().getId());
	}

	/**
	 * 根据id查找订单信息。
	 */
	public Order findOrderById(@NotNull String id) throws SQLException {
		String sql = "select * from orders,users where orders.user_id=users.id and orders.id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			Order order = new Order();
			while (rs.next()) {
				User user = new User();
				setForUser(user, rs);
				setForOrder(order, user, rs);
			}
			return order;
		}, id);
	}

	/**
	 * 查找用户所属订单。
	 */
	public List<Order> findOrdersByUser(@NotNull final User user) throws SQLException {
		String sql = "select * from orders where user_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<Order> orderList = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order();
				setForOrder(order, user, rs);
				orderList.add(order);
			}
			return orderList;
		}, user.getId());
	}

	/**
	 * 查找所有订单。
	 */
	public List<Order> findAllOrders() throws SQLException {
		//1.创建sql
		String sql = "select orders.*,users.* from orders,user where users.id=orders.user_id order by orders.user_id";
		//2.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//3.返回QueryRunner对象query()方法的查询结果
		return runner.query(sql, rs -> {
			//创建订单集合
			List<Order> orderList = new ArrayList<>();
			//循环遍历订单和用户信息
			while (rs.next()) {
				Order order = new Order();
				User user = new User();
				setForUser(user, rs);
				setForOrder(order, user, rs);
				orderList.add(order);
			}
			return orderList;
		});
	}

	/**
	 * 多条件查询。
	 */
	public List<Order> findOrdersByConditions(@Nullable String id, @Nullable String receiverName) throws SQLException {
		//1.创建集合对象，用于存储参数
		List<Object> paramList = new ArrayList<>();
		//2.定义查询sql
		String sql = "select orders.*,users.* from orders,users where users.id=orders.user_id ";
		//3.根据参数拼接sql语句
		if (!StringExt.isSpace(id)) {
			sql += " and orders.id=?";
			paramList.add(id);
		}
		if (!StringExt.isSpace(receiverName)) {
			sql += " and receiverName=?";
			paramList.add(receiverName);
		}
		//按照用户id进行排序
		sql += " order by orders.user_id";

		//4.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//5.返回QueryRunner对象query方法的执行结果
		return runner.query(sql, rs -> {
			List<Order> orderList = new ArrayList<>();
			//循环遍历出订单和用户信息
			while (rs.next()) {
				Order order = new Order();
				User user = new User();
				setForUser(user, rs);
				setForOrder(order, user, rs);
				orderList.add(order);
			}
			return orderList;
		}, paramList.toArray());
	}

	/**
	 * 根据订单号修改订单状态。
	 */
	public void updateOrderState(@NotNull String id) throws SQLException {
		String sql = "update orders set payState=1 where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}

	/**
	 * 根据id删除订单。
	 */
	public void delOrderById(@NotNull String id) throws SQLException {
		String sql = "delete from orders where id=?";
		QueryRunner runner = new QueryRunner();
		runner.update(DataSourceUtils.getConnection(), sql, id);
	}


	/**
	 * 为Order设置多个属性。
	 */
	private void setForOrder(Order order, User user, ResultSet rs) throws SQLException {
		order.setId(rs.getString("orders.id"));
		order.setMoney(rs.getDouble("orders.money"));
		order.setOrderIime(rs.getDate("orders.orderTime"));
		order.setPayState(rs.getInt("orders.payState"));
		order.setReceiverAddress(rs.getString("orders.receiverAddress"));
		order.setReceiverName(rs.getString("orders.receiverName"));
		order.setReceiverPhone(rs.getString("orders.receiverPhone"));
		order.setUser(user);
	}

	/**
	 * 为User设置多个属性。
	 */
	private void setForUser(User user, ResultSet rs) throws SQLException {
		user.setId(rs.getInt("user.id"));
		user.setEmail(rs.getString("user.email"));
		user.setGender(rs.getString("user.gender"));
		user.setActiveCode(rs.getString("user.activeCode"));
		user.setIntroduce(rs.getString("user.introduce"));
		user.setPassword(rs.getString("user.password"));
		user.setRegistTime(rs.getDate("user.registTime"));
		user.setType(rs.getString("user.role"));
		user.setState(rs.getInt("user.state"));
		user.setTelephone(rs.getString("user.telephone"));
		user.setUserName(rs.getString("user.userName"));
	}
}
