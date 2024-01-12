package site.iyangiiiii.Utils;

import site.iyangiiiii.API.CaptchaAPI;

import javax.swing.*;
import java.awt.*;

public class APIUtils {
    /**
     * 获取验证码图案
     * @return 验证码图案
     */
    public static ImageIcon CaptchaImage() {
        Image captcha = CaptchaAPI.generateCaptcha();
        return new ImageIcon(captcha);
    }

    /**
     * 验证验证码
     * @param captcha 用户输入的验证码
     * @return 返回验证码是否正确
     */
    public static boolean verifyCaptcha(String captcha) {
        return CaptchaAPI.verify(captcha);
    }

    /**
     * 商品入库 将商品所有信息添加到数据库
     * @return null
     */
    public static ImageIcon test() {
        ImageIcon img = new ImageIcon();
        return img;
    }
    /**
     * 删除商品 删除商品所有信息
     * @return null
     */
    public static int test1() {
        return 1;
    }
    /**
     * 商品出库 将商品所有信息添加到数据库
     * @return null
     */
    public static int test2() {
        return 1;
    }



}
