package site.iyangiiiii.Entities;

import jakarta.persistence.*;

@Entity
public class OrderGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ogid;

    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "uid")
    Order order;

    @ManyToOne
    @JoinColumn(name = "goods", referencedColumnName = "gid")
    Goods goods;

    public OrderGoods(Order order, Goods goods) {
        this.order = order;
        this.goods = goods;
    }

    public OrderGoods() {
    }

    public int getOgid() {
        return ogid;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
