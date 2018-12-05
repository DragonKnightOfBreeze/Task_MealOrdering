package task_itcaststore.service;

import task_itcaststore.dao.OrderDao;
import task_itcaststore.dao.OrderItemDao;
import task_itcaststore.dao.ProductDao;
import task_itcaststore.domain.Order;
import task_itcaststore.domain.OrderItem;
import task_itcaststore.domain.User;
import task_itcaststore.utils.DataSourceUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * 订单的服务类
 */
public class OrderService {
	private OrderItemDao orderItemDao = new OrderItemDao();
	private OrderDao orderDao = new OrderDao();
	private ProductDao productDao = new ProductDao();

	/**
	 * 添加订单。
	 */
	public void addOrder(Order order) {
		try {
			//1.开启事务
			DataSourceUtils.startTransaction();
			//2.完成操作
			//2.1向orders表中添加数据
			orderDao.addProduct(order);
			//2.2向orderItem表中添加数据
			orderItemDao.addOrderItems(order);
			//2.3修改商品表中数据
			productDao.updateProductNumWhenAdd(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback(); //事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				//关闭，释放以及提交事务
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据用户查找订单。
	 */
	public List<Order> findOrdersByUser(User user) {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findOrdersByUser(user);
			for (Order order : orderList) {
				List<OrderItem> items = orderItemDao.findOrderItemsByOrder(order);
				order.setOrderItems(items);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 根据id查找订单。
	 */
	public Order findOrderById(String id) {
		Order order = null;
		try {
			order = orderDao.findOrderById(id);
			List<OrderItem> itemList = orderItemDao.findOrderItemsByOrder(order);
			order.setOrderItems(itemList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	/**
	 * 查找所有订单。
	 */
	public List<Order> findAllOrders() {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findAllOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 支付成功后修改订单状态。
	 */
	public void updateOrderState(String id) {
		try {
			orderDao.updateOrderState(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多条件查询订单信息。
	 */
	public List<Order> findOrdersByConditions(String id, String receiverName) {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findOrdersByConditions(id, receiverName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 根据id删除订单 管理员删除订单。
	 */
	public void delOrderById_Admin(String id) {
		try {
			DataSourceUtils.startTransaction();

			orderItemDao.delOrderItemById(id);
			orderDao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 普通用户删除订单。
	 */
	public void delOrderById_Client(String id) {
		try {
			DataSourceUtils.startTransaction();

			//从订单项中查找商品购买数量
			Order order = new Order();
			order.setId(id);
			List<OrderItem> items = orderItemDao.findOrderItemsByOrder(order);
			//修改商品数量
			productDao.updateProductNumWhenDel(items);

			//删除订单项
			orderItemDao.delOrderItemById(id);
			//删除订单
			orderDao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
