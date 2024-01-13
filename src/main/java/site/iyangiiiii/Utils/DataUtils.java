package site.iyangiiiii.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import site.iyangiiiii.Service.UserService;

import java.util.List;
import java.util.Vector;
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

    /**
     * 将List<Vector<String>>类型转换为Object[][]类型
     * @param listOfVectors
     * @return
     */
    public static Object[][] convertListVectorToObjectArray(List<Vector<String>> listOfVectors) {
        int numRows = listOfVectors.size();
        int numCols = listOfVectors.isEmpty() ? 0 : listOfVectors.get(0).size();

        Object[][] objectArray = new Object[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            Vector<String> vector = listOfVectors.get(i);
            objectArray[i] = vector.toArray();
        }

        return objectArray;
    }
}
