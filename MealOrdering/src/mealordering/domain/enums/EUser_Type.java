package mealordering.domain.enums;

/**
 * 用户类型的枚举
 */
public enum EUser_Type {
	Client, Admin;

	@Override
	public String toString() {
		switch (name()) {
			case "Client":
				return "普通用户";
			case "Admin":
				return "管理员";
			default:
				return "普通用户";
		}
	}
}

