package mealordering.enums;

/**
 * 用户类型的枚举
 */
public enum Identity {
	normalUser, admin;

	@Override
	public String toString() {
		switch(name()) {
			case "normalUser":
				return "普通用户";
			case "admin":
				return "管理员";
			default:
				return "普通用户";
		}
	}
}

