package lmy.com.utilslib.base.more;


/**
 * 监听适配，用来处理首次加载错误, 重写不必要实现的方法。
 * Created by on 2018/9/26.
 *
 * @author lmy
 */
public abstract class OnLoadErrListenerAdapter extends OnLoadMoreDateListener {
    /***
     * 数据刷新
     */
    protected abstract void superRequestData();

    @Override
    protected void onAddData() {

    }

    @Override
    protected void onNewData() {

    }

    @Override
    protected int dataSize() {
        return 0;
    }

    @Override
    protected void nextErrPager() {
        super.nextErrPager();
    }
}
