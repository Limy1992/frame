package lmy.com.utilslib.net.down.normal;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by miya95 on 2016/12/5.
 */
public class FileSubscriber<T> implements Observer<T> {
    private FileCallBack fileCallBack;

    public FileSubscriber(FileCallBack fileCallBack) {
        this.fileCallBack = fileCallBack;
    }


    @Override
    public void onError(Throwable e) {
        if (fileCallBack != null)
            fileCallBack.onError(e);
    }

    @Override
    public void onComplete() {
        if (fileCallBack != null)
            fileCallBack.onCompleted();
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (fileCallBack != null)
            fileCallBack.onStart();
    }

    @Override
    public void onNext(T t) {
        if (fileCallBack != null)
            fileCallBack.onSuccess(t);
    }
}
