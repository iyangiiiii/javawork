package site.iyangiiiii.Entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int oid;

    // 商品id
    @ManyToOne
    @JoinColumn(name = "goods", referencedColumnName = "gid")
    Goods gid;

    // 客户id
    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "uid")
    User uid;

    // 状态
    String states;

    // 订单的时间
    Date saleDate;

    public Order(Goods gid, User uid, String states, Date saleDate) {
        this.gid = gid;
        this.uid = uid;
        this.states = states;
        this.saleDate = saleDate;
    }

    public Order() {
    }

    public int getOid() {
        return oid;
    }

    public Goods getGid() {
        return gid;
    }

    public void setGid(Goods gid) {
        this.gid = gid;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
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
}
