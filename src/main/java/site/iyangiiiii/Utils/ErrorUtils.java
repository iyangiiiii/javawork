package site.iyangiiiii.Utils;

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

    public static void setLastError(int errCode, String errDetail) {
        lastError = errDetail;
        lastErrorCode = errCode;
    }

    public static Error getLastError() {
        return new Error(lastErrorCode, lastError);
    }
}
