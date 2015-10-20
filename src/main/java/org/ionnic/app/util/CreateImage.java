package org.ionnic.app.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class CreateImage {
	// 图片的宽度。
	private int width = 160;
	// 图片的高度。
	private int height = 40;
	// 验证码字符个数
	private int codeCount = 4;
	// 验证码干扰线数
	private int lineCount = 20;
	// 验证码
	private String code = null;

	// 验证码图片Buffer
	private BufferedImage buffImg = null;

	Random random = new Random();

	/**
	 * @param width
	 * @param height
	 */
	public CreateImage(int width, int height) {
		this.width = width;
		this.height = height;
		creatImage();
	}

	/**
	 * @param width
	 * @param height
	 * @param codeCount
	 */
	public CreateImage(int width, int height, int codeCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		creatImage();
	}

	/**
	 * @param width
	 * @param height
	 * @param codeCount
	 * @param lineCount
	 */
	public CreateImage(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
		creatImage();
	}

	// 生成图片
	private void creatImage() {
		int fontWidth = width / codeCount;// 字体的宽度
		int fontHeight = height - 5;// 字体的高度
		int codeY = height - 8;

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();

		// 设置背景色
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, width, height);

		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {

			// 设置字体
			Font font = getFont(fontHeight);
			g.setFont(font);

			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(getRandColor(50, 225));
			g.drawLine(xs, ys, xe, ye);
		}

		// 添加噪点
		float yawpRate = 0.01F;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			buffImg.setRGB(x, y, random.nextInt(255));
		}

		String str1 = randomStr(codeCount);// 得到随机字符
		this.code = str1;
		for (int i = 0; i < codeCount; i++) {
			String strRand = str1.substring(i, i + 1);
			g.setColor(getRandColor(1, 255));
			g.drawString(strRand, i * fontWidth + 3, codeY);
		}
	}

	// 得到随机字符
	private String randomStr(int n) {
		String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}

	// 得到随机颜色
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 产生随机字体
	 */
	private Font getFont(int size) {
		Random random = new Random();
		Font font[] = new Font[5];

		font[0] = new Font("Ravie", Font.BOLD, size);
		font[1] = new Font("Antique Olive Compact", Font.BOLD, size);
		font[2] = new Font("Fixedsys", Font.BOLD, size);
		font[3] = new Font("Wide Latin", Font.BOLD, size);
		font[4] = new Font("Gill Sans Ultra Bold", Font.BOLD, size);
		return font[random.nextInt(5)];
	}

	/**
	 * @param sos
	 * @throws IOException
	 */
	public void write(OutputStream sos) throws IOException {
		ImageIO.write(buffImg, "png", sos);
		sos.close();

	}

	public String getCode() {
		return code.toLowerCase();
	}
}
