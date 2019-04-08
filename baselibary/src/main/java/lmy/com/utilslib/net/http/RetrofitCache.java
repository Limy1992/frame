package lmy.com.utilslib.net.http;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import lmy.com.utilslib.utils.LogUtils;

/**
 * 缓存数据
 * Created by lmy on 2017/7/11
 */

class RetrofitCache {
    /**
     * @param cacheKey     缓存的Key
     * @param fromNetwork
     * @param forceRefresh 是否强制刷新
     */
    static <T> Observable<T> load(final String cacheKey,
                                  Observable<T> fromNetwork,
                                  boolean forceRefresh) {
        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                T cache = Hawk.get(cacheKey);
                if (cache != null) {
                    if (cache instanceof List) {
                        if (((List) cache).size() == 0) {
                            subscriber.onError(new Throwable("缓存数据错误"));
                        }else {
                            subscriber.onNext(cache);
                        }
                    }else {
                        subscriber.onNext(cache);
                    }
                } else {
                    subscriber.onError(new Throwable("缓存数据错误"));
//                    subscriber.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        //强制刷新
        if (forceRefresh) {
            return fromNetwork;
        } else {
            return Observable.concat(fromCache, fromNetwork).filter(new Predicate<T>() {
                @Override
                public boolean test(T t) throws Exception {
                    return t != null;
                }
            });
        }
    }
}
