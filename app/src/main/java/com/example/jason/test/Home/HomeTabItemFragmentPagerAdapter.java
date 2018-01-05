package com.example.jason.test.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jason.test.Home.VO.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 2018/1/5.
 */

public class HomeTabItemFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Page> pageList;

    public HomeTabItemFragmentPagerAdapter(FragmentManager fm) {

        super(fm);
        pageList = new ArrayList<>();
        pageList.add(new Page(new HomeNewsFragment(), "NewsList"));
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
