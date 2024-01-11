package site.iyangiiiii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.OrderRepository;

import java.util.logging.Logger;

@Service
public class OrderService {
    public static Logger logger = Logger.getLogger("OrderService");
    protected OrderRepository orderRepository;
    protected static OrderService orderService;

    @Autowired
    protected OrderService(OrderRepository repository) {
        this.orderRepository = repository;

        orderService = this;
    }
}
