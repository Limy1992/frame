package lmy.com.utilslib.app;

import com.orhanobut.hawk.Hawk;


import lmy.com.utilslib.utils.Utils;

/**
 * 初始化三方操作
 * Created by lmy on 2017/7/14
 */

public class ConfigureApplication extends BaseApplication {
    @Override
    public void configureInitialization() {
        Utils.init(this);
        Hawk.init(this).build();
        //设置不随系统的设置字体改变
        Utils.setToDefaults();
    }
}
