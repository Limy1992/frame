package lmy.com.utilslib.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lmy.com.utilslib.bean.FileBean;

/**
 * 文件工具
 * Created by on 2018/1/17.
 *
 * @author lmy
 */

public class FileUtils {

    /**pdf下载*/
    private static final String DOC_PDF = Utils.getContext().getFilesDir().getPath();
    //pdf下载文件夹
    public static final String DOC = DOC_PDF + "/other";


    private static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static final String SD_ILIFE = SD_PATH + "/yimicaiqWealth";
    public static final String PHOTO = SD_ILIFE + "/cutImage";

    private static final String[] PATHS = {SD_ILIFE,  PHOTO, DOC};


    /***
     * 创建文件夹
     * @return
     */
    public static boolean file() {
        boolean finebo = false;
        for (String path : PATHS) {
            File file = new File(path);
            if (!file.exists()) {
                finebo = file.mkdir();
            }
            file.mkdirs();
        }
        return finebo;
    }

    /**
     * 删除指定文件
     */
    public static boolean delete(String path,String fileName) {
        try {
            return new File(path, fileName).delete();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断一个文件
     *
     * @param fileCa   扫描地址
     * @param filePath 文件路径
     */
    public static boolean fileIsExi(String fileCa, String filePath) {
        File file = new File(fileCa);
        File[] listFiles = file.listFiles();
        for (File musicFiles : listFiles) {
            if (musicFiles.getPath().equals(filePath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取手机内部空间总大小
     *
     * @return 大小，字节为单位
     */
    public static long getTotalInternalMemorySize() {
        //获取内部存储根目录
        File path = Environment.getDataDirectory();
        //系统的空间描述类
        StatFs stat = new StatFs(path.getPath());
        //每个区块占字节数
        long blockSize = stat.getBlockSize();
        //区块总数
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 获取手机内部可用空间大小
     *
     * @return 大小，字节为单位
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        //获取可用区块数量
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取可用空间的百分比
     *
     * @return 可用空间的百分比
     */
    public static float getTotalAvailable() {
        return getAvailableInternalMemorySize() / (float) getTotalInternalMemorySize() * 100;
    }


    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 通知系统保存
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    public static void systemSaveImage(String filePath, String fileName){
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath + "/" + fileName);
            MediaStore.Images.Media.insertImage(Utils.getContext().getContentResolver(), bitmap, fileName, null);
            Utils.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + filePath + "/" + fileName)));
            LogUtils.e("保存成功" + bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("保存分享图片=" + e.toString());
        }
    }

    /**
     * 扫描PDF 和doc文档
     * @param fileType pdf
     * @return doc
     */
    public static List<FileBean> getFilesByType(String fileType) {
        List<FileBean> files = new ArrayList<>();
        // 扫描files文件库
        Cursor c = null;
        try {
            ContentResolver mContentResolver = Utils.getContext().getContentResolver();
            c = mContentResolver.query(MediaStore.Files.getContentUri("external"), new String[]{"_id", "_data", "_size"}, null, null, null);
            if (c == null) {
                return new ArrayList<>();
            }
            int dataIndex = c.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            int sizeIndex = c.getColumnIndex(MediaStore.Files.FileColumns.SIZE);
            int dateIndex = c.getColumnIndex(MediaStore.Files.FileColumns.DATE_ADDED);

            while (c.moveToNext()) {
                String path = c.getString(dataIndex);
                LogUtils.d("path="+path);
                if (path.endsWith(fileType)) {
                    long size = c.getLong(sizeIndex);
//                    long dateLong = c.getLong(dateIndex);
                    String fileName = path.substring(path.lastIndexOf("/") + 1);
                    files.add(new FileBean(path, fileName,size, ""));
                    LogUtils.el("path="+path);
                    LogUtils.el("size="+size);
//                    LogUtils.el("dateLong="+dateLong);
                    LogUtils.el("path="+path);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return files;
    }
}
