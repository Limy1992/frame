package lmy.com.utilslib.net;

import android.content.Context;
import android.text.TextUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lmy.com.utilslib.utils.CommonManger;
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
        //只有网络异常的时候调用  recode不调用此方法.在本类对recode的实现,提供公共方法,是否重写
        if (!e.getLocalizedMessage().equals(ApiException.codes)) {
            _onError(e.toString());
            dismissProgressDialog();
            return;
        }

        //管理recode
        administrationCode(e);

    }

    private void administrationCode(Throwable e) {
        switch (e.getLocalizedMessage()) {
            case "190":
                onRecode();
                ToastUtils.showShortToast(e.getMessage());
                break;
        }

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
