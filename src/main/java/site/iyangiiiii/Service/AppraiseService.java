package site.iyangiiiii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.AppraiseRepository;
import site.iyangiiiii.DAO.OrderRepository;
import site.iyangiiiii.Entities.Appraise;
import site.iyangiiiii.Entities.Goods;
import site.iyangiiiii.Entities.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AppraiseService {
    public static Logger logger = Logger.getLogger("AppraiseService");
    protected AppraiseRepository appraiseRepository;
    protected static AppraiseService appraiseService;

    @Autowired
    protected AppraiseService(AppraiseRepository repository) {
        this.appraiseRepository = repository;

        appraiseService = this;
    }

    /**
     * 获取商品的好评的列表
     * @param gid 商品id
     * @return 成功 返回好评列表, 否则返回null
     */
    public static List<Appraise> getApplauseList(int gid) {
        try {
            Goods goods = new Goods();
            goods.setGid(gid);
            return appraiseService.appraiseRepository.findAppraisesByStarsAfterAndGoods(2, goods);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "AppraiseService: ", e);
            return null;
        }
    }

    public static List<Appraise> findAllApplause() {
        try {
            return appraiseService.appraiseRepository.findAppraisesByAidNotNull();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "AppraiseService: ", e);
            return null;
        }
    }

    public static List<Appraise> findApplauseListByUser(int uid) {
        try {
            User user = new User();
            user.setUid(uid);
            return appraiseService.appraiseRepository.findAppraisesByUser(user);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "AppraiseService: ", e);
            return null;
        }
    }
}
