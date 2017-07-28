package lmy.com.utilslib.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.PublishSubject;
import lmy.com.utilslib.net.ActivityLifeCycleEvent;

/**
 *  Fragment基类
 * Created by lmy on 2017/7/25
 */

public abstract class BaseHomeFragment extends Fragment {
    private Unbinder bind;
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentView(), null);
        bind = ButterKnife.bind(this, view);
        Log.e("tag", "ButterKnife");
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        return view;
    }

    protected abstract int getFragmentView();

    @Override
    public void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        bind.unbind();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
        Log.e("tag", "lifecycleSubject");
        super.onDestroyView();
    }
}
