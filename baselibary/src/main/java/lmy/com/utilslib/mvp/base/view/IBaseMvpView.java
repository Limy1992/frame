package lmy.com.utilslib.mvp.base.view;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * v层通用方法
 * Created by on 2017/12/14.
 *
 * @author lmy
 */

public interface IBaseMvpView {
    Context getContexts();

    LifecycleTransformer bindLifecycle();

    void onError();
}
