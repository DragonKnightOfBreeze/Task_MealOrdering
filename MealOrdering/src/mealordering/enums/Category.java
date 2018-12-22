package mealordering.enums;

/**
 * 餐品类别的枚举
 */
public enum Category {
	all, vegetarian, carnivorous, dessert, darkCooking;

	@Override
	public String toString() {
		switch(name()) {
			case "vegetarian":
				return "素食";
			case "carnivorous":
				return "荤食";
			case "dessert":
				return "甜点";
			case "darkCooking":
				return "黑暗料理";
			default:
				return "所有分类";
		}
	}
}
