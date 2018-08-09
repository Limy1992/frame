package lmy.com.utilslib.net.api;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.bean.ModelBean;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 定义接口地址
 * Created by lmy on 2017/7/11
 */

public interface ApiService {
    /**
     * 下载
     */
    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Url String url);


    @GET("competition/getCompetitions")
    Observable<BaseHttpResult<List<ModelBean>>> getCompetitions(@QueryMap() Map<String, String> map);
}
