package site.iyangiiiii.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import site.iyangiiiii.Entities.Appraise;
import site.iyangiiiii.Entities.Order;
import site.iyangiiiii.Entities.User;

import java.util.List;

public interface AppraiseRepository extends JpaRepository<Appraise, Integer> {
    Appraise findAppraiseByAid(int aid);
    List<Appraise> findAppraisesByUid(User author);
    List<Appraise> findAppraisesByOid(Order order);
    List<Appraise> findAppraisesByStars(int stars);
    List<Appraise> findAppraisesByStarsBetween(int lhs, int rhs);
}
