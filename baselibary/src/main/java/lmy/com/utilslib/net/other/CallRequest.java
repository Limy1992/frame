package lmy.com.utilslib.net.other;

import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import lmy.com.utilslib.net.NetState;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.ToastUtils;
import lmy.com.utilslib.utils.Utils;

/**
 * 结果回调
 * Created by on 2017/12/29.
 *
 * @author lmy
 */

public abstract class CallRequest<T> {

    public abstract void onSuccess(T o);

    public void onError(Throwable e){
        if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            ToastUtils.showLongToast("连接超时,请重试");
        } else if (e instanceof SocketException || e instanceof HttpException) {
            if (!NetState.IfNet(Utils.getContext())) {
                ToastUtils.showLongToast("后台连接异常");
            }
        } else if (e instanceof JsonSyntaxException){
            ToastUtils.showLongToast("解析异常");
            LogUtils.e("JsonSyntaxException:"+e.toString());
        }else if (e instanceof UnknownHostException){
            ToastUtils.showLongToast("后台异常");
        }else {
            String code = e.getLocalizedMessage();
            if (code != null && code.equals("2")) {
                String message = e.getMessage();
                ToastUtils.showShortToast(message == null ? "未知异常" : message);
            }else {
                ToastUtils.showShortToast("未知异常");
            }
        }
    }
}
