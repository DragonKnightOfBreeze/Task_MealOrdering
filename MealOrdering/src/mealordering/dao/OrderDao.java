package mealordering.dao;

import mealordering.domain.Order;
import mealordering.domain.OrderItem;
import mealordering.domain.User;
import mealordering.domain.annotations.Permission;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 订单的Dao类
 * @noinspection Duplicates
 */
public class OrderDao {
	/**
	 * 生成订单。
	 */
	@Permission(Permission.P.Client)
	public void doGenerate(@NotNull Order order) throws SQLException {
		String sql = "insert into Order(id,money,receiverAddress,receiverName,receiverPhone,payState,orderTime,user_id)" +
				" value(?,?,?,?,?,0,null,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(DataSourceUtils.getConnection(), sql,
				order.getId(), order.getTotalPrice(), order.getReceiverAddress(), order.getReceiverName(),
				order.getReceiverPhone(), order.getUser().getId()
		);
	}

	/**
	 * 取消订单。
	 */
	@Permission(Permission.P.Client)
	public void doCancel(@NotNull String id) throws SQLException {
		String sql = "delete from orders where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(DataSourceUtils.getConnection(), sql, id);
	}


	/**
	 * 根据Id查询订单。
	 */
	@Permission(Permission.P.Admin)
	public Order findById(@NotNull String id) throws SQLException {
		String sql = "select * from Order,User where Order.user_id=User.id and Order.id=? order by Order.id";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			Order order = new Order();
			if (rs.next()) {
				User user = new User();
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
	 * 根据用户查询订单。
	 */
	@Permission(Permission.P.All)
	public List<Order> findByUser(@NotNull final User user) throws SQLException {
		String sql = "select * from orders where user_id=? order by orderTime";
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
	@Permission(Permission.P.All)
	public List<Order> findByUserRecent(@NotNull final User user, int count) throws SQLException {
		String sql = "select * from orders where user_id=? order by orderTime limit 0,?";
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
		}, user.getId(), count);
	}


	/**
	 * 查询所有订单，按照用户Id进行排列。
	 */
	@Permission(Permission.P.Admin)
	public List<Order> findAll() throws SQLException {
		String sql = "select Order.*,User.* from Order,User where User.id=Order.user_id" +
				" order by Order.user_id asc,Order.orderTime desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<Order> orderList = new ArrayList<>();
			while (rs.next()) {
				User user = new User();
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
	 * 查询指定数量的最近生成的订单。
	 */
	@Permission(Permission.P.Admin)
	public Order findAllRecent(int count) throws SQLException {
		String sql = "select * from Order order by orderTime desc limit 0,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Order.class), count);
	}


	/**
	 * 更新餐品库存数量。
	 * @param count 计算库存数量的Lambda
	 */
	@Permission(Permission.P.Basic)
	public void updateMealCount(@NotNull List<OrderItem> itemList, IntFunction<Integer> count) throws SQLException {
		String sql = "update Meal set count=count+? where id=?";
		QueryRunner runner = new QueryRunner();
		Object[][] params = new Object[itemList.size()][2];
		for (int i = 0; i < params.length; i++) {
			params[i][0] = itemList.get(i).getBuyCount();
			params[i][1] = itemList.get(i).getMeal().getId();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	/**
	 * 更新订单支付状态。
	 */
	@Permission(Permission.P.Basic)
	public void updatePayState(@NotNull String id) throws SQLException {
		String sql = "update orders set payState=1 where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
