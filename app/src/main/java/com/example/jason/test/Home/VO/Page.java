package com.example.jason.test.Home.VO;

import android.support.v4.app.Fragment;

/**
 * Created by Jason on 2018/1/5.
 */

public class Page {

    private Fragment fragment;
    private String title;

    public Page(Fragment fragment, String title) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
