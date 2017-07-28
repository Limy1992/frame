package lmy.com.conlib.bean;

import java.util.List;

/**
 * Created by lmy on 2017/7/18
 */

public class HomeBean {

    /**
     * ads : [{"bannerUrl":"http://106.14.63.155/ilife/images/ad_zxyh.jpg","content":{"name":"我要办卡","url":"http://wx.citiccardmkt.com/app/index.php?i=9&c=entry&rid=844&do=index&m=newsupersale&wxref=mp.weixin.qq.com#wechat_redirect"},"type":"2"},{"bannerUrl":"http://106.14.63.155/ilife/images/ad_pufa.png","content":{"name":"浦发银行","url":"http://106.14.63.155:8080/ilife_rules/pudong_development_bank.html"},"type":"2"},{"bannerUrl":"http://106.14.63.155/ilife/images/ad_coffee.jpg","content":{"name":"咖啡ceshi","url":""},"type":"0"},{"bannerUrl":"http://106.14.63.155/ilife/images/ad_huipu.png","content":{"height":1334,"name":"中民惠普","url":"http://106.14.63.155/ilife/images/ad_huipuDetail.png","width":750},"type":"1"},{"bannerUrl":"http://106.14.63.155/ilife/images/ad_shangshe.jpg","content":{"height":419,"name":"尚奢工坊","url":"http://106.14.63.155/ilife/images/ads_start.jpg","width":235},"type":"1"}]
     * advertUrl : http://m.cmiinv.com/product/registerVip.jsp?id=141
     * android : 1.3.0
     * homeList : [{"iconName":"京东商城","iconType":"7","iconUrl":"http://106.14.63.155/ilife/images/icon/jingDongicon@2x.png","isConstruction":"1"},{"iconName":"饿了么","iconType":"8","iconUrl":"https://www.i-fully.cn/ilife/images/activity/shangshe_fuwugongyue.png","isConstruction":"1"},{"iconName":"话费充值","iconType":"9","iconUrl":"https://www.i-fully.cn/ilife/images/activity/shangshe_fuwugongyue.png","isConstruction":"1"},{"iconName":"健康","iconType":"2","iconUrl":"https://www.i-fully.cn/ilife/images/icon_hospital.png","isConstruction":"1"},{"iconName":"卡券","iconType":"1","iconUrl":"https://www.i-fully.cn/ilife/images/icon_card.png","isConstruction":"1"},{"iconName":"保险","iconType":"3","iconUrl":"https://www.i-fully.cn/ilife/images/icon_insurance.png","isConstruction":"1"},{"iconName":"考勤休假","iconType":"5","iconUrl":"https://www.i-fully.cn/ilife/images/icon_kaoqin.png","isConstruction":"1"},{"iconName":"尚奢","iconType":"6","iconUrl":"https://www.i-fully.cn/ilife/images/icon_shangshe.png","isConstruction":"1"},{"iconName":"狂欢节","iconType":"4","iconUrl":"http://106.14.63.155/ilife/images/icon_duanwu.png","isConstruction":"1"}]
     * ios : 1.3.0
     * rcode : 0
     */

    public String advertUrl;
    public String android;
    public String ios;
    public int rcode;
    public List<AdsBean> ads;
    public List<HomeListBean> homeList;

    public static class AdsBean {
        /**
         * bannerUrl : http://106.14.63.155/ilife/images/ad_zxyh.jpg
         * content : {"name":"我要办卡","url":"http://wx.citiccardmkt.com/app/index.php?i=9&c=entry&rid=844&do=index&m=newsupersale&wxref=mp.weixin.qq.com#wechat_redirect"}
         * type : 2
         */

        public String bannerUrl;
        public ContentBean content;
        public String type;

        public static class ContentBean {
            /**
             * name : 我要办卡
             * url : http://wx.citiccardmkt.com/app/index.php?i=9&c=entry&rid=844&do=index&m=newsupersale&wxref=mp.weixin.qq.com#wechat_redirect
             */

            public String name;
            public String url;
        }
    }

    public static class HomeListBean {
        /**
         * iconName : 京东商城
         * iconType : 7
         * iconUrl : http://106.14.63.155/ilife/images/icon/jingDongicon@2x.png
         * isConstruction : 1
         */

        public String iconName;
        public String iconType;
        public String iconUrl;
        public String isConstruction;
    }
}
