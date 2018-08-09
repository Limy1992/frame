package lmy.com.utilslib.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import butterknife.ButterKnife;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.Utils;

/**
 * dialog
 * Created by on 2018/8/7.
 *
 * @author lmy
 */
public abstract class CommonDialog extends Dialog {
    public CommonDialog(@NonNull Context context) {
        super(context);
        initDialog(context);
    }

    private void initDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(dialogViewLayout(), null);
        ButterKnife.bind(this, view);
        setContentView(view);
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.CENTER);
//            dialogWindow.setLayout(dialogWidth() == 0 ? ViewGroup.LayoutParams.MATCH_PARENT :Utils.dip2px(260)
//                    ,dialogHeight() == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT);
            show();
        }
    }

    /**设置view*/
    public abstract int dialogViewLayout();

    /**设置宽度 默认全屏*/
    public int dialogWidth(){
        return 0;
    }

    /**设置宽度*/
    public int dialogHeight(){
        return 0;
    }

}
