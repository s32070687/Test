package com.example.jason.test;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Created by Jason on 2018/1/4.
 */

public class APP extends Application {

    private static APP app;

    public static APP getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 使用APP設定的字體大小，字體不隨用戶系統設定變化
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        app = this;

    }

}
