package lmy.com.utilslib.net.down.sopt;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载管理
 * Created by on 2018/4/28.
 *
 * @author lmy
 */
public class DownLoadManger {

    //用来存放各个下载的请求
    private HashMap<String, Call> downCalls;
    //OKHttpClient;
    private OkHttpClient mClient;

    /**
     * 下载url
     */
    private String downUri;
    /**
     * 下载路径
     */
    private String downFilePath;
    /**
     * 下载的文件名称
     */
    private String downFileName;
    private DownLoadMangerListener mDownLoadMangerListener;


    private DownLoadManger() {
        downCalls = new HashMap<>();
        mClient = new OkHttpClient.Builder().build();
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final DownLoadManger INSTANCE = new DownLoadManger();
    }

    /**
     * 获取单例
     */
    public static DownLoadManger getInstance() {
        return DownLoadManger.SingletonHolder.INSTANCE;
    }


    public DownLoadManger setDownUrl(String downUrl) {
        this.downUri = downUrl;
        return this;
    }

    public DownLoadManger setDownFilePath(String downFilePath) {
        this.downFilePath = downFilePath;
        return this;
    }

    public DownLoadManger setDownFileName(String downFileName) {
        this.downFileName = downFileName;
        return this;
    }

    /**
     * 开始下载
     *
     * @param downLoadObserver 用来回调的接口
     */
    public void download(final DownLoadObserver downLoadObserver) {
        Observable.just(downUri)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !downCalls.containsKey(s);
                    }
                })
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {
                        return Observable.just(createDownInfo(s));
                    }
                })
                .map(new Function<Object, DownloadInfo>() {
                    @Override
                    public DownloadInfo apply(Object o) throws Exception {
                        return getRealFileName((DownloadInfo) o);
                    }
                })
                .flatMap(new Function<DownloadInfo, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(final DownloadInfo downloadInfo) throws Exception {
                        return Observable.create(new DownloadSubscribe(downloadInfo, mClient, downCalls));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(downLoadObserver);
    }

    /**
     * 取消下载
     * @param cancelUrl 取消的url
     */
    public void cancel(String cancelUrl) {
        Call call = downCalls.get(cancelUrl);
        if (call != null) {
            call.cancel();//取消
        }
        remove(cancelUrl);
    }

    /**
     * 删除下载url
     * @param downUri 下载的url
     */
    public void remove(String downUri){
        if (downCalls != null) {
            downCalls.remove(downUri);
        }
    }


    /**
     * 是否正在下载
     *
     * @return
     */
    public boolean isDown(String downUri) {
        return downCalls.get(downUri) != null;
    }

    /**
     * 创建DownInfo
     *
     * @param url 请求网址
     * @return DownInfo
     */
    private DownloadInfo createDownInfo(String url) {
        DownloadInfo downloadInfo = new DownloadInfo(url);
        long contentLength = getContentLength(url);//获得文件大小
        downloadInfo.setTotal(contentLength);
        downloadInfo.setFilePath(downFilePath);
        downloadInfo.setFileName(downFileName);
        return downloadInfo;
    }

    /**
     * 获取下载长度
     *
     * @param downloadUrl
     * @return
     */
    private long getContentLength(String downloadUrl) {
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        String filePath = downloadInfo.getFilePath();
        String fileName = downloadInfo.getFileName();
        long downloadLength = 0, contentLength = downloadInfo.getTotal();
        File file = new File(filePath, fileName);
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length();
        }else {
            file.mkdirs();
        }
        //之前下载过,需要重新来一个文件
//        int i = 1;
//        while (downloadLength >= contentLength) {
//            int dotIndex = fileName.lastIndexOf(".");
//            String fileNameOther;
//            if (dotIndex == -1) {
//                fileNameOther = fileName + "(" + i + ")";
//            } else {
//                fileNameOther = fileName.substring(0, dotIndex)
//                        + "(" + i + ")" + fileName.substring(dotIndex);
//            }
//            File newFile = new File(FileUtils.VIDEOS, fileNameOther);
//            file = newFile;
//            downloadLength = newFile.length();
//            i++;
//        }
        //设置改变过的文件名/大小
        downloadInfo.setProgress(downloadLength);
        downloadInfo.setFileName(file.getName());
        return downloadInfo;
    }




    public void setDownLoadMangerListener(DownLoadMangerListener downLoadMangerListener){
        this.mDownLoadMangerListener = downLoadMangerListener;
    }

    public interface DownLoadMangerListener{
        void onFileContentLength(long contentLength);
    }


}
