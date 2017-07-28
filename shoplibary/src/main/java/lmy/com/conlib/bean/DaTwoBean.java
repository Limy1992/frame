package lmy.com.conlib.bean;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lmy on 2017/7/24
 */
@Module
public class DaTwoBean {
    private final int age;

    public DaTwoBean(int age){
        this.age = age;
    }

    @Provides
    public int provideInts(){
        return age;
    }
}
