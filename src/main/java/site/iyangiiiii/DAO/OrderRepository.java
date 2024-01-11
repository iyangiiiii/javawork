package site.iyangiiiii.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Entities.User;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderByOid(int oid);
    List<Order> findOrdersByGidNotNull();
    List<Order> findOrdersByGid(Goods goods);
    List<Order> findOrdersByUid(User customer);
    List<Order> findOrdersByStates(String state);
    List<Order> findOrdersBySaleDateBefore(Date beforeDate);
    List<Order> findOrdersBySaleDateAfter(Date beforeDate);
    List<Order> findOrdersBySaleDateBetween(Date lDate, Date rDate);
}
