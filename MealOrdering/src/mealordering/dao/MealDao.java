package mealordering.dao;

import mealordering.domain.Meal;
import mealordering.domain.annotations.Permission;
import mealordering.domain.enums.EMeal_Category;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import task_itcaststore.utils.ext.StringExt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 餐品的Dao类
 */
public class MealDao {
	/**
	 * 添加餐品。
	 */
	@Permission(Permission.P.Admin)
	public void doAdd(@NotNull Meal meal) throws SQLException {
		String sql = "insert into Meal(id,name,price,category,imgUrl,description,count,soldCount)" +
				" value(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, meal.getId(), meal.getName(), meal.getPrice(), meal.getCategory(), meal.getCount(), meal.getImgUrl(), meal.getDescription(), 0);
	}

	/**
	 * 编辑商品信息，区分有无图片的情况。
	 */
	@Permission(Permission.P.Admin)
	public void doEdit(@NotNull Meal meal) throws SQLException {
		String sql = "update Meal set name=?,price=?,category=?,count=?,description=? ";
		List<Object> params = new ArrayList<>();
		Collections.addAll(params,
				meal.getName(), meal.getPrice(), meal.getCategory(), meal.getCount(), meal.getDescription()
		);
		if (!StringExt.isSpace(meal.getImgUrl())) {
			sql += ",imgUrl=?";
			params.add(meal.getImgUrl());
		}
		sql += "where id=? ";
		params.add(meal.getId());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, params.toArray());
	}

	/**
	 * 根据Id删除商品。
	 */
	public void doDelete(@NotNull String id) throws SQLException {
		String sql = "delete from Meal where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}


	/**
	 * 根据Id查询餐品。
	 */
	@Permission(Permission.P.All)
	public Meal findById(@NotNull String id) throws SQLException {
		String sql = "select * from Meal where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Meal.class), id);
	}

	/**
	 * 多条件查询餐品。
	 */
	@Permission(Permission.P.All)
	public List<Meal> findByConditions(@Nullable String id, @Nullable String name, @Nullable String category, @Nullable String minPrice, @Nullable String maxPrice) throws SQLException {
		String sql = "select * from Meal where 1=1";
		List<Object> params = new ArrayList<>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (!StringExt.isSpace(id)) {
			sql += " and id=? ";
			params.add(id);
		}
		if (!StringExt.isSpace(name)) {
			sql += " and name=? ";
			params.add(name);
		}
		if (!StringExt.isSpace(category)) {
			sql += " and category=? ";
			params.add(category);
		}
		if (!StringExt.isSpace(minPrice) && !StringExt.isSpace(maxPrice)) {
			sql += " and price between ? and ? ";
			params.add(minPrice);
			params.add(maxPrice);
		}
		return runner.query(sql, new BeanListHandler<>(Meal.class), params.toArray());
	}

	/**
	 * 查询所有餐品，可选分类。
	 */
	@Permission(Permission.P.All)
	public List<Meal> findAllWithCategory(@Nullable String category) throws SQLException {
		String sql = "select * from Meal where 1=1";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (StringExt.isSpace(category) || StringExt.equalsE(category, EMeal_Category.Default)) {
			return runner.query(sql, new BeanListHandler<>(Meal.class));
		} else {
			sql += " and category=?";
			return runner.query(sql, new BeanListHandler<>(Meal.class), category);
		}
	}

//	/**
//	 * 查询所有餐品，分页显示，可选分类。
//	 */
//	@Permission(Permission.P.All)
//	public List<Meal> findAllInPage(int pageIndex, int count, @NotNull String category) throws SQLException {
//		String sql = "select * from Meal where 1=1";
//		Object[] params;
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		if (StringExt.equalsE(category, EMeal_Category.Default)) {
//			sql += "limit ?,?";
//			params = new Object[]{(pageIndex - 1) * count, count};
//		} else {
//			sql += " and category=? limit ?,?";
//			params = new Object[]{category, (pageIndex - 1) * count, count};
//		}
//		return runner.query(sql, new BeanListHandler<>(Meal.class), params);
//	}


	/**
	 * 得到餐品总数，可选分类。
	 */
	@Permission(Permission.P.Basic)
	public int getTotalCountWithCategory(@Nullable String category) throws SQLException {
		String sql = "select count(*) from Meal";
		Long count;
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (StringExt.isSpace(category) || StringExt.equalsE(category, EMeal_Category.Default)) {
			count = runner.query(sql, new ScalarHandler<>());
		} else {
			sql += " where category=?";
			count = runner.query(sql, new ScalarHandler<>(), category);
		}
		return count.intValue();
	}


	/**
	 * 得到销售榜单。
	 */
	@Permission(Permission.P.View)
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
	 * @param count 得到的数量
	 */
	@Permission(Permission.P.View)
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
	 * 根据餐品名字进行模糊查询。
	 */
	@Permission(Permission.P.View)
	public List<Meal> searchByName(@NotNull String searchField) throws SQLException {
		String sql = "select * from Meal where name like '%" + searchField + "%'";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Meal.class));
	}


	/**
	 * 根据餐品名字进行模糊查询，分页显示。
	 */
	@Permission(Permission.P.View)
	public List<Meal> searchByNameInPage(int pageIndex, int count, @NotNull String searchField) throws SQLException {
		String sql = "select * from Meal where name like '%" + searchField + "%' limit ?,?";

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Meal.class), pageIndex - 1, count);
	}
}
