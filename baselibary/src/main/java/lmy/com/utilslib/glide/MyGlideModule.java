package lmy.com.utilslib.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

import lmy.com.utilslib.glide.config.GlideCatchConfig;

/**
 * 配置glide图片加载格式
 * Created by lmy on 2017/7/18
 */

public class MyGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        //自定义缓存目录
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,
                GlideCatchConfig.GLIDE_CARCH_DIR,
                GlideCatchConfig.GLIDE_CATCH_SIZE));
        builder.setDefaultRequestOptions(new RequestOptions()
                .placeholder(com.base_src.R.drawable.bg_zan)
                .error(com.base_src.R.drawable.bg_zan));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    }
}
