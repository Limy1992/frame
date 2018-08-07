package lmy.com.utilslib.base.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;


import java.lang.ref.WeakReference;

import lmy.com.utilslib.R;
import lmy.com.utilslib.net.ProgressCancelListener;
import lmy.com.utilslib.utils.LogUtils;
import lmy.com.utilslib.utils.Utils;


/**
 * 网络加载lod
 */
public class SimpleLoadDialog {

    private Dialog load;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;
    private final WeakReference<Context> reference;

    public SimpleLoadDialog(Context context, ProgressCancelListener mProgressCancelListener,
                            boolean cancelable) {
        super();
        this.reference = new WeakReference<Context>(context);
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
        createDialog();
    }

    public SimpleLoadDialog(Context context,
                            boolean cancelable) {
        super();
        this.reference = new WeakReference<Context>(context);
        this.cancelable = cancelable;
        createDialog();
    }

    public void createDialog(){
        context = reference.get();
        load = new Dialog(context, R.style.loadstyle);
        View dialogView = LayoutInflater.from(context).inflate(
                R.layout.custom_sload_layout, null);

        load.setCanceledOnTouchOutside(false);
        load.setCancelable(cancelable);
        load.setContentView(dialogView);
        load.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mProgressCancelListener != null)
                    mProgressCancelListener.onCancelProgress();
            }
        });
        Window dialogWindow = load.getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.CENTER);
            dialogWindow.setLayout(Utils.dip2px(60), Utils.dip2px(40));
        }
    }

    public void showDialog() {
        if (load != null && !((Activity)context).isFinishing()) {
            load.show();
        }
    }

    public void dismiss() {
        try {
            if (load != null && load.isShowing() && !((Activity) context).isFinishing()) {
                if (mProgressCancelListener != null) {
                    mProgressCancelListener = null;
                }
                reference.clear();
                load.cancel();
                load = null;
            }
        }catch (Exception e){
            e.printStackTrace();
            LogUtils.e("关闭弹窗异常："+e.toString());
        }
    }
}
