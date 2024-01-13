package site.iyangiiiii.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods implements Comparable<Goods> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int gid;
    // 工厂名
    String factory;
    // 商品名
    String name;
    // 商品类型
    String variety;
    // 价格
    int price;
    // 库存
    Long inventory;
    // 状态
    String state;
    // 备注
    String remark;

    public Goods(String factory, String name, String variety, int price, Long inventory, String state, String remark) {
        this.factory = factory;
        this.name = name;
        this.variety = variety;
        this.price = price;
        this.inventory = inventory;
        this.state = state;
        this.remark = remark;
    }

    public Goods() {
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object[] toArray() {
        List<String> ret = new ArrayList<>();
        ret.add(getName());
        ret.add(getFactory());
        ret.add(String.valueOf(getInventory()));
        ret.add(getVariety());
        ret.add(getState());
        ret.add(String.valueOf(getPrice()));
        return ret.toArray();
    }

    @Override
    public int compareTo(Goods o) {
        return Integer.compare(getGid(), o.getGid());
    }
}
