package lmy.com.utilslib.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import lmy.com.utilslib.utils.ToastUtils;
import lmy.com.utilslib.utils.Utils;

/**
 * Created by mingquan on 2017/5/4.
 * 网络判断
 */

public class NetState {

    public final static int CONNECTION_NO = 0; //无网络连接
    public static final int CONNECTION_HOME = 1;//内网中wifi
    public final static int CONNECTION_OUTSIDE = 2;//外网中wifi或使用移动数据

    //检查网络状态
    public static int checkState(Context context) {
        int state = CONNECTION_NO;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//                  wifiInfo.getBSSID().equals(wifiMac);
                    state = CONNECTION_HOME;
                } else {
                    state = CONNECTION_OUTSIDE;
                }
            }
        }
        return state;
    }


    /**
     * 判断是否有网络
     */
    @SuppressWarnings("deprecation")
    public static boolean IfNet() {
        switch (NetState.checkState(Utils.getContext())) {
            case NetState.CONNECTION_NO:
                ToastUtils.showShortToast("网络断了哦,检查一下您的网络吧");
                return true;
            default:
                break;
        }
        return false;
    }
    /*
    * 是否为wifi
    * */

    public static Boolean getLocalMacAddress(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkINfo = cm.getActiveNetworkInfo();
            if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
//            ToastUtils.showShortToast("wifi已连接");
                return true;
            }
        }
        return false;
    }

    /*
    * 是否为数据流量
    * */
    public static boolean isMobile(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkINfo = cm.getActiveNetworkInfo();
            if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                ToastUtils.showShortToast("当前网为数据流量，请注意您的流量哦");
                return true;
            }
        }
        return false;
    }


    /**
     * 判断是wifi还是3g网络
     *
     * @param context
     * @return
     */
    /*public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            ToastUtils.showShortToast("wifi已连接");
            return true;
        } else if (networkINfo != null && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            ToastUtils.showShortToast("当前网为3g，4g 注意您的流量哦");
        } else {
            ToastUtils.showShortToast("网络断了哦,检查一下您的网络吧");
        }
        return false;
    }*/
}
