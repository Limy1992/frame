package lmy.com.utilslib.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * lib组件中使用
 * Created by on 2018/3/22.
 *
 * @author lmy
 */

public class DebugApplication extends TinkerApplication {
    public DebugApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "lmy.com.utilslib.app.BaseApplication",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
