package lmy.com.utilslib.utils;

import java.text.SimpleDateFormat;

/**
 * Created by cc on 2016/5/13.
 */
public class MusicTimeUtils {


    private static final int HOUR = 60*60*1000;
    private static final int MIN = 60*1000;
    private static final int SEC = 1000;
    /**
     * 返回一个00：00 或00：00：00格式的字符串
     * @param duration
     * @return
     */
    public static String formatDuration(int duration) {

        //小时数
        int hour = duration / HOUR;
        //分钟数
        int min = duration % HOUR / MIN;
        //秒数
        int sec = duration % MIN / SEC;

        return hour==0? String.format("%02d:%02d",min,sec):String.format("%02d:%02d:%02d",hour,min,sec);
    }

    /**
     * 返回一个24小时制的时间
     * @param currentTimeMillis
     */
    public static String formatTime(long currentTimeMillis) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        return format.format(currentTimeMillis);
    }
}
