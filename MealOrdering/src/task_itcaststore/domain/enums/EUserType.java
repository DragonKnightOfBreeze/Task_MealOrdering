package task_itcaststore.domain.enums;

/**
 * 用户类型的枚举
 */
public enum EUserType {
	Client, Admin;

	@Override
	public String toString() {
		switch (this.name()) {
			case "Client":
				return "普通用户";
			case "Admin":
				return "超级用户";
			default:
				return "普通用户";
		}
	}
}

