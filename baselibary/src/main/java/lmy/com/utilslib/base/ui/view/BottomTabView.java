package lmy.com.utilslib.base.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import lmy.com.utilslib.R;
import lmy.com.utilslib.utils.LogUtils;


/**
 * 底部button
 */

public class BottomTabView extends LinearLayout {

    /**
     * 默认选中位置
     */
    public static final int SELECT_POSITION = 2;

    /**
     * 记录最新的选择位置
     */
    private int lastPosition = -1;

    /**
     * 所有 TabItem 的集合
     */
    private List<TabItemView> tabItemViews;
    private OnViewPagerListener mOnViewPagerListener;
    private OnTabItemSelectListener onTabItemSelectListener;
//    private OnSecondSelectListener onSecondSelectListener;

    public BottomTabView(Context context) {
        this(context, null);
    }

    public BottomTabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BottomTabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 连接 Viewpager
     *
     * @param viewPager
     */
    public void setUpWithViewPager(final ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.d("bottomView Pager");
                if (mOnViewPagerListener != null) {
                    mOnViewPagerListener.onPosition(position);
                }
                updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setOnTabItemSelectListener(new OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                viewPager.setCurrentItem(position, false);
            }
        });
    }

    /**
     * 设置 Tab Item View
     */
    public void setTabItemViews(List<TabItemView> tabItemViews) {
        setTabItemViews(tabItemViews, null);
    }

    /**
     * 设置 Tab Item View
     */
    public void setTabItemViews(List<TabItemView> tabItemViews, View centerView) {

        if (this.tabItemViews != null) {
            throw new RuntimeException("不能重复设置！");
        }

        if (tabItemViews == null || tabItemViews.size() < 2) {
            throw new RuntimeException("TabItemView 的数量必须大于2！");
        }

        this.tabItemViews = tabItemViews;
        for (int i = 0; i < tabItemViews.size(); i++) {

            if (centerView != null && i == tabItemViews.size() / 2) {
                this.addView(centerView);
            }

            final TabItemView tabItemView = tabItemViews.get(i);

            this.addView(tabItemView);

            final int finalI = i;

            tabItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == lastPosition) {
                        // 第二次点击
                        return;
                    }

                    //更新选中状态
                    updatePosition(finalI);
                }
            });
        }

        /**
         * 将所有的 TabItem 设置为 初始化状态
         */
        for (TabItemView tab : tabItemViews) {
            tab.setStatus(TabItemView.DEFAULT);
        }

        /**
         * 默认状态选择第一个
         */
        updatePosition(SELECT_POSITION);
    }

    /**
     * 更新被选中 Tab Item 的状态
     * 恢复上一个 Tab Item 的状态
     */
    public void updatePosition(int position) {

        if (lastPosition != position) {
            if (tabItemViews != null && tabItemViews.size() != 0) {
                tabItemViews.get(position).setStatus(TabItemView.PRESS);
                if (lastPosition != -1) {
                    tabItemViews.get(lastPosition).setStatus(TabItemView.DEFAULT);
                }
                if (onTabItemSelectListener != null) {
                    //切换页面
                    onTabItemSelectListener.onTabItemSelect(position);
                }
                lastPosition = position;
            } else {
                throw new RuntimeException("please setTabItemViews !");
            }
        }
    }

    /**
     * 是否显示小红点
     *
     * @param isFlag true显示
     */
    public void isShowRed(boolean isFlag) {
        //获取第几个icon显示小红点
        tabItemViews.get(0).setRedIsShow(isFlag);
    }

    /**
     * 根据要求动态改变首页的icon
     */
    public void setChangeImage(int pagerPosition,int imageNo, int imageSe) {
        TabItemView tabItemView = tabItemViews.get(pagerPosition);
        tabItemView.changeImage(imageNo, imageSe);
        if (lastPosition == pagerPosition) {
            //当前页面选中
            tabItemView.setStatus(TabItemView.PRESS);
        }else {
            tabItemView.setStatus(TabItemView.DEFAULT);
        }
    }


    public void setOnTabItemSelectListener(OnTabItemSelectListener onTabItemSelectListener) {
        this.onTabItemSelectListener = onTabItemSelectListener;
    }


    /**
     * viewPager滑动监听
     */
    public void setOnViewPagerListener(OnViewPagerListener onViewPagerListener) {
        this.mOnViewPagerListener = onViewPagerListener;
    }

    /**
     * 第二次被选择的监听器
     */
    public interface OnSecondSelectListener {
        void onSecondSelect(int position);
    }

    /**
     * 第一次被选择的监听器
     */
    public interface OnTabItemSelectListener {
        void onTabItemSelect(int position);
    }

    //viewPager监听索引回调
    public interface OnViewPagerListener {
        void onPosition(int position);
    }

    /**
     * Item
     */
    public static class TabItemView extends LinearLayout {

        /**
         * 两个状态 选中、未选中
         */
        public final static int PRESS = 1;
        public final static int DEFAULT = 2;

        /**
         * Item 的标题
         */
        public String title;

        /**
         * 标题的两个状态的颜色 选中、未选中
         */
        public int colorDef;
        public int colorPress;

        /**
         * 两个图标的 资源 id ，选中、未选中
         */
        public int iconResDef;
        public int iconResPress;

        public TextView tvTitle;
        public TextView buttonRed;
        public ImageView ivIcon;

        public TabItemView(Context context, String title, int colorDef, int colorPress,
                           int iconResDef, int iconResPress) {
            super(context);
            this.title = title;
            this.colorDef = colorDef;
            this.colorPress = colorPress;
            this.iconResDef = iconResDef;
            this.iconResPress = iconResPress;
            init();
        }

        /**
         * 初始化
         */
        public void init() {
            View view = LayoutInflater.from(super.getContext()).inflate(R.layout.view_tab_item, this);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            buttonRed = (TextView) view.findViewById(R.id.button_red);
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);

            tvTitle.setText(title);

        }

        /**
         * 设置状态
         */
        public void setStatus(int status) {
            tvTitle.setTextColor(ContextCompat.getColor(super.getContext(), status == PRESS ? colorPress : colorDef));
            ivIcon.setImageResource(status == PRESS ? iconResPress : iconResDef);
        }

        /**
         * 小红点是否显示
         */
        public void setRedIsShow(boolean isShow) {
            if (isShow) {
                buttonRed.setVisibility(VISIBLE);
            } else {
                buttonRed.setVisibility(GONE);
            }
        }

        /**
         * 动态改变图片
         */
        public void changeImage(int imageNo, int imageSe) {
            iconResDef = imageNo;
            iconResPress = imageSe;
        }
    }
}
