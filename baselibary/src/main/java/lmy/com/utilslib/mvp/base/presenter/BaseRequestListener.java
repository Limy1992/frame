package lmy.com.utilslib.mvp.base.presenter;

import android.content.Context;


import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * 通用M层接口回调
 * Created by on 2017/12/11.
 *
 * @author lmy
 */

public interface BaseRequestListener {
    Context getContexts();

    LifecycleTransformer bindLifecycle();

    void onRequestErr();
}
