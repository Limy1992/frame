package lmy.com.utilslib.base.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;
import lmy.com.utilslib.R;
import lmy.com.utilslib.net.ActivityLifeCycleEvent;

/**
 * Activity基类
 * Created by Matthew_Chen on 2017/4/14.
 */

public class TopBarBaseActivity extends AppCompatActivity {
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    public Toolbar toolbar;
    public FrameLayout viewContent;
    public TextView tvTitle;
    public Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_top_bar);
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        initView();

        setContentViews(savedInstanceState);

        //其他配置
        setAdditionConfigure();

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewContent = (FrameLayout) findViewById(R.id.viewContent);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
    }

    protected void setAdditionConfigure() {}

    protected void setContentViews(Bundle savedInstanceState) {}

    protected View getViewStub(){
        ViewStub tl_view_stub = (ViewStub) findViewById(R.id.tl_view_stub);
        tl_view_stub.inflate();
        return tl_view_stub;
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
    }

}





