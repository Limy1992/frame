package lmy.com.utilslib.app;

import com.orhanobut.hawk.Hawk;


import lmy.com.utilslib.utils.Utils;

/**
 * Created by lmy on 2017/7/14
 */

public class ConfigureApplication extends BaseApplication {
    @Override
    public void configureInitialization() {

        Utils.init(this);
        Hawk.init(this).build();
    }
}
