package task_itcaststore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.jetbrains.annotations.NotNull;
import task_itcaststore.domain.Order;
import task_itcaststore.domain.OrderItem;
import task_itcaststore.domain.Product;
import task_itcaststore.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单物品的DAO类
 */
public class OrderItemDao {

	/**
	 * 添加订单项。
	 */
	public void addOrderItems(@NotNull Order order) throws SQLException {
		//1.生成sql语句
		String sql = "insert into orderItems values(?,?,?)";

		QueryRunner runner = new QueryRunner();
		List<OrderItem> orderItemList = order.getOrderItems();
		Object[][] params = new Object[orderItemList.size()][3];
		for (int i = 0; i < params.length; i++) {
			params[i][0] = orderItemList.get(i).getOrder().getId();
			params[i][1] = orderItemList.get(i).getProduct().getId();
			params[i][2] = orderItemList.get(i).getBuyNum();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	/**
	 * 根据订单查找订单项，并将订单项中商品查找到。
	 */
	public List<OrderItem> findOrderItemsByOrder(@NotNull final Order order) throws SQLException {
		String sql = "select * from orderItems,products where products.id=orderItems.product_id and orderItems.order_id=?";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<OrderItem> items = new ArrayList<>();
			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(order);
				orderItem.setBuyNum(rs.getInt("buyNum"));

				Product product = new Product();
				product.setCategory(rs.getString("category"));
				product.setId(rs.getString("id"));
				product.setDescription(rs.getString("description"));
				product.setImgUrl(rs.getString("imgUrl"));
				product.setName(rs.getString("name"));
				product.setPnum(rs.getInt("pnum"));
				product.setPrice(rs.getDouble("price"));
				orderItem.setProduct(product);
				items.add(orderItem);
			}
			return items;
		}, order.getId());
	}

	/**
	 * 根据订单id，删除订单项。
	 */
	public void delOrderItemById(@NotNull String id) throws SQLException {
		String sql = "delete from orderItems where order_id=?";
		QueryRunner runner = new QueryRunner();
		runner.update(DataSourceUtils.getConnection(), sql, id);
	}
}
