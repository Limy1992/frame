package lmy.com.utilslib.net.down.sopt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import lmy.com.utilslib.utils.LogUtils;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 处理下载内容
 * Created by on 2018/4/28.
 *
 * @author lmy
 */
public class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
    private final DownloadInfo downloadInfo;
    private final OkHttpClient mClient;
    private final HashMap<String, Call> downCalls;

    public DownloadSubscribe(DownloadInfo downloadInfo, OkHttpClient mClient, HashMap<String, Call> downCalls) {
        this.downloadInfo = downloadInfo;
        this.mClient = mClient;
        this.downCalls = downCalls;
    }

    @Override
    public void subscribe(ObservableEmitter<DownloadInfo> e) throws Exception {
        String url = downloadInfo.getUrl();
        long downloadLength = downloadInfo.getProgress();//已经下载好的长度
        long contentLength = downloadInfo.getTotal();//文件的总长度
        //初始进度信息
        e.onNext(downloadInfo);

        LogUtils.d("下载的url=" + url);
        Request request = new Request.Builder()
                //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                .url(url)
                .build();
        Call call = mClient.newCall(request);
        downCalls.put(url, call);//把这个添加到call里,方便取消
        Response response = call.execute();

        File file = new File(downloadInfo.getFilePath(), downloadInfo.getFileName());
        InputStream is = null;
        FileOutputStream fileOutputStream = null;
        try {
            is = response.body().byteStream();
            fileOutputStream = new FileOutputStream(file, true);
            byte[] buffer = new byte[2048];//缓冲数组2kB
            int len;
            while ((len = is.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
                downloadLength += len;
                downloadInfo.setProgress(downloadLength);
                LogUtils.d("发送进度数据");
                e.onNext(downloadInfo);
            }
            fileOutputStream.flush();
            downCalls.remove(url);
        } catch (Exception e1) {
            e.onError(e1);
        } finally {
            //关闭IO流
            IOUtil.closeAll(is, fileOutputStream);
        }
        e.onComplete();//完成
    }
}
