package audio_tools.com.model_demo1;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lmy.com.utilslib.bean.ModelBean;

/**
 * Created by on 2018/8/9.
 *
 * @author lmy
 */
public class ModelAdapter extends BaseQuickAdapter<ModelBean, BaseViewHolder>{
    public ModelAdapter(@Nullable List<ModelBean> data) {
        super(R.layout.model_demo_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ModelBean item) {
        helper.setText(R.id.model_item_name, item.getTitle());
    }
}
