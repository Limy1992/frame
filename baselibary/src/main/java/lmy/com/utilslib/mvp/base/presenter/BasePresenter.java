package lmy.com.utilslib.mvp.base.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/3/3 0003.
 * T  对应着Activity 的UI抽象接口  视图
 */

public abstract class BasePresenter<T> {
    /**
     * 持有UI接口的弱引用
     */
    protected WeakReference<T> mViewRef;

    /**
     * 获取数据方法
     */
    public abstract void requestData();

    public void requestData(boolean isShowDialog){

    }

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 解绑
     */
    public void detach()
    {
        if(mViewRef!=null)
        {
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
