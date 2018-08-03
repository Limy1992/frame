package lmy.com.utilslib.net.api;


/**
 * recode ！= 1 的处理
 * Created by lmy on 2017/7/11
 */

public class ApiException extends RuntimeException {
    private String messages;
    private String codes;

    public ApiException(int resultCode, String message) {
        this.codes = String.valueOf(resultCode);
        this.messages = message;
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
}
