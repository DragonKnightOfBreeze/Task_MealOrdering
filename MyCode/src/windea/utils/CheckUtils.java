/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package windea.utils;

import org.jetbrains.annotations.NotNull;
import windea.ext.RandomExt;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

/**
 * 验证的工具类
 * @noinspection unused, WeakerAccess
 */
public class CheckUtils {
	private static int width = 60;
	private static int height = 20;

//	/**
//	 * 得到字符验证码图片，输出到servlet。默认字符长度为4。
//	 */
//	public static void printCheckImg(HttpServletRequest req, HttpServletResponse resp,String attrName) throws IOException {
//		//首先就要得到session对象
//		HttpSession session = req.getSession();
//		//设置内容类型为图片，设置浏览器不要缓存此图片
//		resp.setContentType("image/jpeg");
//		resp.setHeader("Pragma", "No-cache");
//		resp.setHeader("Cache-Control", "no-cache");
//		resp.setDateHeader("Expires", 0);
//		//将图像输出到客户端
//		char[] checkCode = getCheckCode(4);
//		ImageIO.write(getCheckImg(checkCode),"jpeg", resp.getOutputStream());
//		//将当前验证码存入到Session中
//		session.setAttribute(attrName, checkCode);
//	}

	/**
	 * 得到一张默认的字符验证码图片。
	 */
	@NotNull
	public static BufferedImage getCheckImg(@NotNull char[] checkCode) {
		Color fontColor = new Color(0x1932C0);
		Font font = new Font("宋体", Font.ITALIC | Font.BOLD, 18);
		Color bgColor = Color.lightGray;
		return getCheckImg(checkCode, fontColor, font, bgColor);
	}

	/**
	 * 得到一张字符验证码图片。
	 */
	@NotNull
	public static BufferedImage getCheckImg(@NotNull char[] checkCode, @NotNull Color fontColor, @NotNull Font font,
			@NotNull Color bgColor) {
		width = 15 * checkCode.length;
		//创建内存图象并获得其图形上下文
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//绘制一张字符验证码图片
		Graphics2D graphics2D = image.createGraphics();
		drawBackground(graphics2D, bgColor);
		drawCheckCode(graphics2D, checkCode, fontColor, font);
		//结束图像的绘制过程，完成图像
		graphics2D.dispose();
		return image;
	}


	/**
	 * 生成指定位数的随机字符验证码。
	 * @param length 随机字符验证码的长度
	 */
	@NotNull
	public static char[] getCheckCode(int length) {
		char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
		return getCheckCode(length, chars);
	}

	/**
	 * 生成指定位数的随机字符验证码。
	 * @param length 随机字符验证码的长度
	 * @param chars 字符范围
	 */
	@NotNull
	public static char[] getCheckCode(int length, @NotNull char[] chars) {
		char[] checkCode = new char[length];
		IntStream.range(0, length).forEach(i -> {
			int rand = RandomExt.range(chars.length);
			checkCode[i] = chars[rand];
		});

		return checkCode;
	}

	/**
	 * 将随机字符验证码画到图片上。
	 * @param graphics2D 图片
	 * @param checkCode 随机字符验证码
	 * @param fontColor 字符验证码的颜色
	 * @param font 字符验证码的样式
	 */
	private static void drawCheckCode(@NotNull Graphics2D graphics2D, @NotNull char[] checkCode, Color fontColor,
			Font font) {
		//设置字符验证码的颜色和样式
		graphics2D.setColor(fontColor);
		graphics2D.setFont(font);
		//在不同的高度上输出验证码的每个字符
		for(int i = 0; i < checkCode.length; i++) {
			float theta = RandomExt.range(-30, 30) * 3.14f / 180;
			int x = 15 * i + 1;
			int y = RandomExt.range(15, 20);
			graphics2D.rotate(theta, x, y);
			graphics2D.drawString("" + checkCode[i], x, y);
			graphics2D.rotate(-theta, x, y);
		}
	}

	/**
	 * 为图片绘制干扰背景。
	 * @param graphics 图片
	 * @param bgColor 背景颜色
	 */
	private static void drawBackground(@NotNull Graphics graphics, Color bgColor) {
		//绘制背景
		graphics.setColor(bgColor);
		graphics.fillRect(0, 0, width, height);
		//随机产生干扰点
		IntStream.range(0, width * 4).forEach(i -> {
			int x = RandomExt.range(width);
			int y = RandomExt.range(height);
			int red = RandomExt.range(255);
			int green = RandomExt.range(255);
			int blue = RandomExt.range(255);
			graphics.setColor(new Color(red, green, blue));
			graphics.drawOval(x, y, 1, 0);
		});
	}
}


