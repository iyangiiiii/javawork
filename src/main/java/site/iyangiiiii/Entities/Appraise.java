package site.iyangiiiii.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "appraises")
public class Appraise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int aid;
    // 评价的用户
    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    User uid;

    // 评价的订单
    @ManyToOne
    @JoinColumn(name = "oid", referencedColumnName = "oid")
    Order oid;

    // 评价的内容
    @Column(columnDefinition = "TEXT")
    String content;

    // 评价的星级
    int stars;

    public Appraise(User uid, Order oid, String content, int stars) {
        this.uid = uid;
        this.oid = oid;
        this.content = content;
        this.stars = stars;
    }

    public Appraise() {
    }

    public int getAid() {
        return aid;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    public Order getOid() {
        return oid;
    }

    public void setOid(Order oid) {
        this.oid = oid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
