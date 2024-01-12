package site.iyangiiiii.Utils;

import java.security.PublicKey;
import java.util.logging.Logger;

public class ErrorUtils {
    protected static Logger logger = Logger.getLogger("ErrorUtils");
    protected static String lastError = "OK";
    protected static Integer lastErrorCode = 0;

    public static class Error {
        public Integer errCode;
        public String errDetail;

        protected Error(Integer errCode, String errDetail) {
            this.errCode = errCode;
            this.errDetail = errDetail;
        }

        @Override
        public String toString() {
            return "code:" + errCode + ",'" + errDetail + "'}";
        }
    }

    /**
     * 判断是否有错
     * @return 是否有错
     */
    public static boolean isError() {
        return lastErrorCode != 0;
    }

    /**
     * 清空之前的错误
     */
    public static void clearError() {
        lastError = "OK";
        lastErrorCode = 0;
    }

    /**
     * 设置错误
     * @param errCode 错误码
     * @param errDetail 错误信息
     */
    public static void setLastError(int errCode, String errDetail) {
        lastError = errDetail;
        lastErrorCode = errCode;
    }

    /**
     * 获取上一个错误
     * @return 错误信息
     */
    public static Error getLastError() {
        return new Error(lastErrorCode, lastError);
    }
}
