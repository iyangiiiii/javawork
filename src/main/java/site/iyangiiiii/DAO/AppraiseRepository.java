package site.iyangiiiii.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import site.iyangiiiii.Entities.Appraise;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Entities.User;

import java.util.List;

public interface AppraiseRepository extends JpaRepository<Appraise, Integer> {
    Appraise findAppraiseByAid(int aid);
    List<Appraise> findAppraisesByUser(User author);
    List<Appraise> findAppraisesByGoods(Goods goods);
    List<Appraise> findAppraisesByStars(int stars);
    List<Appraise> findAppraisesByStarsBetween(int lhs, int rhs);
}
