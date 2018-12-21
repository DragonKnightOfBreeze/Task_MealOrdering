package mealordering.service;

/**
 * Service的工厂类
 */
public class ServiceFactory {
	public static AdminService getAdminSvc() {
		return new AdminService();
	}

	public static MealService getMealSvc() {
		return new MealService();
	}

	public static NormalUserService getNormalUserSvc() {
		return new NormalUserService();
	}

	public static NoticeService getNoticeSvc() {
		return new NoticeService();
	}

	public static OrderService getOrderSvc() {
		return new OrderService();
	}
}
