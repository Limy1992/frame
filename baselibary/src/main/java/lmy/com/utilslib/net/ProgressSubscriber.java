package lmy.com.utilslib.net;

import android.content.Context;
import android.util.Log;


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

    private SimpleLoadDialog dialogHandler;
    private Disposable mDisposable;

    protected ProgressSubscriber(Context context) {
        dialogHandler = new SimpleLoadDialog(context, this, true);
    }

    //显示Dialog
    void showProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.show();
        }
    }

    @Override
    public void onSubscribe(Disposable s) {
        this.mDisposable = s;
        LogUtils.e("Disposable");
    }

    @Override
    public void onNext(T t) {
        LogUtils.e("onNext");
        _onNext(t);
    }

    //关闭Dialog
    private void dismissProgressDialog() {
        if (dialogHandler != null) {
            dialogHandler.dismiss();
            dialogHandler = null;
        }
    }

    @Override
    public void onError(Throwable e) {
        switch (e.getLocalizedMessage()) {
            case "190":
                onRecode();
                ToastUtils.showShortToast(e.getMessage());
                break;
        }

        //网络异常调用
        _onError(e.toString());
        dismissProgressDialog();

    }

    @Override
    public void onComplete() {
        LogUtils.e("onComplete");
        dismissProgressDialog();
    }


    @Override
    public void onCancelProgress() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    //选择性是否重写
    protected void onRecode() {
        //其他操作
    }
}
