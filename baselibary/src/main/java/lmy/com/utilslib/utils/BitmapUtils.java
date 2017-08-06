package lmy.com.utilslib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片采样压缩
 * Created by LMY on 2017/8/6.
 */

public class BitmapUtils {
    /**
     * 采样图片压缩
     *
     * @param filePath  本地照片路径
     * @param reqWidth  所需大小 宽
     * @param reqHeight 所需大小 高
     * @return 采样后的bitmap
     */
    public static Bitmap bitmapOptions(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reWidth, int reHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reHeight || width > reWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reHeight && (halfWidth / inSampleSize) >= reWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;

    }
}
