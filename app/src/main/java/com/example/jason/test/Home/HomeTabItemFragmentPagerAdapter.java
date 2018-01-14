package com.example.jason.test.Home;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jason.test.Home.Albums.AlbumsFragment;
import com.example.jason.test.Home.News.WeatherFragment;
import com.example.jason.test.Home.VO.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2018/1/5.
 */

public class HomeTabItemFragmentPagerAdapter extends FragmentPagerAdapter {

    //Log專用
    private final static String TAG = "HomeTabItemFragmentPagerAdapter";

    List<Page> pageList;

    @SuppressLint("LongLogTag")
    public HomeTabItemFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        pageList = new ArrayList<>();
        pageList.add(new Page(new WeatherFragment(), "NewsList"));
        pageList.add(new Page(new AlbumsFragment(), "ImageList"));
//        pageList.add(new Page(new AlbumsFragment(), "ImageList"));
//        pageList.add(new Page(new AlbumsFragment(), "ImageList"));
//        pageList.add(new Page(new AlbumsFragment(), "ImageList"));
    }

    @Override
    public Fragment getItem(int position) {
        return pageList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
