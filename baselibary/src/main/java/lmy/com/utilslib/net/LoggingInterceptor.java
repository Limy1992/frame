package lmy.com.utilslib.net;

import android.util.Log;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Log日志
 * Created by lmy on 2017/7/20
 */

public class LoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        //请求发起的时间
        long t1 = System.nanoTime();
        String method = request.method();
        //请求log
        postAndGet(method, request, chain);

        Response response = chain.proceed(request);
        //收到响应的时间
        long t2 = System.nanoTime();
        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理
        //响应log
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        Log.e(TAG, String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                response.request().url(),
                responseBody.string(),
                (t2 - t1) / 1e6d,
                response.headers()
        ));

//
        BufferedSource source = responseBody.source();
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                return response;
            }
        }
        long contentLength = responseBody.contentLength();
        if (contentLength != 0) {
            Log.e(TAG, "JSON:" + buffer.readString(charset));
        }

        return response;
    }

    private void postAndGet(String method, Request request, Chain chain) {
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i))
                            .append("=")
                            .append(body.encodedValue(i))
                            .append(",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.e(TAG, String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                        request.url(), chain.connection(), request.headers(), sb.toString()));
            }
        } else {
            Log.e(TAG, String.format("发送请求 %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
        }
    }
}
