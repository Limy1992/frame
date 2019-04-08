package lmy.com.utilslib.base.more;


/**
 * 数据回调(加载更多)
 * Created by on 2018/9/11.
 *
 * @author lmy
 */
public abstract class OnLoadMoreDateListener {

    /***
     * 数据刷新
     */
    protected abstract void superRequestData();

    /**
     * 设置新数据
     */
    protected abstract void onNewData();

    /**
     * 添加数据
     */
    protected abstract void onAddData();

    /**
     * 当前页面的数据大小, 用于判断是否加载完毕
     */
    protected abstract int dataSize();

    /**
     * 第一次加载数据，就出现错误，选择是否重写处理
     */
    protected void nextErrPager() {

    }

    /**
     * 设置空View
     * @return 默认不重写， true重写 isRewSetEmptyView()方法体设置空view
     */
    protected boolean isRewSetEmptyView(){
        return false;
    }

}
