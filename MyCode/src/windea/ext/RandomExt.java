/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package windea.ext;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Random类的拓展类
 */
public class RandomExt {

	public static Random random = new SecureRandom();

	/**
	 * 生成指定范围内的随机数（从0到1）。
	 */
	public static float range01() {
		return random.nextFloat();
	}

	/**
	 * 生成指定范围内的随机数。
	 */
	public static int range(int min, int max) {
		if(min > max)
			throw new IllegalArgumentException();

		return min + random.nextInt(max - min);
	}

	/**
	 * 生成指定范围内的随机数（从0到指定值）。
	 */
	public static int range(int max) {
		return random.nextInt(max);
	}

	/**
	 * 生成指定范围内的随机数（指定小数位数）。
	 */
	public static float range(float min, float max, int bit) {
		if(bit < 0 || bit > 10)
			throw new IllegalArgumentException();

		float bitValue = (float) Math.pow((double) 10, (double) bit);
		return range((int) (min * bitValue), (int) (max * bitValue)) / bitValue;
	}

	/**
	 * 生成指定范围内的随机数（从0到指定值，指定小数位数）。
	 */
	public static float range(float max, int bit) {
		float bitValue = (float) Math.pow((double) 10, (double) bit);
		return range((int) (max * bitValue)) / bitValue;
	}


	/**
	 * 生成浮动范围内的随机数。
	 */
	public static int delta(int num, int sub, int add) {
		int randomDelta = range(sub + add);
		return num - sub + randomDelta;
	}

	/**
	 * 生成浮动范围内的随机数。
	 */
	public static int delta(int num, int delta) {
		return delta(num, delta, delta);
	}

	/**
	 * 生成浮动范围内的随机数（指定位数）。
	 */
	public static float delta(float num, float sub, float add, int bit) {
		if(bit < 0 || bit > 10)
			throw new IllegalArgumentException();

		float bitValue = (float) Math.pow((double) 10, (double) bit);
		return delta((int) (num * bitValue), (int) (sub * bitValue), (int) (add * bitValue)) / bitValue;
	}

	/**
	 * 生成浮动范围内的随机数（指定位数）。
	 */
	public static float delta(float num, float delta, int bit) {
		return delta(num, delta, delta, bit);
	}
}
