package lmy.com.utilslib.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 空列表
 * Created by on 2018/5/25.
 *
 * @author lmy
 */
public class EmptyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public EmptyAdapter(@Nullable List<String> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
