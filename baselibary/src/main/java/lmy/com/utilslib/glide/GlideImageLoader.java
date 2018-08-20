package lmy.com.utilslib.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

/**
 * banner设置加载方式
 *
 * @author lmy
 *         Created by 2017/11/29
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(path)
                    .thumbnail(1.0f)
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
