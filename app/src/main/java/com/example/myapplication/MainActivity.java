package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{


    @BindView(R.id.navbar1)
    com.ashokvarma.bottomnavigation.BottomNavigationBar navbar1;
    @BindView(R.id.layout1)
    NestedScrollView layout1;
    private ArrayList<Fragment> fragments;
    private Home home;
    private List list;
    private Message message;
    private My my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.navbar1);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home, "首页").setActiveColorResource(R.color.tabbarcolor))
                .addItem(new BottomNavigationItem(R.drawable.list, "排名榜").setActiveColorResource(R.color.tabbarcolor))
                .addItem(new BottomNavigationItem(R.drawable.message, "消息").setActiveColorResource(R.color.tabbarcolor))
                .addItem(new BottomNavigationItem(R.drawable.my, "我的").setActiveColorResource(R.color.tabbarcolor))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        home = Home.newInstance();
        transaction.replace(R.id.layout1, home);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d("onTabSelected", "onTabSelected: " + position);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:

                if (home == null) {
                    home = Home.newInstance();
                }
                transaction.replace(R.id.layout1, home);
                break;
            case 1:

                if (list == null) {
                    list = List.newInstance();
                }
                transaction.replace(R.id.layout1, list);
                break;
            case 2:

                if (message == null) {
                    message = Message.newInstance();
                }
                transaction.replace(R.id.layout1, message);
                break;
            case 3:

                if (my == null) {
                    my = My.newInstance();
                }
                transaction.replace(R.id.layout1, my);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }


}
