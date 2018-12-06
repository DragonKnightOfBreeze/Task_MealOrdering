package mealordering.web.servlet.view;

import dk_breeze.utils.ext.MathExt;
import dk_breeze.utils.ext.RandomExt;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 前台页面，生成验证码的工具类
 * @noinspection SameParameterValue
 */
@WebServlet(name = "CheckImageServlet", urlPatterns = {"checkImage"})
public class CheckImageServlet extends HttpServlet {
	//字符的来源列表
	private List<String> wordList = new ArrayList<>();

	@Override
	public void init() {
		//设置字符的来源列表
		setWordList("/WEB-INF/new_words.txt", wordList);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//禁止缓存
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);

		//绘制缓存验证码图片
		BufferedImage bufferedImage = drawBufferedCheckImage(request, wordList, new Font("宋体", Font.BOLD, 18));
		//将上面图片输出到浏览器 ImageIO
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	/**
	 * 设置字符的来源列表
	 * @param relativePath 相对路径
	 */
	private void setWordList(@NotNull String relativePath, List<String> wordList) {
		//初始化阶段，读取new_words.txt
		//web工程中读取文件，必须使用绝对磁盘路径
		String path = getServletContext().getRealPath(relativePath);
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
			String line;
			while ((line = reader.readLine()) != null)
				wordList.add(line);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 绘制缓存验证码图片。
	 * TODO 提取参数
	 * @param request 请求
	 * @param font 随机字符的字体
	 * @param wordList 随机字符的来源列表
	 * @return 缓存验证码图片
	 */
	private BufferedImage drawBufferedCheckImage(HttpServletRequest request, List<String> wordList, Font font) {
		//步骤一 绘制一张内存中的图片
		int width = 180;
		int height = 30;
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		//步骤二 为图片绘制背景颜色。绘制任何图形之前 都必须指定一个颜色
		//得到画图对象 --- 画笔
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width - 1, height - 1);

		//步骤三 绘制边框
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width, height);
		Graphics2D graphics2d = (Graphics2D) graphics;

		//步骤四 设置四个随机字符
		//设置输出字体
		graphics2d.setFont(font);
		//获得成语
		String word = wordList.get(RandomExt.range(wordList.size()) - 1);
		//定义x坐标
		int x = 10;
		for (int i = 0; i < word.length(); i++) {
			//随机颜色
			graphics2d.setColor(new Color(RandomExt.range(20, 130), RandomExt.range(20, 130), RandomExt.range(20, 130)));
			//旋转-30度到30度
			int angle = RandomExt.range(-30, 30);
			//换算弧度
			double theta = angle * Math.PI / 180;
			//获得字母数字
			char c = word.charAt(i);
			//将c 输出到图片
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 40;
		}
		//将验证码内容保存session
		request.getSession().setAttribute("checkCode_session", word);

		//步骤五 绘制干扰线
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1 = RandomExt.range(width);
			x2 = RandomExt.range(12);
			y1 = RandomExt.range(height);
			y2 = RandomExt.range(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}
		//释放资源
		graphics.dispose();

		return bufferedImage;
	}

	/**
	 * 取某一范围的color。
	 * @param fc int 范围参数1
	 * @param bc int 范围参数2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		//参数处理
		fc = MathExt.clamp(fc, 0, 255);
		bc = MathExt.clamp(bc, 0, 255);
		//取其随机颜色
		Random random = new Random();
		//取到随机的rgb值
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
