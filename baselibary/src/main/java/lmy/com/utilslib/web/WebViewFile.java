package lmy.com.utilslib.web;

import android.content.Intent;
import android.net.Uri;

import com.tencent.smtt.sdk.ValueCallback;

/**
 * 配置webView File
 * Created by lmy on 2017/7/31
 */

public class WebViewFile extends WebViewVideo {
    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;

    @Override
    protected void filePathCallBacks(ValueCallback<Uri[]> filePathCallback) {
        WebViewFile.this.uploadFiles = filePathCallback;
        openFileChooseProcess();
    }

    private void openFileChooseProcess() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "test"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null ? null
                                : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (null != uploadFiles) {
                        Uri result = data == null ? null
                                : data.getData();
                        uploadFiles.onReceiveValue(new Uri[]{result});
                        uploadFiles = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }

        }
    }
}
