package lmy.com.utilslib.base.more;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 监听适配，用来处理首次加载错误, 重写不必要实现的方法。
 * Created by on 2018/9/26.
 *
 * @author lmy
 */
public abstract class LoadErrListenerAdapter extends LoadMoreDateListener {
    /***
     * 数据刷新
     */
    protected abstract void superRequestData();

    @Override
    protected BaseQuickAdapter baseQuickAdapter() {
        return null;
    }

    @Override
    protected RecyclerView onRecycleView() {
        return null;
    }

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
