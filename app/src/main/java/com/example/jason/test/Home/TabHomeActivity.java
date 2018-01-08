package com.example.jason.test.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.jason.test.R;

public class TabHomeActivity extends AppCompatActivity {

    //Log專用
    private final static String TAG = "TabHomeActivity";

    //View元件
    private Toolbar tbHome;
    private ViewPager vpHome;
    private TabLayout tlHome;
    private int[] IconResID = {R.drawable.home_tab_select_01,R.drawable.home_tab_select_02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_home);

        initView();
    }

    private void initView() {

        tbHome = (Toolbar) findViewById(R.id.tbHome);
        vpHome = (ViewPager)findViewById(R.id.vpHome);
        tlHome = (TabLayout) findViewById(R.id.tlHome);

        tbHome.setTitle(R.string.app_name);
        tbHome.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        HomeTabItemFragmentPagerAdapter homeTabItemFragmentPagerAdapter =
                new  HomeTabItemFragmentPagerAdapter(getSupportFragmentManager());

        vpHome.setAdapter(homeTabItemFragmentPagerAdapter);

        vpHome.setOffscreenPageLimit(0);
        tlHome.setupWithViewPager(vpHome);

        for(int i =0; i < IconResID.length;i++){
            tlHome.getTabAt(i).setIcon(IconResID[i]);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
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
