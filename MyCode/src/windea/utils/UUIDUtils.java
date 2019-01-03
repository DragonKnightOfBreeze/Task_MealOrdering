/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package windea.utils;

import java.util.UUID;

/**
 * uuid的工具类
 * @noinspection unused, WeakerAccess
 */
public class UUIDUtils {
	/**
	 * 得到随机的uuid。
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
}
