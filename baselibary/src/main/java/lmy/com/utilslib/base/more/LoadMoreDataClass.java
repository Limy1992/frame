package lmy.com.utilslib.base.more;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.CommonManger;
import lmy.com.utilslib.utils.ToastUtils;

/**
 * 加载更多 下拉刷新
 * Created by on 2018/9/11.
 *
 * @author lmy
 */
public class LoadMoreDataClass implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private final Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreDateListener mOnLoadMoreListener;

    public LoadMoreDataClass(Context mContext) {
        this.mContext = mContext;
    }

    public LoadMoreDataClass(Context mContext, SwipeRefreshLayout swipeRefreshLayout) {
        this.mContext = mContext;
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 是否加载更多
     */
    private boolean isLoadMore;
    /**
     * 是否刷新
     */
    private boolean isRefresh;
    /**
     * 是否加载错误
     */
    private boolean isLoadErr;
    /**
     * 当前加载页数 默认1
     */
    private int pagerNum = 1;

    @Override
    public void onRefresh() {
        if (mOnLoadMoreListener == null) {
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        if (!isLoadErr) {
            if (mOnLoadMoreListener.baseQuickAdapter() == null) {
                throw new NullPointerException("baseQuickAdapter() is null");
            }
            pagerNum = 1;
            isRefresh = true;
            mOnLoadMoreListener.baseQuickAdapter().setEnableLoadMore(false);
        } else {
            isLoadMore = false;
        }
        mOnLoadMoreListener.superRequestData();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mOnLoadMoreListener == null) {
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        if (!isLoadErr) {
            //加载错误不执行
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setEnabled(false);
            }
            pagerNum += 1;
        }
        mOnLoadMoreListener.superRequestData();
    }

    /**
     * 加载错误
     */
    public void onLoadError() {
        isLoadErr = true;
        if (mOnLoadMoreListener == null) {
            return;
        }
        if (isRefresh) {
            ToastUtils.showShortToast("刷新错误");
        } else {
            if (isLoadMore) {
                //加载更多错
                BaseQuickAdapter baseQuickAdapter = mOnLoadMoreListener.baseQuickAdapter();
                if (baseQuickAdapter == null) {
                    throw new NullPointerException("baseQuickAdapter() is null");
                }
                baseQuickAdapter.loadMoreFail();
            } else {
                mOnLoadMoreListener.nextErrPager();
            }
        }
    }

    /**
     * 处理首次添加数据、刷新、加载更多。在网络请求的回调中使用。
     */
    public void onLoadSuccess(LoadMoreDateListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
        BaseQuickAdapter baseQuickAdapter = mOnLoadMoreListener.baseQuickAdapter();
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

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(true);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 当前页面没有数据
     */
    private void onEmptyView(int dataSize) {
        if (dataSize == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.empty_view, null);
            BaseQuickAdapter baseQuickAdapter = mOnLoadMoreListener.baseQuickAdapter();
            if (baseQuickAdapter != null) {
                baseQuickAdapter.setEmptyView(view);
            }
        }
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public boolean isLoadErr() {
        return isLoadErr;
    }

    public int getPagerNum() {
        return pagerNum;
    }
}
