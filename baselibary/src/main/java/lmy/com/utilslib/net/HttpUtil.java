package lmy.com.utilslib.net;




import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.net.other.CallRequest;
import lmy.com.utilslib.net.rx.RxSchedulersHelper;
import lmy.com.utilslib.utils.LogUtils;


/**
 * 网络请求方法调用
 * Created by lmy on 2017/7/11
 */

@SuppressWarnings("all")
public class HttpUtil {

    /**
     * 构造方法私有
     */
    private HttpUtil() {
    }

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


    public static class Builder{
        private Observable observable;
        private boolean isShowDialog;
        private String cacheKey;
        private boolean isRefreshData;
        private LifecycleTransformer bindLifecycle;


        public Builder(){}

        /**
         * 创建请求体
         */
        public Builder create(Observable observable){
            this.observable = observable;
            return this;
        }

        public Builder bindLifecycle(LifecycleTransformer bindLifecycle){
            this.bindLifecycle = bindLifecycle;
            return this;
        }

        /**
         * 是否显示dialog 网络加载等待进度
         */
        public Builder showProgress(boolean isShowDialog){
            this.isShowDialog = isShowDialog;
            return this;
        }

        /**
         * 是否缓存data
         */
        public Builder cacheData(String cacheKey){
            this.cacheKey = cacheKey;
            return this;
        }

        /**
         * 是否强制刷新数据
         */
        public Builder refreshData(boolean isRefreshData){
            this.isRefreshData = isRefreshData;
            return this;
        }

        public void subscriber(final ProgressSubscriber subscriber){
            LogUtils.d("observable="+observable);
            LogUtils.d("isShowDialog="+isShowDialog);
            LogUtils.d("cacheKey="+cacheKey);
            LogUtils.d("isRefreshData="+isRefreshData);
            //数据预处理
            ObservableTransformer<BaseHttpResult<Object>, Object> transformer = RxHelper.handleResult();
            Observable onComplete = observable.compose(transformer)
                    .compose(bindLifecycle == null ? RxSchedulersHelper.ioMain() : bindLifecycle)
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            subscriber.onSubscribe(disposable);
                            if (isShowDialog) {
                                subscriber.showProgressDialog();
                            }
                        }
                    });

            onComplete.subscribe(new Consumer() {
                @Override
                public void accept(Object o) throws Exception {
                    subscriber.onNext(o);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    subscriber.onError(throwable);
                }
            }, new Action() {
                @Override
                public void run() throws Exception {
                    subscriber.onComplete();
                }
            });

            if (cacheKey != null) {
                //缓存处理
                RetrofitCache.load(cacheKey, onComplete, isRefreshData);
            }
        }

    }

//    /**
//     * 添加线程管理并订阅
//     *
//     * @param cacheKey     缓存kay
//     * @param isSave       是否缓存
//     * @param forceRefresh 是否强制刷新
//     */
//    public void toSubscribe(Observable ob
//            , LifecycleTransformer bindLifecycle
//            , final ProgressSubscriber subscriber
//            , String cacheKey
//            , boolean isSave
//            , boolean forceRefresh) {
//        //数据预处理
//        ObservableTransformer<BaseHttpResult<Object>, Object> transformer = RxHelper.handleResult();
//        Observable onComplete = ob.compose(transformer)
//                .compose(bindLifecycle)
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        subscriber.onSubscribe(disposable);
//                        subscriber.showProgressDialog();
//                    }
//                });
//
//        onComplete.subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                subscriber.onNext(o);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                subscriber.onError(throwable);
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//                subscriber.onComplete();
//            }
//        });
//
//        //缓存处理
//        RetrofitCache.load(cacheKey, onComplete, isSave, forceRefresh);
//    }
//
//    /**
//     * 添加线程管理并订阅
//     *
//     * @param ob
//     * @param subscriber
//     * @param cacheKey         缓存kay
//     * @param lifecycleSubject
//     * @param isSave           是否缓存
//     * @param forceRefresh     是否强制刷新
//     * @param isShowDialog     是否显示加载进度dialog
//     */
//    public void toSubscribe(Observable ob
//            , LifecycleTransformer bindLifecycle
//            , final ProgressSubscriber subscriber
//            , String cacheKey
//            , boolean isSave
//            , boolean forceRefresh
//            , final boolean isShowDialog) {
//        //数据预处理
//        ObservableTransformer<BaseHttpResult<Object>, Object> transformer = RxHelper.handleResult();
//        Observable onComplete = ob.compose(transformer)
//                .compose(bindLifecycle)
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        subscriber.onSubscribe(disposable);
//                        if (isShowDialog) {
//                            subscriber.showProgressDialog();
//                        }
//                        LogUtils.e("线层:" + Thread.currentThread().getName());
//                    }
//                });
//
//        onComplete.subscribe(new Consumer() {
//            @Override
//            public void accept(Object o) throws Exception {
//                subscriber.onNext(o);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                subscriber.onError(throwable);
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//                subscriber.onComplete();
//            }
//        });
//
//        //缓存处理
//        RetrofitCache.load(cacheKey, onComplete, isSave, forceRefresh);
//    }
//
//    /***
//     * 请求数据处理
//     * @param ob    Rx处理
//     * @param subscriber 请求处理结果
//     * @param isShowDialog  是否显示进度条
//     */
//    public void toSubscribe(Observable ob
//            , LifecycleTransformer bindLifecycle
//            , final ProgressSubscriber subscriber
//            , final boolean isShowDialog) {
//        //数据预处理
//        ObservableTransformer<BaseHttpResult<Object>, Object> transformer = RxHelper.handleResult();
//        ob.compose(transformer)
//                .compose(bindLifecycle)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        subscriber.onSubscribe(disposable);
//                        if (isShowDialog) {
//                            subscriber.showProgressDialog();
//                        }
//                    }
//                })
//                .subscribe(new Consumer() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        subscriber.onNext(o);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        subscriber.onError(throwable);
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        subscriber.onComplete();
//                    }
//                });
//
//    }

    /**
     * 需要回调
     *
     * @param callRequest 回调的类
     */
    public void toSubscribe(Observable ob, final CallRequest callRequest) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        try {
                            callRequest.onSuccess(o);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        try {
                            callRequest.onError(throwable);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 不需要回调
     */
    public void toSubscribe(Observable ob) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });

    }
}
