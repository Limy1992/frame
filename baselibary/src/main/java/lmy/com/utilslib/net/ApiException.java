package lmy.com.utilslib.net;


/**
 * recode ！= 0 的处理
 * Created by lmy on 2017/7/11
 */

public class ApiException extends RuntimeException {
    private static String messages;
    private static String codes;

    ApiException(int resultCode, String message) {
        this(getApiExceptionMessage(resultCode, message));
    }

    private ApiException(String detailMessage) {
        super(detailMessage);
    }

    //充当后台返回message
    @Override
    public String getMessage() {
        return messages;
    }

    //充当code
    @Override
    public String getLocalizedMessage() {
        return codes;
    }

    private static String getApiExceptionMessage(int code, String message) {
        codes = String.valueOf(code);
        messages = message;
        return ApiException.messages;
    }
}
