package com.example.myapplication.cofig;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.myapplication.icon1;
import com.example.myapplication.icon2;
import com.example.myapplication.icon3;
import com.example.myapplication.icon4;
import com.example.myapplication.icon5;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> myFragment;

    public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.myFragment = datas;

    }

    //选中的item
    @Override
    public Fragment getItem(int position) {
        return myFragment.get(position);
    }


    @Override
    public int getCount() {
        if (myFragment == null) {
            return 0;
        } else {
            return myFragment.size();
        }
    }

    public void update(List<Fragment> datas){
        this.myFragment = datas;
        notifyDataSetChanged();
    }
}
