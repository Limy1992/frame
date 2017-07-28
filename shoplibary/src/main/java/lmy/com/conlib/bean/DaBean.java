package lmy.com.conlib.bean;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lmy on 2017/7/24
 */

@Module
public class DaBean {
    private final String s;


    public DaBean(String s){
        this.s = s;

    }

    @Provides
    public String provideString(){
        return s;
    }

}
