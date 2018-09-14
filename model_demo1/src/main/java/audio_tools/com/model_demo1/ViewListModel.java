package audio_tools.com.model_demo1;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.bean.ModelBean;
import lmy.com.utilslib.net.HttpUtil;
import lmy.com.utilslib.net.ProgressSubscriber;
import lmy.com.utilslib.net.api.Api;
import lmy.com.utilslib.utils.LogUtils;

/**
 * Created by on 2018/8/10.
 *
 * @author lmy
 */
public class ViewListModel extends AndroidViewModel {

    private MutableLiveData<List<ModelBean>> mutableLiveData;
    private IViewModel iViewModel;

    public ViewListModel(@NonNull Application application) {
        super(application);
    }

    public void setViewModel(IViewModel iViewModel){
        this.iViewModel = iViewModel;
    }
    public MutableLiveData<List<ModelBean>> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void requestData(){
        Observable<BaseHttpResult<List<ModelBean>>> competitions = Api.getDefault().getCompetitions(iViewModel.map());
        HttpUtil.getInstance()
                .getBuilder()
                .create(competitions)
                .showProgress(true, iViewModel.onContext())
                .subscriber(new ProgressSubscriber<List<ModelBean>>() {
                    @Override
                    protected void onLoadSuccess(final List<ModelBean> modelBeans) {
                        mutableLiveData.setValue(modelBeans);
                    }

                    @Override
                    protected void onLoadError(String message) {
                        iViewModel.onLoadError();
                    }
                });
    }
}
