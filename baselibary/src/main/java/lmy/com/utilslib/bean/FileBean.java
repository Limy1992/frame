package lmy.com.utilslib.bean;

/**
 * pdf
 * Created by on 2018/5/12.
 *
 * @author lmy
 */
public class FileBean {
    public final String fileName;
    /** 文件的路径*/
    public String path;
    /**文件图片资源的id，drawable或mipmap文件中已经存放doc、xml、xls等文件的图片*/
//    public int iconId;

    public long size;
    public String date;

    public FileBean(String path, String fileName, long size, String date) {
        this.path = path;
        this.fileName = fileName;
        this.size = size;
        this.date = date;
    }
}
