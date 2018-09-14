package audio_tools.com.model_demo1;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
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
import lmy.com.utilslib.glide.GlideApp;
import lmy.com.utilslib.net.HttpUtil;
import lmy.com.utilslib.net.ProgressSubscriber;
import lmy.com.utilslib.net.api.Api;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.ToastUtils;

/**
 * Created by on 2018/8/9.
 *
 * @author lmy
 */
public class ModelDemoActivity extends BaseMoreMvcTitleActivity implements IViewModel{
    @BindView(R.id.recycle_view2)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh2)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R2.id.lv)
    ImageView lv;
    private ModelAdapter modelAdapter;
    private DemoViewModel viewModel;
    private ViewListModel listModel;

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
        listModel = ViewModelProviders.of(this).get(ViewListModel.class);
        listModel.setViewModel(this);
        listModel.getMutableLiveData().observe(this, new Observer<List<ModelBean>>() {
            @Override
            public void onChanged(@Nullable final List<ModelBean> modelBeans) {
                LogUtils.d("UI展示成功");
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
        });
    }

    @Override
    protected void initData() {
        listModel.requestData();
    }

    @Override
    protected SwipeRefreshLayout refreshLayout() {
        return swipeRefresh;
    }

    @Override
    protected BaseQuickAdapter baseQuickAdapter() {
        return modelAdapter;
    }

    @Override
    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNo",String.valueOf(pagerNum));
        map.put("sortField","id");
        map.put("sortReverse","1");
        map.put("pageSize","20");
        return map;
    }
}
