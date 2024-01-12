package site.iyangiiiii.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Entities.OrderGoods;

import java.util.List;

public interface OrderGoodsRepository extends JpaRepository<OrderGoods, Integer> {
    OrderGoods findOrderGoodsByOgid(int ogid);
    List<OrderGoods> findOrderGoodsByOrders(Order oid);
    List<OrderGoods> findOrderGoodsByGoods(Goods gid);
    List<OrderGoods> findOrderGoodsByGoodsIn(List<Goods> goodsList);
    List<OrderGoods> findOrderGoodsByOrdersIn(List<Order> orderList);
    @Query("SELECT DISTINCT e.orders FROM OrderGoods e")
    List<Order> findAllDistinctOrders();
    @Query("SELECT DISTINCT e.goods FROM OrderGoods e")
    List<Goods> findAllDistinctGoods();
}
