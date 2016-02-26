package org.ionnic.app.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

public class CreateImage {
    private static final String SESSION_KEY = CreateImage.class.getName() + ".IMAGE_NAME";
    // 图片的宽度。
    private int width = 160;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 20;

    private Color colors[] = new Color[] { new Color(0, 0, 0), new Color(0, 255, 0), new Color(0, 139, 0), new Color(0, 0, 255),
            new Color(0, 206, 209), new Color(255, 215, 0), new Color(255, 106, 106), new Color(178, 34, 34), new Color(255, 0, 0),
            new Color(255, 20, 147), new Color(238, 44, 44), new Color(138, 43, 226), new Color(72, 118, 255), new Color(255, 0, 255),
            new Color(0, 191, 255), new Color(34, 139, 34) };

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
    }

    /**
     * @param session
     * @param code
     * @return
     */
    public boolean check(HttpSession session, String code) {
        String sessionCode = (String) session.getAttribute(SESSION_KEY);
        removeSession(session);

        if (sessionCode == null) {
            return false;
        } else {
            return sessionCode.equals(code);
        }
    }

    // 生成图片
    private void creatImage(String code) {
        int fontWidth = width / codeCount;// 字体的宽度
        int fontHeight = height - 2;// 字体的高度
        int codeY = height - 4;

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();

        // 设置背景色
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, width, height);

        // 设置干扰线
        for (int i = 0; i < lineCount; i++) {
            Font font = getFont(fontHeight);
            g.setFont(font);

            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);

            g.setColor(getRandColor());
            g.drawLine(xs, ys, xe, ye);
        }

        // 添加噪点
        float yawpRate = 0.01F;
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            buffImg.setRGB(x, y, random.nextInt(255));
        }

        // 得到随机字符
        for (int i = 0; i < codeCount; i++) {
            String strRand = code.substring(i, i + 1);
            g.setColor(getRandColor(1, 255));
            g.drawString(strRand, i * fontWidth + 3, codeY);
        }
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
     * @return
     */
    private Color getRandColor() {
        return colors[random.nextInt(colors.length)];
    }

    // 得到随机颜色
    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    // 得到随机字符
    private String randomStr(int n) {
        String str1 = "1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    /**
     * @param session
     */
    public void removeSession(HttpSession session) {
        session.removeAttribute(SESSION_KEY);
    }

    /**
     * @param httpSession
     * @param sos
     * @throws IOException
     */
    public void write(HttpSession httpSession, OutputStream sos) throws IOException {
        String code = randomStr(codeCount);
        creatImage(code);
        ImageIO.write(buffImg, "png", sos);
        httpSession.setAttribute(SESSION_KEY, code);
        sos.close();
    }
}
