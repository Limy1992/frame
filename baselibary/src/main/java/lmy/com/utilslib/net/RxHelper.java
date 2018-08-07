package lmy.com.utilslib.net;

import android.support.annotation.NonNull;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.net.api.ApiException;

/**
 * 处理数据
 * Created by lmy on 2017/7/11
 */

public class RxHelper {

//    /**
//     * 利用Observable.takeUntil()停止网络请求
//     */
//    @NonNull
//    public static <T> ObservableTransformer<T, T> bindUntilEvent(@NonNull final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
//
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//
//                Observable<ActivityLifeCycleEvent> lifeCycleEventObservable = lifecycleSubject.filter(new Predicate<ActivityLifeCycleEvent>() {
//                    @Override
//                    public boolean test(ActivityLifeCycleEvent activityLifeCycleEvent) throws Exception {
//                        return activityLifeCycleEvent.equals(event);
//                    }
//                });
//                return upstream.takeUntil(lifeCycleEventObservable);
//            }
//        };
//    }

    /**
     * 数据处理
     */
    public static <T> ObservableTransformer<BaseHttpResult<T>, T> handleResult() {
        return new ObservableTransformer<BaseHttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseHttpResult<T>> upstream) {
                return upstream.flatMap(new Function<BaseHttpResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseHttpResult<T> tBaseHttpResult) throws Exception {
                        if (tBaseHttpResult.code == 1) {
                            return createData(tBaseHttpResult.content);
                        } else {
                            return Observable.error(new ApiException(tBaseHttpResult.code, tBaseHttpResult.msg));
                        }
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * rcode == 1 发送数据
     *
     * @param data 成功的数据
     * @param <T>  类型
     */
    private static <T> Observable<T> createData(final T data) {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                try {
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
