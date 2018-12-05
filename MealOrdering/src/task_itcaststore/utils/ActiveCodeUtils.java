package task_itcaststore.utils;

import java.util.UUID;

/**
 * 生成注册激活码的工具类
 * @noinspection unused, WeakerAccess
 */
public class ActiveCodeUtils {
	public static String createActiveCode() {
		return UUID.randomUUID().toString();
	}
}
