package mealordering.service;

import dkbreeze.utils.ext.ListExt;
import mealordering.dao.DaoFactory;
import mealordering.dao.MealDao;
import mealordering.domain.Meal;
import mealordering.domain.WeekHotMeal;
import mealordering.exception.ResultEmptyException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

/**
 * 餐品的服务类
 */
public class MealService {
	private MealDao dao = DaoFactory.getMealDao();

	/**
	 * 添加餐品。
	 * @param meal 餐品信息
	 */
	public void doAdd(@NotNull Meal meal) throws SQLException {
		dao.doAdd(meal);
	}

	/**
	 * 编辑餐品信息，区分有无图片的情况。
	 * @param meal 餐品信息
	 */
	public void doEdit(@NotNull Meal meal) throws SQLException {
		dao.doEdit(meal);
	}

	/**
	 * 根据Id删除餐品。
	 * @param id 餐品Id
	 */
	public void doDeleteById(@NotNull String id) throws SQLException {
		dao.doDeleteById(id);
	}

	/**
	 * 根据Id查询餐品。
	 * @param id 餐品Id
	 */
	public Meal findById(@NotNull String id) throws SQLException, ResultEmptyException {
		Meal meal = dao.findById(id);
		if(meal == null)
			throw new ResultEmptyException();
		return meal;
	}

	/**
	 * 查询所有餐品。
	 */
	public List<Meal> findAll() throws SQLException, ResultEmptyException {
		List<Meal> mealList = dao.findAll();
		if(ListExt.orEmpty(mealList))
			throw new ResultEmptyException();
		return mealList;
	}

	/**
	 * 根据餐品名字进行模糊查询。
	 * @param name 餐品名
	 */
	public List<Meal> searchByName(@NotNull String name) throws SQLException, ResultEmptyException {
		List<Meal> mealList = dao.searchByName(name);
		if(ListExt.orEmpty(mealList))
			throw new ResultEmptyException();
		return mealList;
	}

	/**
	 * 根据分类查询餐品，分页显示。
	 * @param category 餐品分类
	 */
	public List<Meal> searchByCategory(@NotNull String category) throws SQLException, ResultEmptyException {
		List<Meal> mealList = dao.searchByCategory(category);
		if(ListExt.orEmpty(mealList))
			throw new ResultEmptyException();
		return mealList;
	}

	/**
	 * 多条件查询餐品，分页显示。
	 */
	public List<Meal> searchByConditions(@NotNull String name, @NotNull String category,
			@NotNull String minPrice, @NotNull String maxPrice)
	throws SQLException, ResultEmptyException {
		List<Meal> mealList = dao.searchByConditions(name, category, minPrice, maxPrice);
		if(ListExt.orEmpty(mealList))
			throw new ResultEmptyException();
		return mealList;
	}

	/**
	 * 得到销售榜单。
	 * @param year 年
	 * @param month 月
	 */
	public List<Object[]> getSalesList(@NotNull String year, @NotNull String month)
	throws SQLException, ResultEmptyException {
		List<Object[]> salasList = dao.getSalesList(year, month);
		if(ListExt.orEmpty(salasList))
			throw new ResultEmptyException();
		return salasList;
	}

	/**
	 * 得到指定数量的本周热销商品。
	 * @param count 要得到的数量
	 */
	public List<WeekHotMeal> getWeekHotMeals(int count) throws SQLException, ResultEmptyException {
		List<WeekHotMeal> weekHot = dao.getWeekHotMeals(count);
		if(ListExt.orEmpty(weekHot))
			throw new ResultEmptyException();
		return weekHot;
	}
}
