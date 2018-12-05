package task_itcaststore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import task_itcaststore.domain.Order;
import task_itcaststore.domain.OrderItem;
import task_itcaststore.domain.Product;
import task_itcaststore.utils.DataSourceUtils;
import task_itcaststore.utils.ext.StringExt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.sun.faces.taglib.jsf_core.MaxMinValidatorTag;

/**
 * 商品的DAO类
 */
public class ProductDao {
	/**
	 * 添加商品。
	 */
	public void addProduct(@NotNull Product product) throws SQLException {
		String sql = "insert into products values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, product.getId(), product.getName(), product.getPrice(), product.getCategory(), product.getPnum(), product.getImgUrl(), product.getDescription());
	}

	/**
	 * 查找所有商品。
	 */
	public List<Product> findAllProducts() throws SQLException {
		String sql = "select * from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * 获取数据总条数。
	 */
	public int getTotalCount(@NotNull String category) throws SQLException {
		String sql = "select count(*) from products";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (!"全部商品".equals(category)) {
			sql += " where category=?";
			Long count = runner.query(sql, new ScalarHandler<>(), category);
			return count.intValue();
		} else {
			Long count = runner.query(sql, new ScalarHandler<>());
			return count.intValue();
		}
	}

	/**
	 * 获取当前页数据。
	 */
	public List<Product> findProductsByPage(int currentPage, int currentCount, @NotNull String category) throws SQLException {
		//要执行的sql语句
		String sql;
		//参数
		Object[] obj;
		//如果category不为null,代表是按分类查找
		if (!"全部商品".equals(category)) {
			sql = "select * from products where category=? limit ?,?";
			obj = new Object[]{category, (currentPage - 1) * currentCount, currentCount,};
		} else {
			sql = "select * from products  limit ?,?";
			obj = new Object[]{(currentPage - 1) * currentCount, currentCount,};
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Product.class), obj);
	}

	/**
	 * 根据id查找商品。
	 */
	public Product findProductById(@NotNull String id) throws SQLException {
		String sql = "select * from products where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<>(Product.class), id);
	}


	/**
	 * 列出销售榜单。
	 * TODO SQL语句是否有错？
	 */
	public List<Object[]> getSalesList(@NotNull String year, @NotNull String month) throws SQLException {
		String sql = "select products.name,sum(orderItems.buyNum) totalSalNum " +
				"from orders,products,orderItems " +
				"where orders.id=orderItems.order_id and products.id=orderItems.product_id " +
				"and orders.payState=1 and date(orderTime) =? and month(orderTime) =? " +
				"group by products.name " +
				"order by totalSalNum desc ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler(), year, month);
	}

	/**
	 * 多条件查询。
	 */
	public List<Product> findProductsByConditions(@Nullable String id, @Nullable String name, @Nullable String category, @Nullable String minPrice, @Nullable String maxPrice) throws SQLException {
		String sql = "select * from products where 1=1 ";

		List<Object> paramList = new ArrayList<>();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		if (!StringExt.isSpace(id)) {
			sql += "and id=? ";
			paramList.add(id);
		}
		if (!StringExt.isSpace(name)) {
			sql += "and name=? ";
			paramList.add(name);
		}
		if (!StringExt.isSpace(category)) {
			sql += "and category=? ";
			paramList.add(category);
		}
		if (!StringExt.isSpace(minPrice) && !StringExt.isSpace(maxPrice)) {
			sql += "and price between ? and ? ";
			paramList.add(minPrice);
			paramList.add(maxPrice);
		}

		Object[] params = paramList.toArray();
		return runner.query(sql, new BeanListHandler<>(Product.class), params);
	}

	/**
	 * 修改商品信息。
	 */
	public void updateProduct(@NotNull Product product) throws SQLException {
		//1.创建集合并将商品信息添加到集合中
		List<Object> obj = new ArrayList<>();
		obj.add(product.getName());
		obj.add(product.getPrice());
		obj.add(product.getCategory());
		obj.add(product.getPnum());
		obj.add(product.getDescription());
		//2.创建sql语句，并拼接sql
		String sql = "update products set name=?,price=?,category=?,pnum=?,description=? ";
		//判断是否有图片
		if (StringExt.isSpace(product.getImgUrl())) {
			sql += ",imgUrl=? ";
			obj.add(product.getImgUrl());
		}
		sql += "where id=? ";
		obj.add(product.getId());
		System.out.println(sql);
		System.out.println(obj);

		//3.创建QueryRunner对象
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//4.使用QueryRunner对象的update()方法更新数据
		runner.update(sql, obj.toArray());
	}

	/**
	 * 删除订单时，修改商品数量。
	 */
	public void updateProductNumWhenDel(@NotNull List<OrderItem> itemList) throws SQLException {
		String sql = "update products set pnum=pnum+? where id=?";
		QueryRunner runner = new QueryRunner();

		Object[][] params = new Object[itemList.size()][2];
		for (int i = 0; i < params.length; i++) {
			params[i][0] = itemList.get(i).getBuyNum();
			params[i][1] = itemList.get(i).getProduct().getId();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	/**
	 * 生成订单时，同时将商品数量减少。
	 */
	public void updateProductNumWhenAdd(@NotNull Order order) throws SQLException {
		String sql = "update products set pnum=pnum-? where id=?";
		QueryRunner runner = new QueryRunner();

		List<OrderItem> itemList = order.getOrderItems();
		Object[][] params = new Object[itemList.size()][2];
		for (int i = 0; i < params.length; i++) {
			params[i][0] = itemList.get(i).getBuyNum();
			params[i][1] = itemList.get(i).getProduct().getId();
		}
		runner.batch(DataSourceUtils.getConnection(), sql, params);
	}

	/**
	 * 前台，获取本周热销商品（两个）。
	 */
	public List<Object[]> getWeekHotProducts() throws SQLException {
		String sql = "select products.id,products.name,products.imgUrl,sum(orderItems.buyNum) totalSalNum " +
				"from orderItems,orders,products " +
				"where orderItems.order_id = orders.id and products.id = orderItems.product_id " +
				"and orders.payState=1 and orders.orderTime > DATE_SUB(NOW(), INTERVAL 7 DAY) " +
				"group by products.id,products.name,products.imgUrl " +
				"order by totalSalNum desc " +
				"limit 0,2 ";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new ArrayListHandler());
	}


	/**
	 * 前台，用于搜索框根据书名来模糊查询相应的图书。
	 * TODO 在套用模版时更改
	 */
	public List<Product> findBooksByName(int currentPage, int currentCount, @NotNull String searchField) throws SQLException {
		//根据名字模糊查询图书
		String sql = "select * from products where name like '%" + searchField + "%' limit ?,?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<>(Product.class), currentPage - 1, currentCount);
	}

	/**
	 * 前台搜索框，返回根据书名模糊查询出的图书总数量。
	 * TODO 在套用模版时更改
	 */
	public int findBooksByNameGetTotalCount(@NotNull String searchField) throws SQLException {
		String sql = "select count(*) from products where name like '%" + searchField + "%'";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//查询出满足条件的总数量，为long类型
		Long count = runner.query(sql, new ScalarHandler<>());
		return count.intValue();
	}

	/**
	 * 后台系统，根据id删除商品信息。
	 */
	public void deleteProductById(@NotNull String id) throws SQLException {
		String sql = "delete from products where id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, id);
	}
}
