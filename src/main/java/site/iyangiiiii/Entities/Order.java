package site.iyangiiiii.Entities;

import jakarta.persistence.*;
import site.iyangiiiii.Service.GoodsService;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int oid;

    // 客户id
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "uid")
    User user;

    // 状态
    String states;

    // 订单的时间
    Date saleDate;

    public Order(User user, String states, Date saleDate) {
        this.user = user;
        this.states = states;
        this.saleDate = saleDate;
    }

    public Order() {
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String[] toArray() {
        StringBuilder goodsStr = new StringBuilder();
        List<Goods> goodsList = GoodsService.findAllGoodsInOrder(getOid());

        int len;
        if((goodsList != null) && (!goodsList.isEmpty())) {
            len = goodsList.size();
            goodsStr.append(goodsList.get(0).getName());
        }
        else len = 0;
        for(int i = 1; i < len; i++) {
            goodsStr.append(",").append(goodsList.get(i).getName());
        }

        return new String[]{
                String.valueOf(getOid()),
                goodsStr.toString(),
                getSaleDate().toString(),
                getStates()
        };
    }
}
