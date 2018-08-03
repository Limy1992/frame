package lmy.com.utilslib.base.ui.fragment;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.ToastUtils;

import static lmy.com.utilslib.utils.CommonManger.REQUEST_CODE_CHOOSE;

/**
 *  Fragment基类
 * Created by lmy on 2017/7/25
 */

public abstract class BaseHomeFragment extends RxFragment {
    private Unbinder bind;
    /**false解除控件绑定*/
    public boolean isBindUn;
    public View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentView(), null);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getFragmentView();


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("tag", "lifecycleSubject");
    }

    @Override
    public void onDestroyView() {
        if (!isBindUn) {
            bind.unbind();
        }
        super.onDestroyView();
    }

    /**
     * 动态权限管理sd卡操作
     */
    @SuppressLint("CheckResult")
    public void startAlbum(final String fileType) {
        final RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.requestEach(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            //允许
                            startAlbumAndCamera(fileType); //带有拍照
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //拒绝
                            ToastUtils.showShortToast("拒绝权限");
                        } else {
                            //不在提示
                            ToastUtils.showShortToast("不在提示权限");
                        }
                    }
                });
    }



    /**
     * 开启知乎三方库相册选择器
     * @param fileType
     */
    public void startAlbumAndCamera(String fileType) {
        Set<MimeType> mimeTypes ;

        if (fileType.equals("VIDEO")) {
            mimeTypes = MimeType.ofVideo();
        }else {
            mimeTypes = MimeType.ofImage();
        }

        startPhoto(mimeTypes);
    }


    private void startPhoto(Set<MimeType> mimeTypes) {
        Matisse.from(this)
                //选着类型
                .choose(mimeTypes, false)
                .theme(R.style.Matisse_Dracula)
                //数字叠加
                .countable(true)
                //开启相机
                .capture(true)
                //共享路径
                .captureStrategy(new CaptureStrategy(true, "com.bluedancer.bluedancer.fileProvider"))
                //最大多少张
                .maxSelectable(1)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                //网络大小
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                //Glide加载
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);
    }
}
