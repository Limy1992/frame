package lmy.com.utilslib.net.down.normal;

/**
 * 文件加载事件
 */
public class FileLoadEvent {

   private long total;
    private long bytesLoaded;

    public long getBytesLoaded() {
        return bytesLoaded;
    }

    public long getTotal() {
        return total;
    }

    /**
     * 文件加载事件的构造函数.
     * @param total 文件总大小
     * @param bytesLoaded 已加载文件的大小
     */
    public FileLoadEvent(long total, long bytesLoaded) {
        this.total = total;
        this.bytesLoaded = bytesLoaded;
    }
}
