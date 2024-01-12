package site.iyangiiiii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.GoodsRepository;
import site.iyangiiiii.DAO.OrderGoodsRepository;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Entities.OrderGoods;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GoodsService {
    public static Logger logger = Logger.getLogger("GoodsService");
    protected GoodsRepository goodsRepository;
    protected OrderGoodsRepository orderGoodsRepository;
    protected static GoodsService goodsService;

    @Autowired
    protected GoodsService(GoodsRepository repository, OrderGoodsRepository repository1) {
        this.goodsRepository = repository;
        this.orderGoodsRepository = repository1;
        goodsService = this;
    }

    /**
     * 查询所有商品
     * @return 成功返回 所有商品, 否则返回 null
     */
    public static List<Goods> getAllGoods() {
        try {
            return goodsService.goodsRepository.findGoodsByGidNotNull();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return null;
        }
    }

    /**
     * 根据gid查询商品
     * @param gid 商品id
     * @return 成功返回 符合条件的商品, 否则返回 null
     */
    public static Goods findGoodsByGid(int gid) {
        try {
            return goodsService.goodsRepository.findGoodsByGid(gid);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return null;
        }
    }

    /**
     * 根据商品名查询商品(注意, 同一个商品名称可能会有多个商品名, 只有gid才是所有商品唯一的)
     * @param name 商品名
     * @return 成功返回 符合条件的商品, 否则返回 null
     */
    public static List<Goods> findGoodsByName(String name) {
        try {
            return goodsService.goodsRepository.findGoodsByName(name);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return null;
        }
    }

    /**
     * 根据生产厂商查询商品
     * @param factory 商品的生产厂商
     * @return 成功返回 符合条件的商品, 否则返回 null
     */
    public static List<Goods> findGoodsByFactory(String factory) {
        try {
            return goodsService.goodsRepository.findGoodsByFactory(factory);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return null;
        }
    }

    /**
     * 根据商品类型查询商品
     * @param type 商品类型
     * @return 成功返回 符合条件的商品, 否则返回 null
     */
    public static List<Goods> findGoodsByType(String type) {
        try {
            return goodsService.goodsRepository.findGoodsByVariety(type);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return null;
        }
    }

    /**
     * 添加一个商品
     * @param goods 商品信息
     * @return 成功返回商品id, 否则返回-1
     */
    public static int addGoods(Goods goods) {
        try {
            goods = goodsService.goodsRepository.save(goods);
            return goods.getGid();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return -1;
        }
    }

    /**
     * 查询订单中的商品
     * @param oid 订单id
     * @return 成功返回 符合要求的所有商品, 否则返回null
     */
    public static List<Goods> findAllGoodsInOrder(int oid) {
        try {
            Order order = new Order();
            order.setOid(oid);
            List<OrderGoods> res = goodsService.orderGoodsRepository.findOrderGoodsByOrders(order);
            List<Goods> ret = new ArrayList<>();
            for(OrderGoods orderGoods : res) ret.add(orderGoods.getGoods());
            return ret;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return null;
        }
    }

    /**
     * 查询订单列表中包含的商品
     * @param oidList 订单id列表
     * @return 成功返回 符合要求的所有商品, 否则返回null
     */
    public static List<Goods> findAllGoodsInOrder(List<Integer> oidList) {
        try {
            List<Order> orderList = new ArrayList<>();
            for(int i: oidList) {
                Order item = new Order();
                item.setOid(i);
                orderList.add(item);
            }
            List<OrderGoods> res = goodsService.orderGoodsRepository.findOrderGoodsByOrdersIn(orderList);
            List<Goods> ret = new ArrayList<>();
            for(OrderGoods orderGoods : res) ret.add(orderGoods.getGoods());
            return ret;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "GoodsService: ", e);
            return null;
        }
    }
}
