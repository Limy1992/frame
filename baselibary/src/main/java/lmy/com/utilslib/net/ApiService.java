package lmy.com.utilslib.net;

import java.util.List;

import io.reactivex.Observable;
import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.bean.DataBean;
import lmy.com.utilslib.bean.HomeBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 定义接口地址
 * Created by lmy on 2017/7/11
 */

public interface ApiService {
    @POST("wordAttendance/selectOneMonth")
    Observable<BaseHttpResult<List<DataBean.ListBean>>> getServiceData(@Query("token") String token);


    @GET("login/checkupdate/")
    Call<HomeBean> getHomeBean();
}
