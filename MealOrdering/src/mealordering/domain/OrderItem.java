package mealordering.domain;

import java.io.Serializable;

/**
 * 订单物品的实体类
 */
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属订单 */
	private Order order;
	/** 餐品 */
	private Meal meal;
	/** 购买数量 */
	private int buyCount;


	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal p) {
		this.meal = p;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}
}
