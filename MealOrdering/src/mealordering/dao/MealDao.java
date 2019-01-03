/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package mealordering.dao;

import mealordering.domain.Meal;
import mealordering.domain.OrderItem;
import mealordering.domain.WeekHotMeal;
import mealordering.enums.Category;
import mealordering.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static windea.ext.StringExt.equalsE;

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
		@Language("MySQL")
		String sql = "insert into Meal(id,name,price,category,imgUrl,description,count,soldCount)" +
				" value(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, meal.getId(), meal.getName(), meal.getPrice(), meal.getCategory(), meal.getCount(),
				meal.getImgUrl(), meal.getDescription(), 0);
	}

	/**
	 * 编辑餐品信息，区分有无图片的情况。
	 * @param meal 餐品信息
	 */
	public void doEdit(@NotNull Meal meal) throws SQLException {
		@Language("MySQL")
		String sql = "update Meal set name=?,price=?,category=?,count=?,description=? ";
		List<Object> paramList = new ArrayList<>();
		paramList.add(meal.getName());
		paramList.add(meal.getPrice());
		paramList.add(meal.getCategory());
		paramList.add(meal.getCount());
		paramList.add(meal.getDescription());
		if(!meal.getImgUrl().isEmpty()) {
			sql += ",imgUrl=?";
			paramList.add(meal.getImgUrl());
		}
		sql += "where id=? ";
		paramList.add(meal.getId());
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, paramList.toArray());
	}

	/**
	 * 根据Id删除餐品。
	 * @param id 餐品Id
	 */
	public void doDeleteById(@NotNull String id) throws SQLException {
		@Language("MySQL")
		String sql = "delete from Meal where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}


	/**
	 * 根据Id查询餐品。
	 * @param id 餐品Id
	 */
	public Meal findById(@NotNull String id) throws SQLException {
		@Language("MySQL")
		String sql = "select * from Meal where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Meal.class), id);
	}

	/**
	 * 根据分类查询餐品（为全部分类时自动查找全部）。
	 * @param category 餐品分类
	 */
	public List<Meal> searchByCategory(@NotNull String category) throws SQLException {
		@Language("MySQL")
		String sql = "select * from Meal where 1=1";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if(category.isEmpty() || equalsE(category, Category.all)) {
			return runner.query(sql, new BeanListHandler<>(Meal.class));
		} else {
			sql += " and category=?";
			return runner.query(sql, new BeanListHandler<>(Meal.class), category);
		}
	}

	/**
	 * 查询所有餐品。
	 */
	public List<Meal> findAll() throws SQLException {
		@Language("MySQL")
		String sql = "select * from Meal";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Meal.class));
	}

	/**
	 * 得到销售榜单。
	 * @param year 年
	 * @param month 月
	 */
	public List<Object[]> getSalesList(@NotNull String year, @NotNull String month) throws SQLException {
		@Language("MySQL")
		String sql = "select Meal.name,sum(OrderItem.buyCount) salesCount" +
				" from `Order`,Meal,OrderItem" +
				" where `Order`.id=OrderItem.order_id and Meal.id=OrderItem.meal_id" +
				" and `Order`.payState=1 and month(`Order`.orderTime) =? and year(`Order`.orderTime) =?" +
				" group by Meal.name" +
				" order by salesCount desc";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month);
	}

	/**
	 * 得到指定数量的本周热销商品。
	 * @param count 要得到的数量
	 */
	public List<WeekHotMeal> getWeekHotMeals(int count) throws SQLException {
		@Language("MySQL")
		String sql = "select Meal.id,Meal.name,Meal.imgUrl,Meal.description,sum(OrderItem.buyCount) salesCount" +
				" from OrderItem,`Order`,Meal" +
				" where OrderItem.order_id = `Order`.id and Meal.id = OrderItem.meal_id" +
				" and `Order`.payState=1 and `Order`.orderTime > DATE_SUB(NOW(), INTERVAL 7 DAY)" +
				" group by Meal.id,Meal.name,Meal.imgUrl" +
				" order by salesCount desc" +
				" limit 0, ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, rs -> {
			List<WeekHotMeal> list = new ArrayList<>();
			while(rs.next()) {
				WeekHotMeal weekHot = new WeekHotMeal();
				weekHot.setId(rs.getString("Meal.id"));
				weekHot.setName(rs.getString("Meal.name"));
				weekHot.setImgUrl(rs.getString("Meal.imgUrl"));
				weekHot.setDescription(rs.getString("Meal.description"));
				weekHot.setSalesCount(rs.getInt("salesCount"));
				list.add(weekHot);
			}
			return list;
		}, count);
	}

	/**
	 * 根据餐品名字进行模糊搜索。
	 * @param name 餐品名字
	 */
	public List<Meal> searchByName(@NotNull String name) throws SQLException {
		@Language("MySQL")
		String sql = "select * from Meal where name like '%" + name + "%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Meal.class));
	}

	/**
	 * 多条件搜索餐品。
	 */
	public List<Meal> searchByConditions(@NotNull String name, @NotNull String category,
			@NotNull String minPrice, @NotNull String maxPrice)
	throws SQLException {
		@Language("MySQL")
		String sql = "select * from Meal where 1=1";
		List<Object> paramList = new ArrayList<>();
		if(!name.isEmpty()) {
			sql += " and name like '%" + name + "%'";
		}
		if(!category.isEmpty()) {
			sql += " and category=? ";
			paramList.add(category);
		}
		if(!minPrice.isEmpty() && !maxPrice.isEmpty()) {
			sql += " and price between ? and ? ";
			paramList.add(minPrice);
			paramList.add(maxPrice);
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Meal.class), paramList.toArray());
	}

	/**
	 * 根据订单餐品中的购买数量，更新餐品库存数量。
	 * @param itemList 订单餐品列表
	 * @param doAdd 是否进行增加操作
	 */
	public void updateMealCount(@NotNull List<OrderItem> itemList, boolean doAdd) throws SQLException {
		@Language("MySQL")
		String sql = "update Meal set count= count+? where id=?";
		QueryRunner runner = new QueryRunner();
		Object[][] params = new Object[itemList.size()][2];
		for(int i = 0; i < params.length; i++) {
			int delta = itemList.get(i).getBuyCount();
			params[i][0] = doAdd ? delta : -delta;
			params[i][1] = itemList.get(i).getMeal().getId();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}
}
