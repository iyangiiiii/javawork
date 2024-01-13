package site.iyangiiiii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.GoodsRepository;
import site.iyangiiiii.DAO.OrderGoodsRepository;
import site.iyangiiiii.DAO.OrderRepository;
import site.iyangiiiii.DAO.UserRepository;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Entities.OrderGoods;
import site.iyangiiiii.Entities.User;
import site.iyangiiiii.Utils.Global;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class OrderService {
    public static Logger logger = Logger.getLogger("OrderService");
    protected UserRepository userRepository;
    protected OrderRepository orderRepository;
    protected GoodsRepository goodsRepository;
    protected OrderGoodsRepository orderGoodsRepository;
    protected static OrderService orderService;

    @Autowired
    protected OrderService(OrderRepository repository, GoodsRepository repository1, OrderGoodsRepository repository2, UserRepository userRepository) {
        this.orderRepository = repository;
        this.goodsRepository = repository1;
        this.orderGoodsRepository = repository2;
        this.userRepository = userRepository;

        orderService = this;
    }

    /**
     * 根据订单id查询订单
     * @param oid 订单id
     * @return 成功返回订单信息, 否则返回null
     */
    public static Order findOrderByOid(int oid) {
        try {
            Order order = orderService.orderRepository.findOrderByOid(oid);
            return order;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 根据订单id查询订单
     * @param oid 订单id列表
     * @return 成功返回订单信息, 否则返回null
     */
    public static List<Order> findOrderByOid(List<Integer> oid) {
        try {
            List<Order> order = orderService.orderRepository.findOrdersByOidIn(oid);
            return order;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 查询某件商品的所有订单
     * @param gid 商品id
     * @return 成功返回 该商品的所有订单, 否则返回null
     */
    public static List<Order> findOrderByGid(int gid) {
        try {
            Goods goods = orderService.goodsRepository.findGoodsByGid(gid);
            List<OrderGoods> orderGoodsList = orderService.orderGoodsRepository.findOrderGoodsByGoods(goods);
            List<Order> ret = new ArrayList<>();
            for(OrderGoods orderGoods: orderGoodsList) {
                ret.add(orderGoods.getOrders());
            }
            return ret;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 根据状态查询订单
     * @param state 状态字符串
     * @return 成功返回 符合要求的所有订单, 否则返回null
     */
    public static List<Order> findOrderByState(String state) {
        try {
            return orderService.orderRepository.findOrdersByStates(state);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 查询在某个日期之前的所有订单
     * @param date 截止日期
     * @return 成功返回 符合要求的所有订单, 否则返回null
     */
    public static List<Order> findOrderByDateBefore(Date date) {
        try {
            return orderService.orderRepository.findOrdersBySaleDateBefore(date);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 查询在某个商品之后的所有订单
     * @param date 截止日期
     * @return 成功返回 符合要求的所有订单, 否则返回null
     */
    public static List<Order> findOrderByDateAfter(Date date) {
        try {
            return orderService.orderRepository.findOrdersBySaleDateAfter(date);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 查询两个日期之间的所有订单
     * @param lhs 日期下界
     * @param rhs 日期上界
     * @return 成功返回 符合要求的所有订单, 否则返回null
     */
    public static List<Order> findOrderByDateBetween(Date lhs, Date rhs) {
        try {
            return orderService.orderRepository.findOrdersBySaleDateBetween(lhs, rhs);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 查询所有含有某商品的所有订单
     * @param gid 商品id
     * @return 成功返回 符合要求的所有订单, 否则返回null
     */
    public static List<Order> findAllOrdersContainsGoods(int gid) {
        try {
            Goods goods = orderService.goodsRepository.findGoodsByGid(gid);
            List<OrderGoods> res = orderService.orderGoodsRepository.findOrderGoodsByGoods(goods);
            List<Order> ret = new ArrayList<>();
            for(OrderGoods orderGoods : res) ret.add(orderGoods.getOrders());
            return ret;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 查询所有含有某些商品的所有订单
     * @param gidList 商品id列表
     * @return 成功返回 符合要求的所有订单, 否则返回null
     */
    public static List<Order> findAllOrdersContainsGoods(List<Integer> gidList) {
        try {
            List<Goods> goodsList = new ArrayList<>();
            for(int i : gidList) {
                Goods item = new Goods();
                item.setGid(i);
                goodsList.add(item);
            }
            List<OrderGoods> res = orderService.orderGoodsRepository.findOrderGoodsByGoodsIn(goodsList);
            List<Order> ret = new ArrayList<>();
            for(OrderGoods orderGoods : res) ret.add(orderGoods.getOrders());
            return ret;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 查询所有存在的订单
     * @return 成功返回 符合要求的所有订单, 否则返回null
     */
    public static List<Order> getAllOrders() {
        try {
            return orderService.orderGoodsRepository.findAllDistinctOrders();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 通过用户id查找订单
     * @param uid 用户id
     * @return 成功返回 用户所有订单, 否则返回null
     */
    public static List<Order> findOrdersByUid(int uid) {
        try {
            User user = new User(); user.setUid(uid);
            return orderService.orderRepository.findOrdersByUser(user);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return null;
        }
    }

    /**
     * 添加一个订单
     * @param user 订单的发起者
     * @param state 订单的状态
     * @param date 订单创建的时间
     * @return 成功返回 订单编号, 否则返回-1
     */
    public static int addOrder(User user, String state, java.util.Date date) {
        try {
            Order order = new Order();
            order.setSaleDate(new java.sql.Date(new java.util.Date().getTime()));
            order.setUser(Global.curUser);
            order.setStates("未发货");
            order = orderService.orderRepository.save(order);
            return order.getOid();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "OrderService: ", e);
            return -1;
        }
    }
}
