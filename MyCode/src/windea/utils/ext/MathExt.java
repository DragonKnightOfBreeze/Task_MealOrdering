/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package windea.utils.ext;

import org.jetbrains.annotations.Contract;
import windea.exception.NotImplementedException;

/**
 * Math类的拓展类
 */
public class MathExt {

	/**
	 * 夹值方法。
	 */
	@Contract(pure = true)
	public static double clamp(double num, double min, double max) {
		if(min > max)
			throw new IllegalArgumentException();

		if(num < min)
			num = min;
		else if(num > max)
			num = max;
		return num;
	}

	/**
	 * 夹值方法。
	 */
	@Contract(pure = true)
	public static float clamp(float num, float min, float max) {
		return (float) clamp((double) num, (double) min, (double) max);
	}

	/**
	 * 夹值方法。
	 */
	@Contract(pure = true)
	public static int clamp(int num, int min, int max) {
		return (int) clamp((double) num, (double) min, (double) max);
	}


	@Contract(pure = true)
	public static double clamp01(double num) {
		if(num < 0)
			num = 0;
		else if(num > 1)
			num = 1;
		return num;
	}

	/**
	 * 夹值方法（0到1）。
	 */
	@Contract(pure = true)
	public static float clamp01(float num) {
		return (float) clamp01((double) num);
	}


	/**
	 * 插值方法。
	 */
	public static float lerp(float num, float target, float speed) {
		throw new NotImplementedException();
	}
}
