package mealordering.service;

import mealordering.dao.DaoFactory;
import mealordering.dao.MealDao;
import mealordering.domain.BeanPage;
import mealordering.domain.Meal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
	 * 多条件查询餐品。
	 */
	public List<Meal> findByConditions(@Nullable String id, @Nullable String name, @Nullable String category, @Nullable String minPrice, @Nullable String maxPrice) {
		List<Meal> mealList = null;
		try {
			mealList = dao.findByConditions(id, name, category, minPrice, maxPrice);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mealList;
	}


	/**
	 * 根据分类查询餐品，分页显示。
	 * @param category 餐品分类
	 */
	public BeanPage<Meal> findByCategoryInPage(@Nullable String category, int pageIndex, int count) {
		BeanPage<Meal> mealPage = new BeanPage<>(pageIndex, count);
		mealPage.setCategory(category);
		try {
			//得到总条数
			int searchedCount = dao.findByCategoryGetCount(category);
			mealPage.setTotalCount(searchedCount);
			//得到总页数
			int pageCount = (int) Math.ceil(searchedCount * 1.0 / count);
			mealPage.setPageCount(pageCount);
			//得到满足条件的餐品
			List<Meal> mealList = dao.findByCategoryInPage(category, pageIndex, count);
			mealPage.setBeanList(mealList);
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


	/**
	 * 根据餐品名字进行模糊查询，分页显示。
	 * @param pageIndex 页面索引
	 * @param count 每页条数
	 * @param searchField 搜索域
	 */
	public BeanPage<Meal> searchByNameInPage(@NotNull String searchField, int pageIndex, int count) {
		BeanPage<Meal> mealPage = new BeanPage<>(pageIndex, count);
		mealPage.setSearchField(searchField);
		try {
			//得到总条数
			int searchedCount = dao.searchByNameGetCount(searchField);
			mealPage.setTotalCount(searchedCount);
			//得到总页数
			int pageCount = (int) Math.ceil(searchedCount * 1.0 / count);
			mealPage.setPageCount(pageCount);
			//得到满足条件的餐品
			List<Meal> mealList = dao.searchByNameInPage(searchField, pageIndex, count);
			mealPage.setBeanList(mealList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mealPage;
	}


}
