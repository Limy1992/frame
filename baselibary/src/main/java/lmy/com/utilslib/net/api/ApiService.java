package lmy.com.utilslib.net.api;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
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


}
