package mealordering.dao;

/**
 * Dao的工厂类
 */
public class DaoFactory {
	public static MealDao getMealDao() {
		return new MealDao();
	}

	public static NoticeDao getNoticeDao() {
		return new NoticeDao();
	}

	public static OrderDao getOrderDao() {
		return new OrderDao();
	}

	public static OrderItemDao getOrderItemDao() {
		return new OrderItemDao();
	}

	public static NormalUserDao getNormalUserDao() {
		return new NormalUserDao();
	}

	public static AdminDao getAdminDao() {
		return new AdminDao();
	}


}
