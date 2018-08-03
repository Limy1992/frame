package lmy.com.utilslib.app;

import android.app.Application;
import android.content.Intent;

/**
 * 初始化三方操作
 * Created by lmy on 2017/7/14
 */

public class ConfigureApplication extends SampleApplicationLike {
    public ConfigureApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }
}
