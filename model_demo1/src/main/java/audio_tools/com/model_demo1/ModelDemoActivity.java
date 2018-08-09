package audio_tools.com.model_demo1;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import lmy.com.utilslib.base.ui.activity.BaseTitleActivity;
import lmy.com.utilslib.base.ui.activity.more.BaseMoreMvcTitleActivity;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.bean.ModelBean;
import lmy.com.utilslib.net.HttpUtil;
import lmy.com.utilslib.net.ProgressSubscriber;
import lmy.com.utilslib.net.api.Api;
import lmy.com.utilslib.utils.LogUtils;

/**
 * Created by on 2018/8/9.
 *
 * @author lmy
 */
public class ModelDemoActivity extends BaseMoreMvcTitleActivity {
    @BindView(R2.id.recycle_view2)
    RecyclerView recyclerView;
    @BindView(R2.id.swipe_refresh2)
    SwipeRefreshLayout swipeRefresh;
    private ModelAdapter modelAdapter;

    @Override
    protected int getContentView() {
        return R.layout.model_demo_activity;
    }

    @Override
    protected String setTextTitle() {
        return "组件1";
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        modelAdapter = new ModelAdapter(null);
        recyclerView.setAdapter(modelAdapter);
        modelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @Override
    protected void initData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo",String.valueOf(pagerNum));
        map.put("sortField","id");
        map.put("sortReverse","1");
        map.put("pageSize","20");
        final Observable<BaseHttpResult<List<ModelBean>>> observable = Api.getDefault().getCompetitions(map);

        HttpUtil.getInstance()
                .getBuilder()
                .create(observable)
                .bindLifecycle(bindUntilEvent(ActivityEvent.PAUSE))
                .showProgress(true, mContext)
                .subscriber(new ProgressSubscriber<List<ModelBean>>() {
                    @Override
                    protected void onLoadSuccess(final List<ModelBean> modelBeans) {
                        ModelDemoActivity.super.onLoadSuccess(new OnLoadMoreListener() {
                            @Override
                            public RecyclerView onRecycleView() {
                                return recyclerView;
                            }

                            @Override
                            public void onNewData() {
                                modelAdapter.setNewData(modelBeans);
                            }

                            @Override
                            public void onAddData() {
                                modelAdapter.addData(modelBeans);
                            }

                            @Override
                            public int dataSize() {
                                return modelBeans.size();
                            }
                        });
                    }

                    @Override
                    protected void onLoadError(String message) {
                        ModelDemoActivity.super.onLoadError();
                    }
                });
    }

    @Override
    protected SwipeRefreshLayout refreshLayout() {
        return swipeRefresh;
    }

    @Override
    protected BaseQuickAdapter baseQuickAdapter() {
        return modelAdapter;
    }
}
