package lmy.com.utilslib.net.down.sopt;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by on 2018/4/28.
 *
 * @author lmy
 */
public class IOUtil {
    public static void closeAll(Closeable... closeables){
        if(closeables == null){
            return;
        }
        for (Closeable closeable : closeables) {
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
