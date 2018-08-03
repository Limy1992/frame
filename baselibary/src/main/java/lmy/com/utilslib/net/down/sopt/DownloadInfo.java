package lmy.com.utilslib.net.down.sopt;

/**
 * 下载模型
 * Created by on 2018/4/28.
 *
 * @author lmy
 */
public class DownloadInfo {
    public static final long TOTAL_ERROR = -1;//获取进度失败
    /**下载url*/
    private String url;
    /**下载的最大值*/
    private long total;
    /**当前下载位置*/
    private long progress;
    /**下载后存储的名字*/
    private String fileName;
    /**下载后存储的路径*/
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public DownloadInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }
}
