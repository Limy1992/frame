package lmy.com.utilslib.mvp.base;

import android.app.Activity;

/**
 * view基类
 * Created by lmy on 2017/7/12
 */

public interface BaseImlView {
    Activity getActivityes();           //获取activity

    void onLoadSuccess();               //加载成功

    void onLoadErr();                   //加载错误

    void showToast(String msg);         //显示toast
}
