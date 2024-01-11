package site.iyangiiiii.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Entities.OrderGoods;

import java.util.List;

public interface OrderGoodsRepository extends JpaRepository<OrderGoods, Integer> {
    OrderGoods findOrderGoodsByOgid(int ogid);
    List<OrderGoods> findOrderGoodsByOrders(Order oid);
    List<OrderGoods> findOrderGoodsByGoods(Goods gid);
}
