package lmy.com.utilslib.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * 管理打开的activity
 * Created by on 2018/3/2.
 *
 * @author lmy
 */

public class ActivityManger {
    private volatile ActivityManger activityManger;
    private Stack<Activity> activitySet = new Stack<>();
    private ActivityManger(){}

    public ActivityManger getInstance(){
        if (activityManger == null) {
            synchronized (ActivityManger.class){
                if (activityManger == null) {
                    activityManger = new ActivityManger();
                }
            }
        }
        return activityManger;
    }

    public void addActivity(Activity activity){
        activitySet.add(activity);
    }

    public void removeActivity(Activity activity){
        activitySet.remove(activity);
    }

}
