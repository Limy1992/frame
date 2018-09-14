package audio_tools.com.model_demo1;

import android.content.Context;

import java.util.Map;

import lmy.com.utilslib.mvp.base.view.IBaseMvpView;

/**
 * Created by on 2018/8/10.
 *
 * @author lmy
 */
public interface IViewModel extends IBaseMvpView {
    Map<String, String> map();
}
