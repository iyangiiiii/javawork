package site.iyangiiiii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.AppraiseRepository;
import site.iyangiiiii.DAO.OrderRepository;

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
}
