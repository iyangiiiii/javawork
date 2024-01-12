package site.iyangiiiii.Entities;

import jakarta.persistence.*;

@Entity
public class OrderGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ogid;

    @ManyToOne
    @JoinColumn(name = "orders", referencedColumnName = "oid")
    Order orders;

    @ManyToOne
    @JoinColumn(name = "goods", referencedColumnName = "gid")
    Goods goods;

    public OrderGoods(Order orders, Goods goods) {
        this.orders = orders;
        this.goods = goods;
    }

    public void setOgid(int ogid) {
        this.ogid = ogid;
    }

    public OrderGoods() {
    }

    public int getOgid() {
        return ogid;
    }
    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        this.orders = order;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
