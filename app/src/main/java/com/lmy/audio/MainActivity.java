package com.lmy.audio;


import com.github.mzule.activityrouter.annotation.Router;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;


import io.reactivex.functions.Consumer;

/**
 * 提示权限操作, 和其他操作
 */
@Router("mainActivity")
public class MainActivity extends BaseMainActivity {
    @Override
    protected void permissionsPrompt() {
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ,android.Manifest.permission.CALL_PHONE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {

                    }
                });
    }

    @Override
    protected void initOr() {
       //其他操作
    }

}
