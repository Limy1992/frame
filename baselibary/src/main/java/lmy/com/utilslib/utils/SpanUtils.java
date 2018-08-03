package lmy.com.utilslib.utils;

import android.content.Intent;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenliu on 2016/8/31.<br/>
 * 描述：
 * </br>
 */
public class SpanUtils {

    public static class PatternString {
        /**
         * #号括起来的话题#
         */
        public static final String TOPIC_PATTERN = "#[^#]+#";

        /**@ T*/
        public static final String COMMENT_PATTERN = "([@][^\\s]+\\s)|([#][^#]+#)";

        /**
         * 表情[大笑]
         */
        public static final String EXPRESSION_PATTERN = "\\[[^\\]]+\\]";

        /**
         * 网址
         */
        public static final String URL_PATTERN = "(([hH]ttp[s]{0,1}|ftp)://[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)|(www.[a-zA-Z0-9\\.\\-]+\\.([a-zA-Z]{2,4})(:\\d+)?(/[a-zA-Z0-9\\.\\-~!@#$%^&*+?:_/=<>]*)?)";

    }

    /**
     * 关键词变色处理
     *
     * @param str
     * @param patterStr 需要变色的关键词 或者 正则表达式
     * @return
     */
    public static SpannableString getKeyWordSpan(int color, String str, String patterStr) throws Exception {
        SpannableString spannableString = new SpannableString(str);
        Pattern patten = Pattern.compile(patterStr, Pattern.CASE_INSENSITIVE);
        dealPattern(color, spannableString, patten, 0);
        return spannableString;
    }

    /**
     * 匹配正则符号, 字体变色并可以点击（不包含关键字）
     *
     * @param color  字体颜色
     * @param str  要处理的文本
     */
    public static SpannableString getTopicSpan(int color, String str) throws Exception {
        SpannableString spannableString = new SpannableString(str);
        Pattern patten = Pattern.compile(PatternString.COMMENT_PATTERN, Pattern.CASE_INSENSITIVE);
        dealClick(spannableString, patten, 0);
        dealPattern(color, spannableString, patten, 0);
        return spannableString;
    }

    /**
     * 匹配正则符号, 字体变色并可以点击, （带有搜索关键字）
     *
     * @param color 匹配成功后的字体颜色
     */
    public static SpannableString getTopicSpan(int color, SpannableString spannableString) throws Exception {
        Pattern patten = Pattern.compile(PatternString.COMMENT_PATTERN, Pattern.CASE_INSENSITIVE);

        dealClick(spannableString, patten, 0);

        dealPattern(color, spannableString, patten, 0);
        return spannableString;
    }

    /**
     * 对spanableString进行正则判断，如果符合要求，则将内容变色
     *
     * @param color
     * @param spannableString
     * @param patten
     * @param start
     * @throws Exception
     */
    private static void dealPattern(int color, SpannableString spannableString, Pattern patten, int start) throws Exception {
        Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            // 返回第一个字符的索引的文本匹配整个正则表达式,ture 则继续递归
            if (matcher.start() < start) {
                continue;
            }
            // 计算该内容的长度，也就是要替换的字符串的长度
            int end = matcher.start() + key.length();
            //设置前景色span
            spannableString.setSpan(new ForegroundColorSpan(color), matcher.start(), end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (end < spannableString.length() && end != 0) {
                // 如果整个字符串还未验证完，则继续。。
                dealPattern(color, spannableString, patten, end);
            }
            break;
        }
    }

    /**
     * 对spanableString进行正则判断，如果符合要求，将内容设置可点击
     *
     * @param spannableString
     * @param patten
     * @param start
     */
    private static void dealClick(SpannableString spannableString, Pattern patten, int start) {
        final String title;
        final Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            // 返回第一个字符的索引的文本匹配整个正则表达式,ture 则继续递归
            if (matcher.start() < start) {
                continue;
            }
            // 计算该内容的长度，也就是要替换的字符串的长度
            int end = matcher.start() + key.length();
            title = spannableString.subSequence(matcher.start(), end).toString();
            spannableString.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
//                    if (title.contains("@")) {
//                        //跳转主页
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("model://personalPagerActivity"));
//                        String replaceTitle = title.replace("@", "").trim();
//                        if (!replaceTitle.equals(SPUtils.getUserName(Utils.getContext()))) {
//                            intent.putExtra("selectClick", CommonManger.MY_OTHER);
//                        }else {
//                            intent.putExtra("selectClick", CommonManger.MY_CLICK);
//                        }
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("userName", replaceTitle);
//                        Utils.getContext().startActivity(intent);
//                    } else {
//                        //跳转搜索
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("model://searchActivity"));
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("searchTag", title.replace("#", ""));
//                        Utils.getContext().startActivity(intent);
//                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置画笔属性
                    ds.setUnderlineText(false);//默认有下划线
                }
            }, matcher.start(), end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (end < spannableString.length()) {
                // 如果整个字符串还未验证完，则继续。。
                dealClick(spannableString, patten, end);
            }
            break;
        }
    }
}
