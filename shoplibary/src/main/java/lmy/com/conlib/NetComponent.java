package lmy.com.conlib;

import dagger.Component;
import lmy.com.conlib.bean.DaBean;
import lmy.com.conlib.bean.DaTwoBean;

/**
 * Created by lmy on 2017/7/24
 */
@Component(modules = {DaBean.class, DaTwoBean.class})
public interface NetComponent {
    void inject(NetActivity activity);
}
