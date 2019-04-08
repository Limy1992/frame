package lmy.com.utilslib.base.more;

/**
 * 数据加载回调（不包含加载更多）
 * Created by on 2018/10/29.
 *
 * @author lmy
 */
public abstract class OnLoadDateListener extends OnLoadMoreDateListener {
    /***
     * 数据刷新
     */
    protected abstract void superRequestData();

    /**
     * 设置新数据
     */
    protected abstract void onNewData();

    @Override
    protected void onAddData() {

    }

    @Override
    protected int dataSize() {
        return -1;
    }

    @Override
    protected void nextErrPager() {
        super.nextErrPager();
    }
}
