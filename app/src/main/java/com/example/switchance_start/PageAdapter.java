package com.example.switchance_start;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new logo();
            case 1:
                return new ChatList();
            case 2:
                return new Personal();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

