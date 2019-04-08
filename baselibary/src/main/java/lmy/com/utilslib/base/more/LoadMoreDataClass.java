package lmy.com.utilslib.base.more;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private final BaseQuickAdapter baseQuickAdapter;
    private final RecyclerView recycleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    /**加载回调监听*/
    private OnLoadMoreDateListener mOnLoadMoreListener;
    /**空视图view*/
    private View emptyView;
    /**是否隐藏数据加载完毕，显示无更多数据的文本， 默认隐藏*/
    private boolean isLoadMoreEnd = true;
    /**数据加载回调，不包含加载更多*/
//    private OnLoadDateListener mOnLoadDateListener;

    public LoadMoreDataClass(Context mContext
            , BaseQuickAdapter baseQuickAdapter
            , RecyclerView recyclerView) {
        this.mContext = mContext;
        this.baseQuickAdapter = baseQuickAdapter;
        this.recycleView = recyclerView;
    }

    public LoadMoreDataClass(Context mContext
            , BaseQuickAdapter baseQuickAdapter
            , RecyclerView recyclerView
            , SwipeRefreshLayout swipeRefreshLayout) {
        this.mContext = mContext;
        this.baseQuickAdapter = baseQuickAdapter;
        this.recycleView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.v3_music_home
                , R.color.rea, R.color.app_them_color);
    }

    /**
     * 是否已加载数据
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

    /**
     * 刷新
     */
    @Override
    public void onRefresh() {
        if (mOnLoadMoreListener == null) {
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        if (!isLoadErr || isLoadMore) {
            pagerNum = 1;
            isRefresh = true;
            baseQuickAdapter.setEnableLoadMore(false);
        }
        mOnLoadMoreListener.superRequestData();
    }

    /**
     * load more
     */
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
    public void setOnLoadMoreErrorListener(OnLoadErrListenerAdapter loadErrListenerAdapter) {
        mOnLoadMoreListener = loadErrListenerAdapter;
        isLoadErr = true;
        if (isRefresh) {
            ToastUtils.showShortToast("刷新错误");
        } else {
            if (isLoadMore) {
                //加载更多错
                baseQuickAdapter.loadMoreFail();
            } else {
                mOnLoadMoreListener.nextErrPager();
            }
        }

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 处理首次添加数据、刷新、加载更多。在网络请求的回调中使用。
     */
    public void setOnLoadMoreSuccessListener(OnLoadMoreDateListener onLoadMoreListener) {
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener = null;
        }
        this.mOnLoadMoreListener = onLoadMoreListener;
        isLoadErr = false;
        if (!isLoadMore) {
            //首次加载
            isLoadMore = true;
            //设置新的数据
            mOnLoadMoreListener.onNewData();
            if (mOnLoadMoreListener.dataSize() > CommonManger.LOAD_SIZE) {
                //符号条件设置可以加载更多
                baseQuickAdapter.setOnLoadMoreListener(this, recycleView);
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
                    baseQuickAdapter.loadMoreEnd(isLoadMoreEnd);
                }
            }
        }

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(true);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 数据加载，不包含加载更多
     */
    public void setOnLoadSuccess(OnLoadDateListener onLoadDateListener){
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener = null;
        }
        this.mOnLoadMoreListener = onLoadDateListener;
        isLoadErr = false;
        //数据数量， 默认=-1（如果不需要设置空数据的view，就不需要重写dataSize（）方法）
        if (mOnLoadMoreListener.dataSize() == 0) {
            onEmptyView(0);
        }else {
            //数量只要不为0，就设置新数据
            mOnLoadMoreListener.onNewData();
            FrameLayout emptyView = (FrameLayout) baseQuickAdapter.getEmptyView();
            if (emptyView != null) {
                emptyView.removeAllViews();
            }
        }

        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 当前页面没有数据
     */
    private void onEmptyView(int dataSize) {
        if (dataSize == 0) {
            if (!mOnLoadMoreListener.isRewSetEmptyView()) {
                View view;
                if (emptyView == null) {
                    view = LayoutInflater.from(mContext).inflate(R.layout.empty_view, null);
                }else {
                    view = emptyView;
                }
                if (baseQuickAdapter != null) {
                    baseQuickAdapter.setEmptyView(view);
                }
            }
        }
    }


    /**
     * 设置空数据视图
     * @param emptyView 空数据展示的view
     */
    public LoadMoreDataClass setEmptyView(View emptyView){
        this.emptyView = emptyView;
        return this;
    }

    /**
     * 空数据视图
     */
    public LoadMoreDataClass setEmptyView(String text){
        TextView textView = new TextView(mContext);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        textView.setTextColor(mContext.getResources().getColor(R.color.v3_black));
        textView.setGravity(Gravity.CENTER);
        emptyView = textView;
        return this;
    }

    /**
     * 是否隐藏loadMoreEnd
     */
    public LoadMoreDataClass isLoadMoreEnd(boolean isLoadMoreEnd){
        this.isLoadMoreEnd = isLoadMoreEnd;
        return this;
    }

    /**
     * 设置错误的页面view
     */
    public void setErrPagerView() {
        if (baseQuickAdapter != null && baseQuickAdapter.getData().size() == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.empty_view, null);
            final TextView emptyText = view.findViewById(R.id.empty_text);
            final ProgressBar emptyLoading = view.findViewById(R.id.empty_loading);
            emptyText.setText("加载失败,点击重试");
            emptyText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emptyLoading.setVisibility(View.VISIBLE);
                    emptyText.setVisibility(View.GONE);
                    onLoadMoreRequested();
                }
            });
            baseQuickAdapter.setEmptyView(view);
        }
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean refresh) {
        isRefresh = refresh;
    }

    public boolean isLoadErr() {
        return isLoadErr;
    }

    public int getPagerNum() {
        return pagerNum;
    }

    public void setPagerNum(int pagerNum) {
        this.pagerNum = pagerNum;
    }
}
