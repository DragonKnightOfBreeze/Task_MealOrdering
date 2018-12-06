package mealordering.service;

import mealordering.dao.DaoFactory;
import mealordering.dao.MealDao;
import mealordering.dao.OrderDao;
import mealordering.dao.OrderItemDao;
import mealordering.domain.Order;
import mealordering.domain.OrderItem;
import mealordering.domain.User;
import mealordering.domain.annotations.Permission;
import mealordering.utils.DataSourceUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
	private OrderDao orderDao = DaoFactory.getOrderDao();
	private OrderItemDao orderItemDao = DaoFactory.getOrderItemDao();
	private MealDao mealDao = DaoFactory.getMealDao();

	/**
	 * 生成订单。
	 * @param order 订单信息
	 */
	@Permission(Permission.P.Client)
	public void doGenerate(@NotNull Order order) {
		try {
			DataSourceUtils.startTransaction();

			//向Order表中添加数据
			orderDao.doGenerate(order);
			//向OrderItem表中添加数据
			orderItemDao.doAdd(order);
			//修改Meal表中的餐品数量
			mealDao.updateMealCount(order.getOrderItems(), n -> n + 1);
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据Id取消订单（对于普通用户）。
	 */
	@Permission(Permission.P.Client)
	public void doCancelById(@NotNull String id) {
		try {
			DataSourceUtils.startTransaction();

			//查询订单物品
			Order order = new Order();
			order.setId(id);
			List<OrderItem> itemList = orderItemDao.findByOrder(order);
			//修改餐品库存数量
			mealDao.updateMealCount(itemList, n -> n - 1);
			//删除订单物品
			orderItemDao.doDeleteById(id);
			//删除订单
			orderDao.doDeleteById(id);
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据Id删除订单（对于管理员）。
	 */
	@Permission(Permission.P.Admin)
	public void doDeleteById(String id) {
		try {
			DataSourceUtils.startTransaction();

			orderItemDao.doDeleteById(id);
			orderDao.doDeleteById(id);
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 根据Id查询订单。
	 * @param id 订单Id
	 */
	@Permission(Permission.P.Admin)
	public Order findById(@NotNull String id) {
		Order order = null;
		try {
			order = orderDao.findById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	/**
	 * 根据用户查询订单。
	 * @param user 用户
	 */
	@Permission(Permission.P.Admin)
	public List<Order> findByUser(@NotNull User user) {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findByUser(user);
			for(Order order : orderList) {
				List<OrderItem> itemList = orderItemDao.findByOrder(order);
				order.setOrderItems(itemList);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 根据用户查询指定数量的最近生成的订单。
	 */
	@Permission(Permission.P.Client)
	public List<Order> findByUserRecent(@NotNull User user, int count) {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findByUserRecent(user, count);
			for(Order order : orderList) {
				List<OrderItem> itemList = orderItemDao.findByOrder(order);
				order.setOrderItems(itemList);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 查询所有订单
	 */
	@Permission(Permission.P.Admin)
	public List<Order> findAll() {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}


	/**
	 * 更新订单支付状态。
	 * @param id 订单Id
	 */
	@Permission(Permission.P.System)
	public void updatePayState(@NotNull String id) {
		try {
			orderDao.updatePayState(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
