package audio_tools.com.model_demo1;

import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;

/**
 * Created by on 2018/8/10.
 *
 * @author lmy
 */
public class DemoViewLiveData extends LiveData<String> {

    private static DemoViewLiveData demoViewLiveData;

    @MainThread
    public static DemoViewLiveData get() {
        if (demoViewLiveData == null) {
            demoViewLiveData = new DemoViewLiveData();
        }
        return demoViewLiveData;
    }


    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }
}
