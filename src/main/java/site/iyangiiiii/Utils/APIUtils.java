package site.iyangiiiii.Utils;

import site.iyangiiiii.API.CaptchaAPI;
import site.iyangiiiii.Entities.Chat;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.User;
import site.iyangiiiii.Service.ChatService;
import site.iyangiiiii.Service.GoodsService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
     * 商品入库
     * @param name 商品名
     * @param factory 生产商品的工厂名
     * @param inventory 库存数量
     * @param remark 备注
     * @param variety 类别(字符串)
     * @return 如果成功 返回商品id, 否则返回-1
     */
    public static int addGoods(String name, String factory, Long inventory, String remark, String variety) {
        Goods goods = new Goods(factory, name, variety,inventory, remark);
        return GoodsService.addGoods(goods);
    }

    /**
     * 删除商品 删除商品所有信息
     * @param gid 需要删除的货物id
     * @return 如果成功 返回0, 否则返回-1
     */
    public static int deleteGoods(int gid) {
        Goods goods = new Goods();
        goods.setGid(gid);
        return GoodsService.deleteGoods(goods);
    }

    /**
     * 商品出库 数据库库存-1
     * @param gid 商品id
     * @return 如果成功 返回0, 否则返回-1
     */
    public static int saleGoods(int gid) {
        return GoodsService.subGoods(gid, 1);
    }

    /**
     * 商品出库 数据库库存-1
     * @param info 商品列表, first代表商品id, second代表商品出库数量
     * @return 如果成功 返回0, 否则返回-1
     */
    public static int saleGoods(List<Pair<Integer, Integer>> info) {
        return GoodsService.subGoods(info);
    }

    /**
     * 超级管理员 是否是超级管理员
     * @return 如果已登录返回 是否是超级管理员, 否则返回null
     */
    public static Boolean isAdmin() {
        return Global.curUser.isAdmin();
    }
    /**
     * 客服沟通 获取聊天记录
     * @param lhs 左方
     * @param rhs 右方
     * @return 如果成功返回 双方的聊天记录(按照时间排序), 否则返回null
     */
    public static List<Chat> getHistory(int lhs, int rhs) {
        return ChatService.getHistory(lhs, rhs);
    }
    /**
     * 订单查询 从数据库中展示数据并且通过一些标签查询订单
     * @return 查询到的内容
     */
    public static int test5() {
        return 1;
    }
    /**
     * 排行榜 从数据库中获取用于展示排行榜的数据
     * @return 获取到的内容
     */
    public static int test6() {
        return 1;
    }
    /**
     * 排行榜 从数据库中获取用于展示排行榜的数据
     * @return 获取到的内容
     */
    public static int test7() {
        return 1;
    }

}
