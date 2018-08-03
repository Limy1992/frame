package lmy.com.utilslib.net.down.normal;

import android.util.Log;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import lmy.com.utilslib.net.rx.RxBus;
import lmy.com.utilslib.utils.LogUtils;
import okhttp3.ResponseBody;

/**
 * 普通下载
 * Created by miya95 on 2016/12/5.
 */
public abstract class FileCallBack<T> {

    private String destFileDir;
    private String destFileName;

    public FileCallBack(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        subscribeLoadProgress();
    }

    public abstract void onSuccess(T t);

    public abstract void progress(long progress, long total);

    public void onStart() {
    }

    public void onCompleted() {
    }

    public void onError(Throwable e) {
    }

    public void saveFile(ResponseBody body) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = body.byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            unsubscribe();
            //onCompleted();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                Log.e("saveFile", e.getMessage());
            }
        }
    }

    /**
     * 订阅加载的进度条
     */
    public void subscribeLoadProgress() {
        Disposable disposable = RxBus.getInstanceBus()
                .doSubscribe(FileLoadEvent.class, new Consumer<FileLoadEvent>() {
                    @Override
                    public void accept(FileLoadEvent fileLoadEvent) throws Exception {
                        progress(fileLoadEvent.getBytesLoaded(), fileLoadEvent.getTotal());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.e("throwable=" + throwable.getMessage());
                    }
                });
        RxBus.getInstanceBus().addSubscription(this, disposable);

    }

    /**
     * 取消订阅，防止内存泄漏
     */
    public void unsubscribe() {
        RxBus.getInstanceBus().unSubscribe(this);
    }
}
