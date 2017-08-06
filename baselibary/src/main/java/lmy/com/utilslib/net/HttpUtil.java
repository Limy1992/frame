package lmy.com.utilslib.net;

import android.util.Log;


import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import lmy.com.utilslib.bean.BaseHttpResult;


/**
 * 网络请求方法调用
 * Created by lmy on 2017/7/11
 */

public class HttpUtil {

    /**
     * 构造方法私有
     */
    private HttpUtil() {}

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }
    /**
     * 添加线程管理并订阅
     * @param ob
     * @param subscriber
     * @param cacheKey 缓存kay
     * @param event Activity 生命周期
     * @param lifecycleSubject
     * @param isSave 是否缓存
     * @param forceRefresh 是否强制刷新
     */
    public void toSubscribe(Observable ob, final ProgressSubscriber subscriber, String cacheKey, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, boolean isSave, boolean forceRefresh) {
        //数据预处理
        ObservableTransformer<BaseHttpResult<Object>, Object> transformer = RxHelper.handleResult(event, lifecycleSubject);
        Observable onComplete = ob.compose(transformer)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e("tag", "Exception");
                        //显示Dialog
                        subscriber.showProgressDialog();
                    }
                });
        RetrofitCache.load(cacheKey, onComplete, isSave, forceRefresh).subscribe(new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                subscriber.onSubscribe(d);
            }

            @Override
            public void onNext(Object value) {
                subscriber.onNext(value);
            }

            @Override
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override
            public void onComplete() {
                subscriber.onComplete();
            }
        });
    }
}
