package com.cyq7on.template;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * @Description:
 * @author: cyq7on
 * @date: 2016/7/27 11:10
 * @version: V1.0
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Logger.init("template")
              .methodCount(3);
    }

    public static Context getContext() {
        return context;
    }
}
