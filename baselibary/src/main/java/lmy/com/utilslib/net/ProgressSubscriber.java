package lmy.com.utilslib.net;

import android.content.Context;
import android.util.Log;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
        dialogHandler = new SimpleLoadDialog(context,this,true);
    }

    /**
     * 显示Dialog
     */
     void showProgressDialog(){
        if (dialogHandler != null) {
            dialogHandler.show();
        }
    }

    @Override
    public void onSubscribe(Disposable s) {
        this.mDisposable = s;
        Log.e("tag", "Disposable");
    }

    @Override
    public void onNext(T t) {
        Log.e("tag", "onNext");
        _onNext(t);
    }

    /**
     * 隐藏Dialog
     */
    private void dismissProgressDialog(){
        if (dialogHandler != null) {
            dialogHandler.dismiss();
            dialogHandler=null;
        }
    }
    @Override
    public void onError(Throwable e) {
        ToastUtils.showShortToast(e.toString());
        _onError(e.toString());
        dismissProgressDialog();

    }

    @Override
    public void onComplete() {
        Log.e("tag", "onComplete");
        dismissProgressDialog();
    }


    @Override
    public void onCancelProgress() {


        //back键关闭dialog
    Log.e("tag", "onCancelProgress");
        if (mDisposable != null) {
//            mDisposable.dispose();
        }

//        if (!this.isUnsubscribed()) {
//            this.unsubscribe();
//        }
    }
    protected abstract void _onNext(T t);
    protected abstract void _onError(String message);
}
