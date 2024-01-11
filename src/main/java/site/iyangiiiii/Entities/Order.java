package site.iyangiiiii.Entities;

import jakarta.persistence.*;

import java.sql.Date;

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
}
