package lmy.com.utilslib.base.ui.activity;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;

import lmy.com.utilslib.R;

/**
 * titleBar标题
 * Created by lmy on 2017/8/10
 * @author lmy
 */

public class BaseTitleBarActivity extends TopBarBaseActivity {
    private OnClickListener onClickListenerTopLeft;
    private OnClickListener onClickListenerTopRight;
    private int menuResId;
    private String menuStr;
    public Menu rightMenu;
    /**改变右标题颜色*/
    private int rightColor;

    /**
     * 设置中间title
     *
     * @param title title
     */
    protected void setTitleText(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }

    /**
     * 设置中间title
     * @param title title
     */
    protected void setTitleText(int title){
        if (title != 0) {
            tvTitle.setText(title);
        }
    }

    /**
     * 可以更换左上角默认显示图片
     *
     * @param iconResId 资源id
     */
    public void setReplaceTopLeftButton(int iconResId) {
        setTopLeftButton(iconResId, null);
    }

    /**
     * 可以更换左上角默认显示图片
     *
     * @param iconResId       资源id
     * @param onClickListener 点击事件监听
     */
    public void setReplaceTopLeftButton(int iconResId, OnClickListener onClickListener) {
        setTopLeftButton(iconResId, onClickListener);
    }

    protected void setTopLeftButton(int iconResId, OnClickListener onClickListener) {
        toolbar.setNavigationIcon(iconResId);
        this.onClickListenerTopLeft = onClickListener;
    }

    /**
     * title右边内容
     *
     * @param menuStr         内容
     * @param onClickListener 点击监听
     */
    protected void setTopRightButton(String menuStr, OnClickListener onClickListener) {
        this.onClickListenerTopRight = onClickListener;
        this.menuStr = menuStr;
    }

    /**
     * title右边内容
     *
     * @param menuStr         内容
     * @param onClickListener 点击监听
     */
    protected void setTopRightButton(String menuStr, OnClickListener onClickListener, int rightColor) {
        this.onClickListenerTopRight = onClickListener;
        this.rightColor = rightColor;
        this.menuStr = menuStr;
    }

    /***
     * 更改右边内容
     * @param menuStr 内容

     */
    protected void setTopRightButton(String menuStr) {
        this.menuStr = menuStr;
    }

    /**
     * title右边内容
     *
     * @param menuResId       资源id
     * @param onClickListener 事件点击
     */
    protected void setTopRightButton(int menuResId, OnClickListener onClickListener) {
        this.menuResId = menuResId;
        this.onClickListenerTopRight = onClickListener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuResId != 0 || !TextUtils.isEmpty(menuStr)) {
            getMenuInflater().inflate(R.menu.menu_activity_base_top_bar, menu);
        }
        return true;
    }

    //动态修改内容。右边菜单
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        rightMenu = menu;
        if (menuResId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuResId);
        }
        if (!TextUtils.isEmpty(menuStr)) {
            menu.findItem(R.id.menu_1).setTitle(menuStr);

            if (rightColor != 0) {
                setMenuTitleColor();
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (onClickListenerTopLeft != null) {
                onClickListenerTopLeft.onClick();
            } else {
                finish();       //不设置onClickListenerTopLeft监听，默认直接finish();
            }
        } else if (item.getItemId() == R.id.menu_1) {
            onClickListenerTopRight.onClick();
        }

        return true; // true 告诉系统我们自己处理了点击事件
    }

    /**
     * 改变右标题颜色
     */
    public void setMenuTitleColor(){
        MenuItem rItem = rightMenu.findItem(R.id.menu_1);
        CharSequence rightTitle = rItem.getTitle();
        SpannableString spannableString = new SpannableString(rightTitle);
        spannableString.setSpan(new ForegroundColorSpan(rightColor), 0, spannableString.length(), 0);
        rItem.setTitle(spannableString);
    }

   public interface OnClickListener {
        void onClick();
    }

}
