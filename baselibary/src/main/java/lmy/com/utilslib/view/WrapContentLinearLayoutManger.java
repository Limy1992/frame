package lmy.com.utilslib.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import lmy.com.utilslib.utils.LogUtils;

/**
 * 解决recycleView  Invalid item position 2(offset:3).state:3问题
 * Created by on 2018/5/7.
 *
 * @author lmy
 */
public class WrapContentLinearLayoutManger extends LinearLayoutManager {
    public WrapContentLinearLayoutManger(Context context) {
        super(context);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        }catch (IndexOutOfBoundsException e){
            LogUtils.e("IndexOutOfBoundsException="+e.toString());
        }catch (IllegalArgumentException i){
            LogUtils.e("IllegalArgumentException="+i.toString());
        }
    }
}
