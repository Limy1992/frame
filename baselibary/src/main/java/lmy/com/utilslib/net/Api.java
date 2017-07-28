package lmy.com.utilslib.net;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 初始化re
 * Created by lmy on 2017/7/11
 */

public class Api {
    private static final long DEFAULT_TIMEOUT = 10000;
    private static ApiService apiService;

    public static ApiService getDefault() {
        if (apiService == null) {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
            httpClientBuilder.addInterceptor(new LoggingInterceptor());//使用自定义的Log拦截器
//
//            /**
//             *  拦截器
//             */
//            httpClientBuilder.addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request request = chain.request();
//                    HttpUrl.Builder authorizedUrlBuilder = request.url()
//                            .newBuilder()
//                            //添加统一参数 如手机唯一标识符,token等
//                            .addQueryParameter("key1","value1")
//                            .addQueryParameter("key2", "value2");
//
//                    Request newRequest = request.newBuilder()
//                            //对所有请求添加请求头
//                            .header("mobileFlag", "adfsaeefe").addHeader("type", "4")
//                            .method(request.method(), request.body())
//                            .url(authorizedUrlBuilder.build())
//                            .build();
//
//                    return  chain.proceed(newRequest);
//                }
//            });

            apiService = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://t.i-fully.cn/ilife/")
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }
}
