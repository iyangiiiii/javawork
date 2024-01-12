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
    @JoinColumn(name = "user", referencedColumnName = "uid")
    User user;

    // 评价的商品
    @ManyToOne
    @JoinColumn(name = "goods", referencedColumnName = "gid")
    Goods goods;

    // 评价的内容
    @Column(columnDefinition = "TEXT")
    String content;

    // 评价的星级
    int stars;

    public Appraise(User user, Goods goods, String content, int stars) {
        this.user = user;
        this.goods = goods;
        this.content = content;
        this.stars = stars;
    }

    public Appraise() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
