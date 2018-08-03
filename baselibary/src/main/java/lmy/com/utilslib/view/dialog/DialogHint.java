package lmy.com.utilslib.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lmy.com.utilslib.R;
import lmy.com.utilslib.R2;
import lmy.com.utilslib.utils.Utils;

/**
 * 通用dialog
 * Created by on 2018/1/13.
 *
 * @author lmy
 */

public class DialogHint {
    private final Context mContext;
    @BindView(R2.id.dialog_hint_content)
    TextView dialogHintContent;
    @BindView(R2.id.dialog_hint_ok)
    TextView dialogHintOk;
    @BindView(R2.id.dialog_hint_clear)
    TextView dialogHintClear;
    private final Dialog dialog;
    private OnDialogHitCancelListener mOnDialogHitCancelListener;
    private OnDialogHitOkListener mOnDialogHitOkListener;


    public DialogHint(Context context) {
        this.mContext = context;
        dialog = new Dialog(context, R.style.Dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_hint, null);
        ButterKnife.bind(this, view);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.CENTER);
            dialogWindow.setLayout(Utils.dip2px(260), ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
        }
    }

    public void setCancelable(boolean isFlag){
        dialog.setCancelable(isFlag);
    }

    /**
     * 设置内容
     */
    public DialogHint setContent(String contentText) {
        dialogHintContent.setText(contentText);
        return this;
    }

    /**
     * 设置内容
     */
    public DialogHint setContent(int contentText) {
        dialogHintContent.setText(contentText);
        return this;
    }

    /**
     * 确定
     */
    public DialogHint setOk(final OnDialogHitOkListener listener) {
        this.mOnDialogHitOkListener = listener;
        return this;
    }

    /**
     * 取消
     */
    public DialogHint setCancel(final OnDialogHitCancelListener listener) {
        this.mOnDialogHitCancelListener = listener;
        dialogHintClear.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示取消
     */
    public void setCancel(){
        dialogHintClear.setVisibility(View.VISIBLE);
    }

    /**
     * 设置取消文本
     * @param cacText 更改取消文本
     */
    public DialogHint setCancelText(String cacText){
        dialogHintClear.setText(cacText);
        return this;
    }

    /**
     * 设置确定文本
     * @param cacText 更改确定文本
     */
    public DialogHint setOnText(String cacText){
        dialogHintOk.setText(cacText);
        return this;
    }

//    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.dialog_hint_ok, R2.id.dialog_hint_clear})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.dialog_hint_ok) {
            dialog.dismiss();
            if (mOnDialogHitOkListener != null) {
                mOnDialogHitOkListener.onOk();
            }
        }else if (id == R.id.dialog_hint_clear){
            dialog.dismiss();
            if (mOnDialogHitCancelListener != null) {
                mOnDialogHitCancelListener.onCancel();
            }
        }
    }


    public interface OnDialogHitOkListener {
        void onOk();
    }


    public interface OnDialogHitCancelListener {
        void onCancel();
    }
}
