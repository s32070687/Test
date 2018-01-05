package com.example.jason.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.jason.test.GetSever.GetSeverActivity;
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

        //取消ActonBar
        getSupportActionBar().hide();

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
        startActivity(new Intent(WelcomeActivity.this,GetSeverActivity.class));
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}