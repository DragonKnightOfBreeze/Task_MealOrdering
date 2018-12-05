package mealordering.domain.enums;

public enum EMeal_Category {
	Default, Vegetarian, Carnivorous, RawMeat, Drink, Dessert, DarkCooking;

	@Override
	public String toString() {
		switch (name()) {
			case "Vegetarian":
				return "素食";
			case "Carnivorous":
				return "素食";
			case "RawMeat":
				return "生肉";
			case "Drink":
				return "饮料";
			case "Dessert":
				return "甜点";
			case "DarkCooking":
				return "黑暗料理";
			default:
				return "默认";
		}
	}
}
