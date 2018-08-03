package lmy.com.utilslib.bean;

/**
 * retrofit网络框架请求 java后端返回的固定json格式
 * Created by lmy on 2017/7/11
 *
 * @author lmy
 */

public class BaseHttpResult<T> {
    public String msg;
    public int code;
    public T content;
}
