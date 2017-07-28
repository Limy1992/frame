package lmy.com.utilslib.net;

import lmy.com.utilslib.utils.LogUtils;

/**
 * recode ！= 0 的处理
 * Created by lmy on 2017/7/11
 */

public class ApiException extends RuntimeException {
    private static final int USER_NOT_EXIST = 100;
    private static final int WRONG_PASSWORD = 101;
    private static String message;

    ApiException(int resultCode, String message) {
        this(getApiExceptionMessage(resultCode, message));
    }

    private ApiException(String detailMessage) {
        super(detailMessage);
    }

    @Override
    public String getMessage() {
        return message;
    }


    private static String getApiExceptionMessage(int code, String message) {
        switch (code) {
            case USER_NOT_EXIST:
                ApiException.message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                ApiException.message = "密码错误";
                break;
            case 0:
                ApiException.message = "rcodel";
                LogUtils.e("message="+message);
                break;
            default:
                ApiException.message = "未知错误";
        }
        return ApiException.message;
    }
}
