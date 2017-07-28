package com.lmy.audio.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by lmy on 2017/7/13
 */

public abstract class CommonDialog extends Dialog {
    public CommonDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);

        View view = LayoutInflater.from(context).inflate(getDialogView(), null);
        setContentView(view);
        initView(context, view);
    }

    private void initView(Context context, View view) {

    }

    protected abstract int getDialogView();
}
