package mealordering.dao;

import dk_breeze.utils.ext.StringExt;
import mealordering.domain.Meal;
import mealordering.domain.OrderItem;
import mealordering.domain.enums.EMeal_Category;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 餐品的Dao类
 */
public class MealDao {

	MealDao() {
	}

	/**
	 * 添加餐品。
	 * @param meal 餐品信息
	 */
	public void doAdd(@NotNull Meal meal) throws SQLException {
		String sql = "insert into Meal(id,name,price,category,imgUrl,description,count,soldCount)" +
				" value(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, meal.getId(), meal.getName(), meal.getPrice(), meal.getCategory(), meal.getCount(), meal.getImgUrl(), meal.getDescription(), 0);
	}

	/**
	 * 编辑餐品信息，区分有无图片的情况。
	 * @param meal 餐品信息
	 */
	public void doEdit(@NotNull Meal meal) throws SQLException {
		String sql = "update Meal set name=?,price=?,category=?,count=?,description=? ";
		List<Object> params = new ArrayList<>();
		Collections.addAll(params,
				meal.getName(), meal.getPrice(), meal.getCategory(), meal.getCount(), meal.getDescription()
		);
		if(!StringExt.isSpace(meal.getImgUrl())) {
			sql += ",imgUrl=?";
			params.add(meal.getImgUrl());
		}
		sql += "where id=? ";
		params.add(meal.getId());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, params.toArray());
	}

	/**
	 * 根据Id删除餐品。
	 * @param id 餐品Id
	 */
	public void doDeleteById(@NotNull String id) throws SQLException {
		String sql = "doDeleteById from Meal where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}


	/**
	 * 根据Id查询餐品。
	 * @param id 餐品Id
	 */
	public Meal findById(@NotNull String id) throws SQLException {
		String sql = "select * from Meal where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Meal.class), id);
	}

	/**
	 * 多条件查询餐品。
	 */
	public List<Meal> findByConditions(@Nullable String id, @Nullable String name, @Nullable String category, @Nullable String minPrice, @Nullable String maxPrice) throws SQLException {
		String sql = "select * from Meal where 1=1";
		List<Object> params = new ArrayList<>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(!StringExt.isSpace(id)) {
			sql += " and id=? ";
			params.add(id);
		}
		if(!StringExt.isSpace(name)) {
			sql += " and name=? ";
			params.add(name);
		}
		if(!StringExt.isSpace(category)) {
			sql += " and category=? ";
			params.add(category);
		}
		if(!StringExt.isSpace(minPrice) && !StringExt.isSpace(maxPrice)) {
			sql += " and price between ? and ? ";
			params.add(minPrice);
			params.add(maxPrice);
		}
		return runner.query(sql, new BeanListHandler<>(Meal.class), params.toArray());
	}

	/**
	 * 根据分类查询餐品，分页显示。
	 * @param category 餐品分类
	 */
	public List<Meal> findByCategoryInPage(@Nullable String category, int pageIndex, int count) throws SQLException {
		String sql = "select * from Meal where 1=1";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(StringExt.isSpace(category) || StringExt.equalsE(category, EMeal_Category.Default)) {
			sql += " limit ?,?";
			return runner.query(sql, new BeanListHandler<>(Meal.class), (pageIndex - 1) * count, count);
		} else {
			sql += " and category=? limit ?,?";
			return runner.query(sql, new BeanListHandler<>(Meal.class), category, (pageIndex - 1) * count, count);
		}
	}


	/**
	 * 得到根据分类查询所查询到的餐品数量。
	 * @param category 餐品分类
	 */
	public int findByCategoryGetCount(@Nullable String category) throws SQLException {
		String sql = "select count(*) from Meal where 1=1";
		Long count;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(StringExt.isSpace(category) || StringExt.equalsE(category, EMeal_Category.Default)) {
			count = runner.query(sql, new ScalarHandler<>());
		} else {
			sql += " and category=?";
			count = runner.query(sql, new ScalarHandler<>(), category);
		}
		return count.intValue();
	}


	/**
	 * 得到销售榜单。
	 * @param year 年
	 * @param month 月
	 */
	public List<Object[]> getSalesList(@NotNull String year, @NotNull String month) throws SQLException {
		String sql = "select Meal.name,sum(OrderItem.buyCount) salesCount" +
				" from Order,Meal,OrderItem" +
				" where Order.id=OrderItem.order_id and Meal.id=OrderItem.meal_id" +
				" and Order.payState=1 and month(orderTime) =? and year(orderTime) =?" +
				" group by Meal.name" +
				" order by salesCount desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month);
	}

	/**
	 * 得到指定数量的本周热销商品。
	 * @param count 要得到的数量
	 */
	public List<Object[]> getWeekHotProducts(int count) throws SQLException {
		String sql = "select Meal.id,Meal.name,Meal.imgUrl,sum(OrderItem.buyCount) salesCount" +
				" from OrderItem,Order,Meal" +
				" where OrderItem.order_id = Order.id and Meal.id = OrderItem.meal_id" +
				" and Order.payState=1 and Order.orderTime > DATE_SUB(NOW(), INTERVAL 7 DAY)" +
				" group by Meal.id,Meal.name,Meal.imgUrl" +
				" order by salesCount desc" +
				" limit 0, ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), count);
	}

	/**
	 * 根据餐品名字进行模糊查询，分页显示。
	 * @param pageIndex 页面索引
	 * @param count 每页条数
	 * @param searchField 搜索域
	 */
	public List<Meal> searchByNameInPage(@NotNull String searchField, int pageIndex, int count) throws SQLException {
		String sql = "select * from Meal where name like '%" + searchField + "%' limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Meal.class), (pageIndex - 1) * count, count);
	}

	/**
	 * 得到模糊查询所查询到的餐品数量。
	 */
	public int searchByNameGetCount(@NotNull String searchField) throws SQLException {
		var sql = "select count(*) from Meal where name like '%" + searchField + "%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ScalarHandler<>());
	}

	/**
	 * 更新餐品库存数量。
	 * @param count 计算库存数量的Lambda
	 */
	public void updateMealCount(@NotNull List<OrderItem> itemList, IntFunction<Integer> count) throws SQLException {
		String sql = "update Meal set count=count+? where id=?";
		QueryRunner runner = new QueryRunner();
		Object[][] params = new Object[itemList.size()][2];
		for(int i = 0; i < params.length; i++) {
			params[i][0] = itemList.get(i).getBuyCount();
			params[i][1] = itemList.get(i).getMeal().getId();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}
}
