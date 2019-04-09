package lmy.com.utilslib.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 正式版中使用
 * tinker
 * Created by on 2018/1/22.
 *
 * @author lmy
 */

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "lmy.com.utilslib.app.BaseApplication",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
