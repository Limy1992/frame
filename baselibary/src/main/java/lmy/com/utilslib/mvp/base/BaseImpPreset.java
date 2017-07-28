package lmy.com.utilslib.mvp.base;


/**
 * Created by lmy on 2017/7/13
 */

public interface BaseImpPreset<V extends BaseImlView> {
    void bingView(V view);  //绑定view

    void destroyView();     //消费view
}
