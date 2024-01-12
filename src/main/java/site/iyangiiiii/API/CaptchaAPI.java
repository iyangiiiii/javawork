package site.iyangiiiii.API;

import site.iyangiiiii.Utils.ErrorUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaAPI {
    private static int width = 110, height = 50;
    protected static String curCaptcha;
    protected static Image captchaImage;

    /**
     * 获取验证码图像
     * @return 验证码图像
     */
    public static Image generateCaptcha() {
        curCaptcha = generateRandomText(4);
        captchaImage = generateCaptcha(curCaptcha);
        return captchaImage;
    }

    /**
     * 验证用户输入的验证码与上次生成的验证码是否相同
     * 注意, 这个方法调用前一定要先调用generateCaptcha方法
     * @param input 用户输入
     * @return 相同返回true, 否则返回false
     */
    public static boolean verify(String input) {
        if(curCaptcha == null) {
            ErrorUtils.setLastError(7, "在验证之前没有生成过验证码");
            throw new RuntimeException("在验证之前没有生成过验证码");
        }
        String inputStr = input.toLowerCase();
        String standardStr = curCaptcha.toLowerCase();

        return inputStr.equals(standardStr);
    }

    /**
     * 获取验证码图像
     * @param captchaText 验证码文字
     * @return 验证码图像
     */
    protected static BufferedImage generateCaptcha(String captchaText) {
        Random random = new Random();
        BufferedImage captchaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = captchaImage.createGraphics();

        // 设置背景色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // 设置字体和颜色
        Font font = new Font("Arial", Font.BOLD, 24);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        // 获取字符的宽度
        FontMetrics fm = g2d.getFontMetrics();

        // 计算垂直摆动的偏移量
        int wobbleAmount = 5, dx = 5, dy = 30;
        AffineTransform transform = new AffineTransform();

        // 将字符绘制到图片中，添加垂直摆动效果
        for (int i = 0; i < captchaText.length(); i++) {
            double distortion = random.nextDouble(0,0.3);
            transform.setToShear(distortion, 0);
            g2d.setTransform(transform);

            String curChar = String.valueOf(captchaText.charAt(i));
            int yOffset = (int) (Math.sin((i * 0.5 + System.currentTimeMillis() / 100.0)) * wobbleAmount);
            int textWidth = fm.stringWidth(curChar)+2;
            g2d.drawString(curChar, dx, dy + yOffset);
            dx += textWidth;

            // 重置仿射变换
            transform.setToIdentity();
            g2d.setTransform(transform);
        }

        // 添加随机颜色的条纹
        addRandomStripes(g2d, width, height);

        // 释放资源
        g2d.dispose();

        return captchaImage;
    }

    /**
     * 在指定画布上添加条纹
     * @param g2d 画布
     * @param width 画布宽
     * @param height 画布高
     */
    private static void addRandomStripes(Graphics2D g2d, int width, int height) {
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            // 随机生成条纹的起始点、终止点和颜色
            int startX = random.nextInt(width);
            int startY = random.nextInt(height);
            int endX = random.nextInt(width);
            int endY = random.nextInt(height);
            Color stripeColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            // 设置画笔颜色
            g2d.setColor(stripeColor);
            // 绘制线条
            g2d.drawLine(startX, startY, endX, endY);
        }
    }

    /**
     * 生成指定长度的验证码
     * @param length 验证码长度
     * @return 验证码
     */
    private static String generateRandomText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captchaText = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            captchaText.append(characters.charAt(random.nextInt(characters.length())));
        }

        return captchaText.toString();
    }
}
