package mealordering.service;

import dkbreeze.annotation.NotTested;
import dkbreeze.utils.ext.ListExt;
import mealordering.dao.DaoFactory;
import mealordering.dao.MealDao;
import mealordering.dao.OrderDao;
import mealordering.dao.OrderItemDao;
import mealordering.domain.Meal;
import mealordering.domain.NormalUser;
import mealordering.domain.Order;
import mealordering.domain.OrderItem;
import mealordering.exception.ResultEmptyException;
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
			mealDao.updateMealCount(order.getOrderItemList(), false);
		} catch(Exception e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch(Exception e) {
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
			mealDao.updateMealCount(itemList, true);
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
	public Order findById(@NotNull String id) throws SQLException, ResultEmptyException {
		Order order = orderDao.findById(id);
		if(order == null)
			throw new ResultEmptyException();
		return order;
	}

	/**
	 * 根据用户查询订单。
	 * @param user 用户
	 */
	public List<Order> findByUser(@NotNull NormalUser user) throws SQLException, ResultEmptyException {
		List<Order> orderList = orderDao.findByUser(user);
		if(ListExt.orEmpty(orderList))
			throw new ResultEmptyException();
		for(Order order : orderList) {
			List<OrderItem> itemList = orderItemDao.findByOrder(order);
			order.setOrderItemList(itemList);
		}
		return orderList;
	}

	/**
	 * 根据用户查询指定数量的最近生成的订单，分页显示。
	 */
	public List<Order> findByUserRecent(@NotNull NormalUser user, int findCount)
	throws SQLException, ResultEmptyException {
		List<Order> orderList = orderDao.findByUser(user, findCount);
		if(ListExt.orEmpty(orderList))
			throw new ResultEmptyException();
		for(Order order : orderList) {
			List<OrderItem> itemList = orderItemDao.findByOrder(order);
			order.setOrderItemList(itemList);
		}
		return orderList;
	}

	/**
	 * 得到某个用户最近购买的指定数量的商品。
	 */
	@NotTested
	public List<Meal> getRecentMealsByUser(@NotNull NormalUser user, int findCount)
	throws ResultEmptyException, SQLException {
		List<Meal> mealList = new ArrayList<>();
		List<Order> orderList = findByUserRecent(user, findCount);
		if(ListExt.orEmpty(orderList))
			throw new ResultEmptyException();
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
	 * 查询所有订单。
	 */
	public List<Order> findAll() throws SQLException, ResultEmptyException {
		List<Order> orderList = orderDao.findAll();
		if(ListExt.orEmpty(orderList))
			throw new ResultEmptyException();
		for(Order order : orderList) {
			List<OrderItem> itemList = orderItemDao.findByOrder(order);
			order.setOrderItemList(itemList);
		}
		return orderList;
	}

	/**
	 * 更新订单支付状态。
	 * @param id 订单Id
	 */
	public void updatePayState(@NotNull String id) throws SQLException {
		orderDao.updatePayState(id);
	}

}
