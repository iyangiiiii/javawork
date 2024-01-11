package site.iyangiiiii.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int gid;
    // 工厂名
    String factory;
    // 商品名
    String name;
    // 商品类型
    String variety;
    // 库存
    Long inventory;
    // 备注
    String remark;

    public Goods(String factory, String name, String variety, Long inventory, String remark) {
        this.factory = factory;
        this.name = name;
        this.variety = variety;
        this.inventory = inventory;
        this.remark = remark;
    }

    public Goods() {
    }

    public int getGid() {
        return gid;
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
}
