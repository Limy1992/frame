package lmy.com.utilslib.utils.media;

import android.media.MediaMetadataRetriever;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import lmy.com.utilslib.utils.LogUtils;

/**
 * Created by on 2018/1/23.
 *
 * @author lmy
 */

public class MediaUtils {
    public static final int REVERSE_LENGTH = 102;

    /**
     * 获取视频尺寸
     *
     * @param videoPath 视频路径
     */
    public static String getVideoSize(String videoPath) {
        MediaMetadataRetriever retr = new MediaMetadataRetriever();
        retr.setDataSource(videoPath);
        // 视频高度
        String height = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
        // 视频宽度
        String width = retr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
        return width + "*" + height;
    }

    /**
     * 亦或字节加密
     *
     * @param strFile 文件绝对路径
     */
    public static boolean encrypt(String strFile, String fileName) {
        long startTime = System.currentTimeMillis();
        int len = REVERSE_LENGTH;
        try {
            File f = new File(strFile, fileName);
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            long totalLen = raf.length();

            if (totalLen < REVERSE_LENGTH)
                len = (int) totalLen;

            FileChannel channel = raf.getChannel();
            MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_WRITE, 0, REVERSE_LENGTH);
            byte tmp;
            for (int i = 0; i < len; ++i) {
                byte rawByte = buffer.get(i);
                tmp = (byte) (rawByte ^ i);
                buffer.put(i, tmp);
            }
            buffer.force();
            buffer.clear();
            channel.close();
            raf.close();
            long endTime = System.currentTimeMillis() - startTime;
            LogUtils.d("解密加密耗时：=" + endTime);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
