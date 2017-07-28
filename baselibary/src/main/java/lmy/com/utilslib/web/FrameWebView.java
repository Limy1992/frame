package lmy.com.utilslib.web;
/**
 * Created by lmy on 2017/7/21
 */

public class FrameWebView extends BaseWebView {

    @Override
    protected void setLoadUrl() {
        forumContext.loadUrl("https://mp.weixin.qq.com/s/iFX9gOJm-gYnuP9PpR3n4Q");
    }
}
