package lmy.com.utilslib.net.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import lmy.com.utilslib.bean.BaseHttpResult;
import lmy.com.utilslib.net.api.ApiException;
import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * 解析返回数据
 * Created by LMY on 2018/3/25.
 */

final class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        BaseHttpResult httpStatus = gson.fromJson(response, BaseHttpResult.class);
        if (httpStatus.code != 1) {
            //更具项目做修改
            value.close();
            throw new ApiException(httpStatus.code, httpStatus.msg);
        }else {
            if (httpStatus.content == null) {
                BaseHttpResult baseHttpResult = new BaseHttpResult();
                baseHttpResult.content = new Object();
                baseHttpResult.code = httpStatus.code;
                baseHttpResult.msg = httpStatus.msg;
                response = gson.toJson(baseHttpResult);
            }
            try {
                return adapter.fromJson(response);
            } finally {
                value.close();
            }
        }
    }
}
