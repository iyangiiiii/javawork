package site.iyangiiiii.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.User;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    Goods findGoodsByGid(int gid);
    List<Goods> findGoodsByGidIn(List<Integer> gidList);
    List<Goods> findGoodsByName(String name);
    List<Goods> findGoodsByGidNotNull();
    List<Goods> findGoodsByFactory(String factory);
    List<Goods> findGoodsByVariety(String variety);
    List<Goods> findGoodsByState(String state);
}
