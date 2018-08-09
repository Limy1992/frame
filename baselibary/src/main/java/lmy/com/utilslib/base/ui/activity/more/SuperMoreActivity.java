package lmy.com.utilslib.base.ui.activity.more;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.LifecycleTransformer;

import lmy.com.utilslib.R;
import lmy.com.utilslib.base.ui.activity.SuperInitActivity;
import lmy.com.utilslib.mvp.base.view.IBaseMvpView;
import lmy.com.utilslib.utils.CommonManger;
import lmy.com.utilslib.utils.ToastUtils;

/**
 * 加载更多和刷新
 * Created by on 2018/8/9.
 *
 * @author lmy
 */
public abstract class SuperMoreActivity extends SuperInitActivity
        implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener
        , IBaseMvpView {
    /**
     * 是否加载更多
     */
    protected boolean isLoadMore;
    /**
     * 是否刷新
     */
    protected boolean isRefresh;
    /**
     * 是否加载错误
     */
    protected boolean isLoadErr;
    /**
     * 当前加载页数 默认1
     */
    protected int pagerNum = 1;
    /**
     * 数据请求成功后，首次数据加载、刷新、加载更多判断
     */
    private OnLoadMoreListener mOnLoadMoreListener;

    @Override
    public void setContentViews(Bundle savedInstanceState) {
        super.setContentViews(savedInstanceState);
    }

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        if (!isLoadErr) {
            if (baseQuickAdapter() == null) {
                throw new NullPointerException("baseQuickAdapter() is null");
            }
            pagerNum = 1;
            isRefresh = true;
            baseQuickAdapter().setEnableLoadMore(false);
        } else {
            isLoadMore = false;
        }
        superRequestData();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        if (!isLoadErr) {
            //加载错误不执行
            if (refreshLayout() != null) {
                refreshLayout().setEnabled(false);
            }
            pagerNum += 1;
            superRequestData();
        }
    }

    /**
     * load数据
     */
    public void superRequestData() {
        initData();
    }

    /**
     * 加载错误
     */
    @Override
    public void onLoadError() {
        isLoadErr = true;
        if (isRefresh) {
            ToastUtils.showShortToast("刷新错误");
        } else {
            if (isLoadMore) {
                //加载更多错
                BaseQuickAdapter baseQuickAdapter = baseQuickAdapter();
                if (baseQuickAdapter == null) {
                    throw new NullPointerException("baseQuickAdapter() is null");
                }
                baseQuickAdapter.loadMoreFail();
            } else {
                nextErrPager();
            }
        }
    }

    /**
     * 处理首次添加数据、刷新、加载更多。在网络请求的回调中使用。
     */
    protected void onLoadSuccess(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
        BaseQuickAdapter baseQuickAdapter = baseQuickAdapter();
        if (baseQuickAdapter == null) {
            throw new NullPointerException("baseQuickAdapter() is null");
        }
        if (!isLoadMore) {
            //首次加载
            isLoadErr = false;
            isLoadMore = true;
            //设置新的数据
            mOnLoadMoreListener.onNewData();
            if (mOnLoadMoreListener.dataSize() > CommonManger.LOAD_SIZE) {
                //符号条件设置可以加载更多
                baseQuickAdapter.setOnLoadMoreListener(this, mOnLoadMoreListener.onRecycleView());
            }
            onEmptyView(mOnLoadMoreListener.dataSize());
        } else {
            if (isRefresh) {
                //刷新操作
                isRefresh = false;
                //刷新完毕后设置可以加载更多
                baseQuickAdapter.setEnableLoadMore(true);
                mOnLoadMoreListener.onNewData();
                onEmptyView(mOnLoadMoreListener.dataSize());
            } else {
                if (mOnLoadMoreListener.dataSize() > 0) {
                    //加载更多
                    mOnLoadMoreListener.onAddData();
                    baseQuickAdapter.loadMoreComplete();
                } else {
                    //没有更多数据
                    baseQuickAdapter.loadMoreEnd(false);
                }
            }
        }
        refreshLayout().setEnabled(true);
        refreshLayout().setRefreshing(false);
    }

    @Override
    public Context getContexts() {
        return mContext;
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindToLifecycle();
    }

    /**
     * 当前页面没有数据
     */
    protected void onEmptyView(int dataSize) {
        if (dataSize == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.empty_view, null);
            BaseQuickAdapter baseQuickAdapter = baseQuickAdapter();
            if (baseQuickAdapter != null) {
                baseQuickAdapter.setEmptyView(view);
            }
        }
    }

    /**
     * 获取刷新控件
     */
    protected abstract SwipeRefreshLayout refreshLayout();

    /**
     * 获取列表填充adapter
     */
    protected abstract BaseQuickAdapter baseQuickAdapter();

//    /**
//     * 设置数据load回调
//     */
//    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
//        this.mOnLoadMoreListener = onLoadMoreListener;
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener = null;
        }
    }

    /**
     * 处理首次加载、刷新、加载更多、回调
     */
    public interface OnLoadMoreListener {
        RecyclerView onRecycleView();

        /**
         * 设置新数据
         */
        void onNewData();

        /**
         * 添加数据
         */
        void onAddData();

        /**
         * 当前页面的数据大小, 用于判断是否加载完毕
         */
        int dataSize();
    }
}
