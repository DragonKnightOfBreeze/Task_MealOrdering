package mealordering.dao;

import mealordering.domain.Meal;
import mealordering.domain.Order;
import mealordering.domain.OrderItem;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单物品的Dao类
 */
public class OrderItemDao {
	OrderItemDao() {
	}

	/**
	 * 添加订单物品。
	 */
	public void doAdd(@NotNull Order order) throws SQLException {
		String sql = "insert into OrderItem(order_id,meal_id,buyCount)" +
				" value(?,?,?)";
		QueryRunner runner = new QueryRunner();
		List<OrderItem> orderItemList = order.getOrderItemList();
		Object[][] params = new Object[orderItemList.size()][3];
		for (int i = 0; i < params.length; i++) {
			params[i][0] = orderItemList.get(i).getOrder().getId();
			params[i][1] = orderItemList.get(i).getMeal().getId();
			params[i][2] = orderItemList.get(i).getBuyCount();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	/**
	 * 根据Id删除订单物品。
	 */
	public void doDeleteById(@NotNull String id) throws SQLException {
		String sql = "delete from OrderItem where order_id=?";
		QueryRunner runner = new QueryRunner();
		runner.update(DataSourceUtils.getConnection(), sql, id);
	}

	/**
	 * 根据订单查询订单物品，包括对应的餐品。
	 */
	public List<OrderItem> findByOrder(@NotNull final Order order) throws SQLException {
		String sql = "select * from OrderItem,Meal" +
				" where Meal.id=OrderItem.product_id and OrderItem.order_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<OrderItem> itemList = new ArrayList<>();
			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(order);

				Meal meal = new Meal();
				meal.setCategory(rs.getString("category"));
				meal.setId(rs.getString("id"));
				meal.setDescription(rs.getString("description"));
				meal.setImgUrl(rs.getString("imgUrl"));
				meal.setName(rs.getString("name"));
				meal.setCount(rs.getInt("count"));
				meal.setPrice(rs.getDouble("price"));
				orderItem.setMeal(meal);

				orderItem.setBuyCount(rs.getInt("buyCount"));

				itemList.add(orderItem);
			}
			return itemList;
		}, order.getId());
	}


}
