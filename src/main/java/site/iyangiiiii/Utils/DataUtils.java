package site.iyangiiiii.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import site.iyangiiiii.Service.UserService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataUtils {
    public static Logger logger = Logger.getLogger("DataUtils");

    /**
     * 将String转化为Integer
     * @param integerStr 需要转化的字符串
     * @return 成功 返回字符串的数字, 否则返回null
     */
    public static Integer String2Integer(String integerStr) {
        try {
            Integer ret = Integer.parseInt(integerStr);
            return ret;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Fail to convert %s to integer.", e);
            return null;
        }
    }
}
