package mealordering.service;

import dk_breeze.exception.NotImplementedException;
import mealordering.dao.DaoFactory;
import mealordering.dao.MealDao;
import mealordering.domain.BeanPage;
import mealordering.domain.Meal;
import org.jetbrains.annotations.NotNull;

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
	public void doAdd(@NotNull Meal meal) {
		try {
			dao.doAdd(meal);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑餐品信息，区分有无图片的情况。
	 * @param meal 餐品信息
	 */
	public void doEdit(@NotNull Meal meal) {
		try {
			dao.doEdit(meal);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Id删除餐品。
	 * @param id 餐品Id
	 */
	public void doDeleteById(@NotNull String id) {
		try {
			dao.doDeleteById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 根据Id查询餐品。
	 * @param id 餐品Id
	 */
	public Meal findById(@NotNull String id) {
		Meal meal = null;
		try {
			meal = dao.findById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return meal;
	}

	/**
	 * TODO
	 */
	public BeanPage<Meal> findAllInPage() {
		throw new NotImplementedException();
	}

	/**
	 * 根据餐品名字进行模糊查询，分页显示。
	 * @param pageIndex 页面索引
	 * @param count 每页条数
	 * @param searchField 搜索域
	 */
	public BeanPage<Meal> searchByNameInPage(@NotNull String searchField, int pageIndex, int count) {
		BeanPage<Meal> mealPage = null;
		try {
			List<Meal> mealList = dao.searchByName(searchField);
			mealPage = new BeanPage<>(pageIndex, count, mealList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return  mealPage;
	}

	/**
	 * 根据分类查询餐品，分页显示。
	 * @param category 餐品分类
	 */
	public BeanPage<Meal> searchByCategoryInPage(@NotNull String category, int pageIndex, int count) {
		BeanPage<Meal> mealPage = null;
		try {
			List<Meal> mealList = dao.findByCategory(category);
			mealPage = new BeanPage<>(pageIndex, count, mealList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mealPage;
	}

	/**
	 * 多条件查询餐品，分页显示。
	 */
	public BeanPage<Meal> searchByConditionsInPage(@NotNull String id, @NotNull String name, @NotNull String category, @NotNull String minPrice, @NotNull String maxPrice, int pageIndex, int count) {
		BeanPage<Meal> mealPage = null;
		try {
			List<Meal> mealList = dao.searchByConditions(id, name, category, minPrice, maxPrice);
			mealPage = new BeanPage<>(pageIndex, count, mealList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mealPage;
	}


	/**
	 * 得到销售榜单。
	 * @param year 年
	 * @param month 月
	 */
	public List<Object[]> getSalesList(@NotNull String year, @NotNull String month) {
		List<Object[]> list = null;
		try {
			list = dao.getSalesList(year, month);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	/**
	 * 得到指定数量的本周热销商品。
	 * @param count 要得到的数量
	 */
	public List<Object[]> getWeekHotMeals(int count) {
		List<Object[]> list = null;
		try {
			list = dao.getWeekHotProducts(count);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
