package lmy.com.utilslib.net.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import lmy.com.utilslib.app.BaseApplication;
import lmy.com.utilslib.net.LoggingInterceptor;
import lmy.com.utilslib.net.down.normal.ProgressInterceptor;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.SPUtils;
import lmy.com.utilslib.utils.Utils;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * HttpClient
 * Created by on 2018/8/1.
 *
 * @author lmy
 */
class ApiHttpClient {
    private static final long DEFAULT_TIMEOUT = 10000;
    /**
     * 功能参数 客户端标识
     */
    private static final String PLATFORM = "0";
    /**
     * 功能参数 APP版本
     */
    private static final String SYSTEM = Utils.getSystemVersion();

    /**
     * 设置httpClient
     */
    static OkHttpClient.Builder httpClient() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        clientBuilder(httpClientBuilder);

        return httpClientBuilder;
    }

    private static void clientBuilder(OkHttpClient.Builder httpClientBuilder) {
        if (BaseApplication.APP_DEBUG) {
            //使用自定义的Log拦截器
            //设置 Debug Log 模式
            httpClientBuilder.addInterceptor(new LoggingInterceptor());
            //fecckbook程序调试
            httpClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }
        //进度
        httpClientBuilder.addInterceptor(new ProgressInterceptor());

        //拦截器
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl.Builder authorizedUrlBuilder = request.url()
                        .newBuilder()
                        //添加统一参数 如手机唯一标识符,token等
                        .addQueryParameter("platform", PLATFORM)
                        .addQueryParameter("system", SYSTEM)
                        .addQueryParameter("software", Utils.getVersionName())
                        .addQueryParameter("dataAccountId", "");

                Request newRequest = request.newBuilder()
                        //对所有请求添加请求头
//                            .header("mobileFlag", "adfsaeefe")
//                            .addHeader("type", "4")
                        .method(request.method(), request.body())
                        .url(authorizedUrlBuilder.build())
                        .build();

                return chain.proceed(newRequest);
            }
        });
    }
}
