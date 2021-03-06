package com.example.jason.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.jason.test.Home.TabHomeActivity;
import com.example.jason.test.Main.Common;

public class WelcomeActivity extends AppCompatActivity implements Animation.AnimationListener {

    //Log專用
    private final static String TAG = "WelcomeActivity";

    //View
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initSetting();
        initView();
    }

    private void initSetting() {

        //取消狀態欄
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //拿取螢幕解析度
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        Log.d(TAG,displaymetrics.widthPixels + "    螢幕寬");

        float dd = displaymetrics.density;
        float px = 10 * dd;
        Common.phoneWidth = (int) (displaymetrics.widthPixels - px);
        Common.phoneHeight = (int) (displaymetrics.heightPixels - px);

        Log.d(TAG,Common.phoneWidth + "    不同解析度下的新螢幕寬");
        Log.d(TAG,Common.phoneHeight + "    不同解析度下的新螢幕高");

    }

    private void initView() {

        ivLogo = (ImageView)findViewById(R.id.ivLogo);
        //設定動畫
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
        animation.setFillEnabled(true);
        animation.setFillAfter(true);
        animation.setAnimationListener(this);
        ivLogo.setAnimation(animation);

    }

    @Override
    public void onAnimationStart(Animation animation) {
        //動畫開始
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        //動畫結束

        //權限
        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            //未取得權限，向使用者要求允許權限
            Log.d(TAG, "未取得權限，向使用者要求允許權限。");
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            Common.askPermissions(this, permissions, Common.PERMISSION_READ_EXTERNAL_STORAGE);
        }else{
            //已有權限，可進行檔案存取
            Log.d(TAG, "已有權限，可進行檔案存取");
            startActivity(new Intent(WelcomeActivity.this,TabHomeActivity.class));
            finish();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "當Activity變得可見時調用該函數。");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case Common.PERMISSION_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG,"答應了");
                    startActivity(new Intent(WelcomeActivity.this,TabHomeActivity.class));
                    finish();
                } else {
                    Log.d(TAG,"不答應");
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "當Activity開始準備與用戶交互時調用該方法，即把保存的資料拿回來使用。");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "當系統即將啟動另外一個Activity之前調用該方法，即把需要保存的資料保存。");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "當前Activity變得不可見時調用該方法。");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"當前Activity被銷毀之前將會調用該方法，即通常都拿來把onCreate()時的資料做釋放的動作。");
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "當一個Activity再次啟動之前將會調用該方法。");
    }
}
