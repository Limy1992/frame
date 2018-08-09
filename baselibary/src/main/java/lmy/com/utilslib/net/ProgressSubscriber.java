package lmy.com.utilslib.net;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.lang.ref.WeakReference;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.ToastUtils;
import lmy.com.utilslib.base.ui.view.SimpleLoadDialog;

/**
 * 显示dialog和数据回调
 * Created by lmy on 2017/7/11
 */

public abstract class ProgressSubscriber<T> implements ProgressCancelListener, Observer<T> {
    /**
     * 弹窗对象
     */
    private SimpleLoadDialog dialogHandler;
    /***/
    private Disposable mDisposable;
    private WeakReference<Context> weakReference;

//    protected ProgressSubscriber(Context context) {
//
//    }
    protected ProgressSubscriber() {
    }

    public void context(Context context){
        weakReference = new WeakReference<>(context);
        dialogHandler = new SimpleLoadDialog(weakReference.get(), this, false);
    }

    //显示Dialog
    void showProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.showDialog();
        }
    }

    @Override
    public void onSubscribe(Disposable s) {
        this.mDisposable = s;
        LogUtils.e("Disposable");
    }

    @Override
    public void onNext(T t) {
        dismissProgressDialog();
        try {
            onLoadSuccess(t);
        } catch (ClassCastException e) {
            LogUtils.e("ClassCastException=" + e.toString());
        } catch (NullPointerException e) {
            LogUtils.e("NullPointerException=" + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("Exception=" + e.toString());
        }
    }

    //关闭Dialog
    void dismissProgressDialog() {
        try {
            if (dialogHandler != null) {
                dialogHandler.dismiss();
                dialogHandler = null;
                if (weakReference != null) {
                    weakReference.clear();
                    weakReference = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        try {
            //只有网络异常的时候调用  recode不调用此方法.在本类对recode的实现,提供公共方法,是否重写
            String localizedMessage = e.getLocalizedMessage();
            if (localizedMessage != null) {
                //管理recode
                administrationCode(localizedMessage, e);
            } else {
                exceptionInit(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                onLoadError(e.toString());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            dismissProgressDialog();
            ToastUtils.showShortToast("未知异常");
        }
    }

    /**
     * 异常错误提示
     */
    private void exceptionInit(Throwable e) {
        if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            ToastUtils.showLongToast("连接超时,请重试...");
        } else if (e instanceof SocketException || e instanceof HttpException || e instanceof UnknownHostException) {
            netErr();
        } else if (e instanceof JsonSyntaxException) {
            ToastUtils.showLongToast("解析异常");
            LogUtils.e("JsonSyntaxException:" + e.toString());
        } else {
            ToastUtils.showShortToast("未知异常");
        }
        onLoadError(e.toString());
        dismissProgressDialog();
    }

    /**
     * 手机无网络状态提示
     */
    private void netErr() {
        if (weakReference != null) {
            if (!NetState.IfNet(weakReference.get())) {
                ToastUtils.showLongToast("连接后台异常");
            }
        } else {
            ToastUtils.showLongToast("连接后台异常");
        }
    }

    private void administrationCode(String code, Throwable e) {
        switch (code) {
            case "2":
                String messageImport = e.getMessage();
                if (messageImport != null && messageImport.equals("系统繁忙!")) {
                    ToastUtils.showShortToast("系统繁忙");
                    onLoadError(e.toString());
                } else {
                    onRecode();
                    ToastUtils.showShortToast(messageImport);
                }
                break;
            case "3":
                break;
            case "4":
                break;
            default:
                exceptionInit(e);
                break;
        }
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        LogUtils.e("onComplete");
    }

    @Override
    public void onCancelProgress() {
        if (mDisposable != null) {
            dismissProgressDialog();
            mDisposable.dispose();
        }
    }

    protected abstract void onLoadSuccess(T t);

    protected abstract void onLoadError(String message);

    //选择性是否重写
    protected void onRecode() {
        //其他操作
    }
}
