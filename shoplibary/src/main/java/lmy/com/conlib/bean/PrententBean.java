package lmy.com.conlib.bean;

import javax.inject.Inject;

/**
 * Created by lmy on 2017/7/24
 */

public class PrententBean {
    private final String s;
    private final int age;

    @Inject
    public PrententBean(String s, int age){
        this.s =s;
        this.age = age;
    }

    public String getS() {
        return s;
    }

    public int getAge() {
        return age;
    }
}
