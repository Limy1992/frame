package lmy.com.utilslib.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * Created by LMY on 2018/1/21.
 */

public class Executors {
    public static ExecutorService newCachedThreadPool(){
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit. SECONDS, new SynchronousQueue<Runnable>());
    }
}
