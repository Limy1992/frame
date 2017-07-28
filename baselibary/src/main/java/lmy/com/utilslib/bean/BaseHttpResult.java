package lmy.com.utilslib.bean;

/**
 * retrofit网络框架请求，遵循的json格式
 * Created by lmy on 2017/7/11
 */

public class BaseHttpResult<T> {
    public String message;
    public int rcode;
    public T list;              //T 任意类型，对象或者集合
}
