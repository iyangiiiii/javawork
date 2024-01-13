package site.iyangiiiii.Bean;

import site.iyangiiiii.Entities.Goods;

import java.util.Vector;

public class RankingInfo {
    // 商品
    Goods goods;
    // 销量
    Integer salesVolume;
    // 好评率
    Double applauseRate;

    public RankingInfo() {
    }

    public RankingInfo(Goods goods, Integer salesVolume, Double applauseRate) {
        this.goods = goods;
        this.salesVolume = salesVolume;
        this.applauseRate = applauseRate;
    }

    public Vector<String> toVector() {
        Vector<String> ret = new Vector<>();
//        ret.add(String.valueOf(goods.getGid()));
        ret.add(goods.getName());
        ret.add(salesVolume.toString());
        ret.add(applauseRate.toString());
        ret.add(String.valueOf(goods.getPrice()));
        return ret;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Double getApplauseRate() {
        return applauseRate;
    }

    public void setApplauseRate(Double applauseRate) {
        this.applauseRate = applauseRate;
    }
}
