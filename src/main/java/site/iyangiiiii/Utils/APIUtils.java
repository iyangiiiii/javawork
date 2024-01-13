package site.iyangiiiii.Utils;

import site.iyangiiiii.API.CaptchaAPI;
import site.iyangiiiii.Bean.ChatInfo;
import site.iyangiiiii.Bean.RankingInfo;
import site.iyangiiiii.Entities.*;
import site.iyangiiiii.Service.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIUtils {
    protected static Logger logger = Logger.getLogger("APIUtils");
    /**
     * 获取验证码图案
     * @return 成功返回 验证码图案, 否则返回null
     */
    public static ImageIcon CaptchaImage() {
        try {
            Image captcha = CaptchaAPI.generateCaptcha();
            return new ImageIcon(captcha);
        }
        catch (Exception e) {
            ErrorUtils.setLastError(5, "生成验证码失败.");
            logger.log(Level.SEVERE, "APIUtils: 生成验证码失败.", e);
            return null;
        }
    }

    /**
     * 验证验证码
     * @param captcha 用户输入的验证码
     * @return 返回验证码是否正确
     */
    public static boolean verifyCaptcha(String captcha) {
        try {
            ErrorUtils.clearError();
            return CaptchaAPI.verify(captcha);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "APIUtils: 验证验证码失败.", e);
            if(!ErrorUtils.isError()) {
                ErrorUtils.setLastError(9, "验证验证码失败.");
            }
            return false;
        }
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
    public static int addGoods(String name, String factory, Long inventory, String remark, String variety, String state, int price) {
        Goods goods = new Goods(factory, name, variety, price, inventory, state, remark);
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
    public static List<ChatInfo> getHistory(int lhs, int rhs) {
        List<ChatInfo> ret = new ArrayList<>();
        List<Chat> res = ChatService.getHistory(lhs, rhs);
        for(Chat chat: res) {
            ChatInfo chatInfo;
            // 正向
            if(lhs == chat.getOrigin().getUid())
                chatInfo = new ChatInfo(chat.getOrigin(), chat.getDest(), 1, chat.getContent());
            else
                chatInfo = new ChatInfo(chat.getDest(), chat.getOrigin(), -1, chat.getContent());
            ret.add(chatInfo);
        }
        return ret;
    }

    /**
     * 客服沟通 获取聊天记录
     * @param lhs 左方
     * @return 如果成功返回 双方的聊天记录(按照时间排序), 否则返回null
     */
    public static List<ChatInfo> getHistory(int lhs) {
        List<ChatInfo> ret = new ArrayList<>();
        List<Chat> res = ChatService.getHistory(lhs);
        for(Chat chat: res) {
            ChatInfo chatInfo;
            // 正向
            if(lhs == chat.getOrigin().getUid())
                chatInfo = new ChatInfo(chat.getOrigin(), chat.getDest(), 1, chat.getContent());
            else
                chatInfo = new ChatInfo(chat.getDest(), chat.getOrigin(), -1, chat.getContent());
            ret.add(chatInfo);
        }
        return ret;
    }

    /**
     * 订单查询 从数据库中展示数据并且通过一些标签查询订单
     * @param by 根据什么查询
     * @param query 查询条件
     * @return 如果成功返回 满足条件的数据, 否则返回null, 并设置错误
     */
    public static List<Order> findOrders(String by, String query) {
        List<Integer> gidList = null;
        List<Integer> oidList = null;
        List<Goods> goodsList = null;
        List<Order> orderList = null;
        List<Order> ret =new ArrayList<Order>();
        switch (by){
            case "按照编号查找":
                Integer q = DataUtils.String2Integer(query);
                if(q == null) {
                    ErrorUtils.setLastError(1, "查询参数有误");
                    return null;
                }
                ret.add(OrderService.findOrderByOid(q));
                return ret;
            case "按照类别查找":
                goodsList = GoodsService.findGoodsByType(query);
                if(goodsList == null){
                    ErrorUtils.setLastError(1, "查询参数有误");
                    return null;
                }
                gidList = new ArrayList<>();
                for(Goods goods: goodsList) gidList.add(goods.getGid());
                ret = OrderService.findAllOrdersContainsGoods(gidList);
                return ret;
            case "按照商品名查找":
                Goods good = GoodsService.findGoodsByName(query);
                if(good == null){
                    ErrorUtils.setLastError(1, "查询参数有误");
                    return null;
                }
                ret = OrderService.findAllOrdersContainsGoods(good.getGid());
                return ret;
            case "按照商品状态查找":
                goodsList = GoodsService.findGoodsByState(query);
                if(goodsList == null){
                    ErrorUtils.setLastError(1, "查询参数有误");
                    return null;
                }
                gidList = new ArrayList<>();
                for(Goods goods: goodsList) gidList.add(goods.getGid());
                ret = OrderService.findAllOrdersContainsGoods(gidList);
                return ret;
            case "按照订单状态查找":
                orderList = OrderService.findOrderByState(query);
                if(orderList == null){
                    ErrorUtils.setLastError(1, "查询参数有误");
                    return null;
                }
                oidList = new ArrayList<>();
                for(Order order: orderList) oidList.add(order.getOid());
                ret = OrderService.findOrderByOid(oidList);
                return ret;
            case "按照厂家查找":
                goodsList = GoodsService.findGoodsByFactory(query);
                if(goodsList == null){
                    ErrorUtils.setLastError(1, "查询参数有误");
                    return null;
                }
                gidList = new ArrayList<>();
                for(Goods goods: goodsList) gidList.add(goods.getGid());
                ret = OrderService.findAllOrdersContainsGoods(gidList);
                return ret;
            default:
                return null;
        }
    }
    /**
     * 获取排行榜的数据
     * 有若干条数据, 每一条数据格式如下:
     * 商品id, 商品名, 销量, 好评率, 价格
     * @return 成功返回数据, 否则返回null
     */
    public static Object[][] getRanking() {
        List<RankingInfo> temp = new ArrayList<>();
        List<Vector<String>> ret = new ArrayList<>();
        List<Goods> goodsList = GoodsService.getAllSoldGoods();
        if (goodsList == null){
            ErrorUtils.setLastError(3,"获取所有商品失败.");
            return null;
        }
        for (Goods item : goodsList) {
            RankingInfo info = new RankingInfo();
            List<Order> orderList = OrderService.findAllOrdersContainsGoods(item.getGid());
            if (orderList == null){
                ErrorUtils.setLastError(3,"获取所有包含商品的订单失败.");
                return null;
            }
            List<Appraise> appraiseList = AppraiseService.getApplauseList(item.getGid());
            if (appraiseList == null){
                ErrorUtils.setLastError(3,"获取所有商品的评论失败.");
                return null;
            }
            info.setGoods(item);
            info.setSalesVolume(orderList.size());
            info.setSalesVolume(appraiseList.size());

            temp.add(info);
        }

        temp.sort(new Comparator<RankingInfo>() {
            @Override
            public int compare(RankingInfo o1, RankingInfo o2) {
                int t = Integer.compare(o1.getSalesVolume(), o2.getSalesVolume());
                if(t != 0) return t;
                return Double.compare(o1.getApplauseRate(), o2.getApplauseRate());
            }
        });

        return DataUtils.convertListVectorToObjectArray(ret);
    }
    /**
     * 订单展示 展示所有订单 内容为 编号 商品名 时间 状态
     * @return 获取到的内容
     */
    public static String[][] getAllOrderInfo() {
        List<Order> orderList = OrderService.getAllOrders();
        if(orderList == null) {
            return new String[0][0];
        }
        int p = 0;
        String[][] ret = new String[orderList.size()][4];
        for(Order order: orderList) {
            ret[p++] = order.toArray();
        }
        return ret;
    }

    /**
     * 将Order转换为Array
     * @param order 需要转换的订单
     * @return 转换后的Array
     */
    public static String[][] getOrderInfo(Order order) {
        if(order == null) {
            return new String[0][0];
        }
        String[][] ret = new String[1][4];
        ret[0] = order.toArray();
        return ret;
    }

    /**
     * 将Order转换为Array
     * @param orders 需要转换的订单列表
     * @return 转换后的Array
     */
    public static String[][] getOrderInfo(List<Order> orders) {
        if(orders == null || orders.isEmpty()) {
            return new String[0][0];
        }
        String[][] ret = new String[orders.size()][4];
        for(int i = 0; i<orders.size();i++) {
            ret[i] = orders.get(i).toArray();
        }
        return ret;
    }
    /**
     * 商品管理 获取所有商品信息
     * @return 信息列表
     */
    public static Object[][] getAllGoodsInfo() {
        List<Goods> goodsList = GoodsService.getAllGoods();
        int len;
        if(goodsList == null) len = 0;
        else len = goodsList.size();
        Object[][] ret = new Object[len][6];
        for(int i = 0; i < len; i++) {
            ret[i] = goodsList.get(i).toArray();
        }
        return ret;
    }
    /**
     * 用户购买过的商品
     * @return 商品列表 最大只有2
     */
    public static String[] SoldGoods(int uid) {
        List<Order> res = OrderService.findOrdersByUid(uid);
        if(res == null) {
            ErrorUtils.setLastError(1, "查询参数有误");
            return null;
        }
        List<Goods> result = GoodsService.getAllGoodsInOrders(res);
        int len;
        if(result == null) len = 0;
        else len = result.size();
        String[] goodsList = new String[len];
        for(int i = 0; i<len; i++) goodsList[i] = result.get(i).getName();
        return goodsList;
    }

    /**
     * 查询所有与某个用户聊过天的用户
     * @param uid 用户id
     * @return 成功返回 符合条件的用户, 否则返回null
     */
    public static List<User> showChatUser(int uid) {
        Set<User> st = new TreeSet<>();
        List<ChatInfo> chatInfos = getHistory(uid);
        for(ChatInfo chatInfo: chatInfos) {
            if(chatInfo.getDirection()==1) st.add(chatInfo.getRhs());
            else st.add(chatInfo.getLhs());
        }
        return new ArrayList<>(st);
    }

    /**
     * 添加一个订单
     * @param state 订单状态
     * @param goodsList 订单包含的商品
     * @return 成功返回 0, 否则返回-1
     */
    public static int addOrder(String state, List<Goods> goodsList) {
        Order order = new Order();
        order.setStates(state);
        order.setUser(Global.curUser);
        order.setSaleDate(new Date(new java.util.Date().getTime()));
        int oid = OrderService.addOrder(order);
        if(oid == -1){
            ErrorUtils.setLastError(11, "新建订单错误");
            return -1;
        }
        if(OrderService.addGoodsToOrder(oid, goodsList) == -1) return -1;
        return 0;
    }

    /**
     * 根据商品名获取商品价格
     * @return 商品价格
     */
    public static double getprice()
    {
        return 1.0;
    }
}

