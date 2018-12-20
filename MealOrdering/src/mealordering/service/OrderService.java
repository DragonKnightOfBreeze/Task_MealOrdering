package mealordering.service;

import mealordering.dao.DaoFactory;
import mealordering.dao.MealDao;
import mealordering.dao.OrderDao;
import mealordering.dao.OrderItemDao;
import mealordering.domain.*;
import mealordering.utils.DataSourceUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
	private OrderDao orderDao = DaoFactory.getOrderDao();
	private OrderItemDao orderItemDao = DaoFactory.getOrderItemDao();
	private MealDao mealDao = DaoFactory.getMealDao();

	/**
	 * 生成订单。
	 * @param order 订单信息
	 */
	public void doCreate(@NotNull Order order) {
		try {
			DataSourceUtils.startTransaction();

			//向Order表中添加数据
			orderDao.doCreate(order);
			//向OrderItem表中添加数据
			orderItemDao.doAdd(order);
			//修改Meal表中的餐品数量
			mealDao.updateMealCount(order.getOrderItemList(), n -> n + 1);
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
	public List<Order> findByUser(@NotNull NormalUser user) {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findByUser(user);
			for(Order order : orderList) {
				List<OrderItem> itemList = orderItemDao.findByOrder(order);
				order.setOrderItemList(itemList);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 根据用户查询指定数量的最近生成的订单，分页显示。
	 */
	public List<Order> findByUserRecent(@NotNull NormalUser user, int findCount) {
		List<Order> orderList = null;
		try {
			orderList = orderDao.findByUser(user, findCount);
			for(Order order : orderList) {
				List<OrderItem> itemList = orderItemDao.findByOrder(order);
				order.setOrderItemList(itemList);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 得到某个用户最近购买的指定数量的商品。
	 */
	public List<Meal> getRecentMealsByUser(@NotNull NormalUser user, int findCount) {
		List<Meal> mealList = new ArrayList<>();
		List<Order> orderList = findByUserRecent(user, findCount);
		flag:
		for(Order order : orderList) {
			List<OrderItem> itemList = order.getOrderItemList();
			for(OrderItem item : itemList) {
				mealList.add(item.getMeal());
				if(mealList.size() == findCount) {
					break flag;
				}
			}
		}
		return mealList;
	}

	/**
	 * 查询所有订单，分页显示。
	 */
	public BeanPage<Order> findAllInPage(int pageIndex, int count) {
		BeanPage<Order> orderPage = null;
		try {
			List<Order> orderList = orderDao.findAll();
			for(Order order : orderList) {
				List<OrderItem> itemList = orderItemDao.findByOrder(order);
				order.setOrderItemList(itemList);
			}
			orderPage = new BeanPage<>(pageIndex, count, orderList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return orderPage;
	}

	/**
	 * 更新订单支付状态。
	 * @param id 订单Id
	 */
	public void updatePayState(@NotNull String id) {
		try {
			orderDao.updatePayState(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
