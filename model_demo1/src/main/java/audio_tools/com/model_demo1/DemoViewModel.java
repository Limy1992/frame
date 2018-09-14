package audio_tools.com.model_demo1;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by on 2018/8/10.
 *
 * @author lmy
 */
public class DemoViewModel extends ViewModel {

    private MutableLiveData<String> mutableLiveData;

    public MutableLiveData<String> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }
}
