package com.example.reviewreadurl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {
    FragmentHome fragmentHome;
    FragmentNews fragmentNews;
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentHome=new FragmentHome();
        fragmentNews=new FragmentNews();
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0) return fragmentHome;
        return fragmentNews;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
