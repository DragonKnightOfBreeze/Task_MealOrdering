package mealordering.dao;

import mealordering.domain.NormalUser;
import mealordering.domain.Order;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单的Dao类
 * @noinspection Duplicates
 */
public class OrderDao {
	OrderDao() {
	}

	/**
	 * 生成订单。
	 */
	public void doCreate(@NotNull Order order) throws SQLException {
		String sql = "insert into Order(id,totalPrice,receiverAddress,receiverName,receiverPhone,payState,orderTime,user_id)" +
				" value(?,?,?,?,?,0,null,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(DataSourceUtils.getConnection(), sql,
				order.getId(), order.getTotalPrice(), order.getReceiverAddress(), order.getReceiverName(),
				order.getReceiverPhone(), order.getUser().getId()
		);
	}

	/**
	 * 根据Id取消订单。
	 */
	public void doDeleteById(@NotNull String id) throws SQLException {
		String sql = "delete from Order where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(DataSourceUtils.getConnection(), sql, id);
	}


	/**
	 * 根据Id查询订单。
	 */
	public Order findById(@NotNull String id) throws SQLException {
		String sql = "select * from Order,User where Order.user_id=User.id and Order.id=? order by Order.id";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			Order order = new Order();
			if (rs.next()) {
				NormalUser user = new NormalUser();
				user.setId(rs.getInt("User.id"));
				user.setUserName(rs.getString("User.userName"));
				user.setGender(rs.getString("User.gender"));
				user.setEmail(rs.getString("User.email"));
				user.setPhoneNum(rs.getString("User.phoneNum"));
				user.setIntroduce(rs.getString("User.introduce"));
				user.setType(rs.getString("User.type"));
				user.setActiveState(rs.getInt("User.activeState"));
				user.setRegisterTime(rs.getDate("User.registerTime"));

				order.setId(rs.getString("Order.id"));
				order.setTotalPrice(rs.getDouble("Order.money"));
				order.setOrderTime(rs.getDate("Order.orderTime"));
				order.setPayState(rs.getInt("Order.payState"));
				order.setReceiverAddress(rs.getString("Order.receiverAddress"));
				order.setReceiverName(rs.getString("Order.receiverName"));
				order.setReceiverPhone(rs.getString("Order.receiverPhone"));
				order.setUser(user);
			}
			return order;
		}, id);
	}

	/**
	 * 查询指定数量的最近生成的订单。
	 */
	public Order findRecent(int count) throws SQLException {
		String sql = "select * from Order order by orderTime desc limit 0,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Order.class), count);
	}

	/**
	 * 根据用户查询订单。
	 */
	public List<Order> findByUser(@NotNull final NormalUser user) throws SQLException {
		String sql = "select * from Order where user_id=? order by orderTime";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<Order> orderList = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getString("Order.id"));
				order.setTotalPrice(rs.getDouble("Order.money"));
				order.setOrderTime(rs.getDate("Order.orderTime"));
				order.setPayState(rs.getInt("Order.payState"));
				order.setReceiverAddress(rs.getString("Order.receiverAddress"));
				order.setReceiverName(rs.getString("Order.receiverName"));
				order.setReceiverPhone(rs.getString("Order.receiverPhone"));
				order.setUser(user);

				orderList.add(order);
			}
			return orderList;
		}, user.getId());
	}

	/**
	 * 根据用户查询指定数量的最近生成的订单。
	 */
	public List<Order> findByUser(@NotNull final NormalUser user, int findCount) throws SQLException {
		String sql = "select * from Order where user_id=? order by orderTime limit 0,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<Order> orderList = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getString("Order.id"));
				order.setTotalPrice(rs.getDouble("Order.money"));
				order.setOrderTime(rs.getDate("Order.orderTime"));
				order.setPayState(rs.getInt("Order.payState"));
				order.setReceiverAddress(rs.getString("Order.receiverAddress"));
				order.setReceiverName(rs.getString("Order.receiverName"));
				order.setReceiverPhone(rs.getString("Order.receiverPhone"));
				order.setUser(user);

				orderList.add(order);
			}
			return orderList;
		}, user.getId(), findCount);
	}


	/**
	 * 查询所有订单，按照用户Id进行排列。
	 */
	public List<Order> findAll() throws SQLException {
		String sql = "select Order.*,User.* from Order,User" +
				" where User.id=Order.user_id" +
				" order by Order.user_id asc,Order.orderTime desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<Order> orderList = new ArrayList<>();
			while (rs.next()) {
				NormalUser user = new NormalUser();
				user.setId(rs.getInt("User.id"));
				user.setUserName(rs.getString("User.userName"));
				user.setGender(rs.getString("User.gender"));
				user.setEmail(rs.getString("User.email"));
				user.setPhoneNum(rs.getString("User.phoneNum"));
				user.setIntroduce(rs.getString("User.introduce"));
				user.setType(rs.getString("User.type"));
				user.setActiveState(rs.getInt("User.activeState"));
				user.setRegisterTime(rs.getDate("User.registerTime"));

				Order order = new Order();
				order.setId(rs.getString("Order.id"));
				order.setTotalPrice(rs.getDouble("Order.money"));
				order.setOrderTime(rs.getDate("Order.orderTime"));
				order.setPayState(rs.getInt("Order.payState"));
				order.setReceiverAddress(rs.getString("Order.receiverAddress"));
				order.setReceiverName(rs.getString("Order.receiverName"));
				order.setReceiverPhone(rs.getString("Order.receiverPhone"));
				order.setUser(user);

				orderList.add(order);
			}
			return orderList;
		});
	}

	/**
	 * 更新订单支付状态。
	 */
	public void updatePayState(@NotNull String id) throws SQLException {
		String sql = "update Order set payState=1 where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
