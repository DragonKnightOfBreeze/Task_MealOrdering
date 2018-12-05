package task_itcaststore.utils;

import java.util.UUID;

/**
 * 获取uuid的工具类
 * @noinspection unused, WeakerAccess
 */
public class IdUtils {
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
