package com.gzhy.zhichisdk;

import android.app.Application;

import com.sobot.chat.SobotApi;

/**
 * Created by hy on 2018-05-26
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SobotApi.initSobotSDK(this, "c6b29ad917ac4e8a9aac09a928bd55b4", "3113006364");
    }
}
