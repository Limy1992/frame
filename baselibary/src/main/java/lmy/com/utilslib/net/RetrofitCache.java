package lmy.com.utilslib.net;

import com.orhanobut.hawk.Hawk;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

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
                    subscriber.onNext(cache);
                } else {
                    subscriber.onComplete();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        //缓存cacheKey 如果不为null，就缓存数据
        if (cacheKey != null) {
            fromNetwork = fromNetwork.map(new Function<T, T>() {
                @Override
                public T apply(T result) throws Exception {
                    Hawk.put(cacheKey, result);
                    return result;
                }
            });
        }

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
