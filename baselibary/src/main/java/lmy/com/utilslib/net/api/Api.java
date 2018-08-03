package lmy.com.utilslib.net.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import lmy.com.utilslib.net.converter.CustomGsonConverterFactory;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * 初始化re
 * Created by lmy on 2017/7/11
 */

public class Api extends ApiHttpClient {
    private static ApiService apiService;

    public static ApiService getDefault() {
        if (apiService == null) {
            apiService = new Retrofit.Builder()
                    .client(httpClient().build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(CustomGsonConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl("http://101.132.120.121/")
                    .baseUrl("https://www.ymcaijing.com/")
                    .build()
                    .create(ApiService.class);
        }
        return apiService;
    }

    public static RequestBody postJson(String json) {
        return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), json);
    }
}
