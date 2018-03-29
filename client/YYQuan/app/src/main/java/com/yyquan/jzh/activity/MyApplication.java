package com.yyquan.jzh.activity;

import android.app.Application;


import com.mob.MobSDK;

import java.util.concurrent.TimeUnit;


/**
 * Created by jzh on 2015/10/7.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
