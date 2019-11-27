package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.navbar1)
    BottomNavigationBar navbar1;
    @BindView(R.id.layout1)
    NestedScrollView layout1;
    private ArrayList<Fragment> fragments;
    private Home home;
    private List list;
    private Message message;
    private My my;

    private TextBadgeItem mBadgeItem;
    private int num = 1;
    private ImageView mIconView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setDefaultFragment();
        layout1.setFocusable(true);
        layout1.setFocusableInTouchMode(true);
        layout1.requestFocus();
        layout1.scrollTo(0,0);

    }

    private void init() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.navbar1);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );

        mBadgeItem = new TextBadgeItem()
                .setBorderWidth(2)
                .setTextColor(Color.WHITE)
                .setBackgroundColor(Color.RED)
                .setText("0")
                .setAnimationDuration(200)
                .setHideOnSelect(true);

       /*  BadgeItem badge=new BadgeItem()
                .setShape(SHAPE_OVAL) //形状
                .setShapeColor(Color.BLUE) //颜色
                .setShapeColorResource(R.color.colorPrimaryDark) //颜色，资源文件获取
                .setEdgeMarginInDp(this,0) //距离Item的margin，dp
                .setEdgeMarginInPixels(20) //距离Item的margin，px
                .setSizeInDp(this,10,10) //宽高，dp
                .setSizeInPixels(5,5) //宽高，px
                .setAnimationDuration(200) //隐藏和展示的动画速度，单位毫秒,和setHideOnSelect一起使用
                .setGravity(Gravity.LEFT) //位置，默认右上角
                .setHideOnSelect(true); //true：当选中状态时消失，非选中状态显示,moren false

               */

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home, "首页").setActiveColorResource(R.color.tabbarcolor))
                .addItem(new BottomNavigationItem(R.drawable.list, "排名榜").setActiveColorResource(R.color.tabbarcolor))
                .addItem(new BottomNavigationItem(R.drawable.message, "消息").setActiveColorResource(R.color.tabbarcolor).setBadgeItem(mBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.my, "我的").setActiveColorResource(R.color.tabbarcolor))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        /*bottomNavigationBar.hide();//隐藏
        bottomNavigationBar.hide(true);//隐藏是否启动动画，这里并不能自定义动画
        bottomNavigationBar.unHide();//显示
        bottomNavigationBar.hide(true);//隐藏是否启动动画，这里并不能自定义动画*/
        bader();


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
        setBadgeNum(num);
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
                setBadgeNum(num);
                break;
            case 1:

                if (list == null) {
                    list = List.newInstance();
                }
                transaction.replace(R.id.layout1, list);
                setBadgeNum(num);
                break;
            case 2:

                if (message == null) {
                    message = Message.newInstance();
                }
                transaction.replace(R.id.layout1, message);
                num = 0;
                setBadgeNum(num);
                break;
            case 3:

                if (my == null) {
                    my = My.newInstance();
                }
                transaction.replace(R.id.layout1, my);
                setBadgeNum(num);
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

    private void bader() {

        LayoutInflater inflater = LayoutInflater.from(this);
        //获取 bar 的 所对应的子 view 控件，方便扩展动画
        View parentView = inflater.inflate(com.ashokvarma.bottomnavigation.R.layout.bottom_navigation_bar_container, navbar1, true);
        LinearLayout mTabContainer = (LinearLayout) parentView.findViewById(com.ashokvarma.bottomnavigation.R.id.bottom_navigation_bar_item_container);
        //购物车标签是对应位置是 mTabContainer 的2
        mIconView = (ImageView) mTabContainer.getChildAt(2).findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);
    }

    /**
     * 设置tab数字提示加缩放动画
     */
    private void setBadgeNum(int num) {
        mBadgeItem.setText(String.valueOf(num));
        if (num == 0) {
            mBadgeItem.hide();
        } else {
            mBadgeItem.show();
            ObjectAnimator.ofFloat(mIconView, "translationX", 2000, 0).start();
        }
    }

}
