package lmy.com.utilslib.base.more;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 数据回调
 * Created by on 2018/9/11.
 *
 * @author lmy
 */
public abstract class LoadMoreDateListener {

    /***
     * 数据刷新
     */
    protected abstract void superRequestData();

    /**
     * 获取列表填充adapter
     */
    protected abstract BaseQuickAdapter baseQuickAdapter();


    protected abstract RecyclerView onRecycleView();

    /**
     * 设置新数据
     */
    protected abstract void onNewData();

    /**
     * 添加数据
     */
    protected abstract void onAddData();

    /**
     * 当前页面的数据大小, 用于判断是否加载完毕
     */
    protected abstract int dataSize();

    /**
     * 获取刷新控件
     */
//    protected  SwipeRefreshLayout refreshLayout(){
//        return null;
//    }

    /**
     * 第一次加载数据，就出现错误，选择是否重写处理
     */
    protected void nextErrPager() {

    }

}
