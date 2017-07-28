package lmy.com.utilslib.mvp.base;

/**
 * Created by lmy on 2017/7/13
 */

public class BasePrestent<V extends BaseImlView> implements BaseImpPreset<V> {
    private V mView;

    @Override
    public void bingView(V view) {
        this.mView = view;
    }

    @Override
    public void destroyView() {
        if (mView != null) {
            mView = null;
        }
    }

    //是否绑定view
    public boolean isBingView(){
        return mView != null;
    }

    //获取view
    public V getmView(){
        return mView;
    }

    //检查view是否绑定
    public void inspectView(){
        if (!isBingView()) {
            throw new RuntimeException("mView == null");
        }
    }
}
