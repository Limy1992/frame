package lmy.com.utilslib.base.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import lmy.com.utilslib.utils.LogUtils;


/**
 * 跳转
 * Created by on 2018/4/18.
 *
 * @author lmy
 */
public abstract class BaseJumpFragment extends BaseHomeFragment {
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void startNextActivity(Class activity) {
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
    }

    public void startNextActivity(String activityUrl) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(activityUrl));
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }

    public void startNextActivity(Bundle bundle, Class activity) {
        Intent intent = new Intent(mContext, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void startNextActivity(Bundle bundle, String nextUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(nextUrl));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
