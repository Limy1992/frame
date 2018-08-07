package com.lmy.audio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lmy.audio.main.MainActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by lmy on 2017/8/8
 */

public class StartLogoActivity extends RxAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_logo);
        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Intent intent = new Intent(StartLogoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

    }
}
