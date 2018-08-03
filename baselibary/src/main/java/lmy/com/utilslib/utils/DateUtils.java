package lmy.com.utilslib.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by on 2018/3/27.
 *
 * @author lmy
 */

public class DateUtils {
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     */
    //时间戳转字符串
    public static String getStrTime(long timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转字符串
    public static String getStrDate(long timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转字符串
    public static String timeToDate(long timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    /*
     * 将秒数转为*天*小时*分*秒返回*/

    public static String formatDateTime(long mss) {
        DecimalFormat df = new DecimalFormat("00");//不足10 ，补0
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (days > 0) {//时分秒
            DateTimes = df.format(days) + ":" + df.format(hours) + ":" + df.format(minutes) + ":"
                    + df.format(seconds) + "";
        } else if (hours > 0) {//时分秒
            DateTimes = df.format(hours) + ":" + df.format(minutes) + ":"
                    + df.format(seconds) + "";
        } else if (minutes > 0) {//分秒
            DateTimes = df.format(minutes) + ":"
                    + df.format(seconds) + "";
        } else {//秒
            DateTimes = df.format(seconds) + "";
        }

        return DateTimes;
    }

    /**
     * 获取当前的时间戳
     */

    public String getTime() {
        String str = String.valueOf(new Date().getTime());
        return str;
    }

    /**
     * @param time 时间戳
     * @return 小时
     */
    public static String getOnlyHours(long time) {
        long second = time / 1000;
        long hour = second / 60 / 60;
        String rHour = "";
        // 时
        if (time > 0 && hour == 0) {
            rHour = (hour + 1) + "";
        } else {
            rHour = hour + "";
        }

//         return hour + "小时" + minute + "分钟" + sec + "秒";
        return rHour + "小时";

    }

    // 将字符串转为时间戳
    public static String strToTime(String user_time) {

        String str = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            str = String.valueOf(l);


        } catch (ParseException e) {
            e.printStackTrace();
            LogUtils.e("e=" + e.toString());
        }
        return str;
    }

    //资讯时间显示
    public static String showData(long curDate) {
        long delta = (new Date().getTime() - curDate) / 1000;
        if (delta / (60 * 60 * 24 * 365) > 0) return delta / (60 * 60 * 24 * 365) + "年前";
        if (delta / (60 * 60 * 24 * 30) > 0) return delta / (60 * 60 * 24 * 30) + "个月前";
        if (delta / (60 * 60 * 24 * 7) > 0) return delta / (60 * 60 * 24 * 7) + "周前";
        if (delta / (60 * 60 * 24) > 0) return delta / (60 * 60 * 24) + "天前";
        if (delta / (60 * 60) > 0) return delta / (60 * 60) + "小时前";
        if (delta / (60) > 0) return delta / (60) + "分钟前";
        return "刚刚";
    }

    public static String formatTime(long ms) {

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; //天
        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒

        LogUtils.d("milliSecond=" + milliSecond);

        strMilliSecond = milliSecond < 100 ? "00:" + strMilliSecond : "" + strMilliSecond;

        return strMilliSecond;
    }
}
