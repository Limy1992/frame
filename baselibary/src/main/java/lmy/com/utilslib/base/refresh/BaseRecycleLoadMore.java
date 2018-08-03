package lmy.com.utilslib.base.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * Created by on 2018/5/31.
 *
 * @author lmy
 */
public class BaseRecycleLoadMore implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    public BaseRecycleLoadMore(){

    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

    public interface OnBaseRecycleLoadListener{
        RecyclerView recycleView();
        SwipeRefreshLayout refresh();
        BaseQuickAdapter adapter();
    }
}
