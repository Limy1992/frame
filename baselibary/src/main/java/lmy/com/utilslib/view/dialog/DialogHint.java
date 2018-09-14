package lmy.com.utilslib.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lmy.com.utilslib.R;
import lmy.com.utilslib.R2;
import lmy.com.utilslib.utils.CommonManger;
import lmy.com.utilslib.utils.Utils;
import me.codeboy.android.aligntextview.AlignTextView;

/**
 * dialog提示
 * Created by on 2018/1/13.
 *
 * @author lmy
 */

public class DialogHint {
    private final Context mContext;
    @BindView(R2.id.dialog_hint_content)
    AlignTextView dialogHintContent;
    @BindView(R2.id.dialog_hint_ok)
    TextView dialogHintOk;
    @BindView(R2.id.dialog_hint_clear)
    TextView dialogHintClear;
    @BindView(R2.id.dialog_hint_check)
    RadioButton dialogHintCheck;
    private final Dialog dialog;
    private OnDialogHitCancelListener mOnDialogHitCancelListener;
    private OnDialogHitOkListener mOnDialogHitOkListener;
    private boolean checkChecked;


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

    public DialogHint setOnText(String onText){
        dialogHintOk.setText(onText);
        return this;
    }

    public void setCanable(boolean isFlag) {
        dialog.setCancelable(isFlag);
    }

    /**
     * 显示提醒不在提示选项
     */
    public DialogHint showCheckSelect(final String saveKey) {
        if (!Hawk.get(saveKey, false)) {
            dialogHintCheck.setVisibility(View.VISIBLE);
            dialogHintCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkChecked) {
                        dialogHintCheck.setChecked(false);
                    } else {
                        dialogHintCheck.setChecked(true);
                    }
                    checkChecked = dialogHintCheck.isChecked();
                    Hawk.put(saveKey, checkChecked);
                }
            });
        }
        return this;
    }


    /**
     * 设置内容
     */
    public DialogHint setContent(String contentText) {
        dialogHintContent.setText(String.format(mContext.getResources().getString(R.string.space), contentText));
        if (contentText.length() < 20) {
            dialogHintContent.setAlign(AlignTextView.Align.ALIGN_CENTER);
        }
        return this;
    }

    /**
     * 设置内容
     */
    public DialogHint setContent(SpannableStringBuilder contentText) {
        dialogHintContent.setText(String.format(mContext.getResources().getString(R.string.space), contentText));
        if (contentText.length() < 20) {
            dialogHintContent.setAlign(AlignTextView.Align.ALIGN_CENTER);
        }

        return this;
    }

    /**
     * 设置内容
     */
    public DialogHint setContent(int contentText) {
        String content = mContext.getResources().getString(contentText);
        dialogHintContent.setText(String.format(mContext.getResources().getString(R.string.space), content));
        if (content.length() < 20) {
            dialogHintContent.setAlign(AlignTextView.Align.ALIGN_CENTER);
        }
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

    public void setCancel() {
        dialogHintClear.setVisibility(View.VISIBLE);
    }

    //    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.dialog_hint_ok, R2.id.dialog_hint_clear})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.dialog_hint_ok) {
            if (mOnDialogHitOkListener != null) {
                dialog.dismiss();
                mOnDialogHitOkListener.onOk();
            }
        } else if (id == R.id.dialog_hint_clear) {
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
