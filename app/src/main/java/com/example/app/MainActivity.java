package com.example.app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.navbar1)
    BottomNavigationBar navbar1;
    @BindView(R.id.layout1)
    NestedScrollView layout1;
    private ArrayList<Fragment> fragments;
    private Home home;
    private Find find;
    private List list;
    private Messages messages;
    private My my;

    private TextBadgeItem mBadgeItem;
    private int num = 1;
    private ImageView mIconView;
    private BottomNavigationBar bottomNavigationBar;
    protected String[] needPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_WIFI_STATE,

    };

    private static final int PERMISSION_REQUESTED = 0;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setDefaultFragment();

    }

    private void init() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.navbar1);
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
                .addItem(new BottomNavigationItem(R.drawable.back, "首页").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.back, "发现").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.back, "排名榜").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.back, "消息").setActiveColorResource(R.color.colorAccent).setBadgeItem(mBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.back, "我的").setActiveColorResource(R.color.colorAccent))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        /*bottomNavigationBar.hide();//隐藏
        bottomNavigationBar.hide(true);//隐藏是否启动动画，这里并不能自定义动画
        bottomNavigationBar.unHide();//显示
        bottomNavigationBar.hide(true);//隐藏是否启动动画，这里并不能自定义动画*/
        LayoutInflater inflater = LayoutInflater.from(this);
        //获取 bar 的 所对应的子 view 控件，方便扩展动画
        View parentView = inflater.inflate(com.ashokvarma.bottomnavigation.R.layout.bottom_navigation_bar_container, navbar1, true);
        LinearLayout mTabContainer = (LinearLayout) parentView.findViewById(com.ashokvarma.bottomnavigation.R.id.bottom_navigation_bar_item_container);
        //购物车标签是对应位置是 mTabContainer 的2
        mIconView = (ImageView) mTabContainer.getChildAt(2).findViewById(com.ashokvarma.bottomnavigation.R.id.fixed_bottom_navigation_icon);


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


    /**
     *  Activity创建或者从后台重新回到前台时被调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent =getIntent();
        int id= intent.getIntExtra("id",0);
        bottomNavigationBar.setFirstSelectedPosition(id).initialise();
        onTabSelected(id);
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

                if (find == null) {
                    find = Find.newInstance();
                }
                transaction.replace(R.id.layout1, find);
                setBadgeNum(num);
                break;
            case 2:

                if (list == null) {
                    list = List.newInstance();
                }
                transaction.replace(R.id.layout1, list);
                setBadgeNum(num);
                break;
            case 3:

                if (messages == null) {
                    messages = Messages.newInstance();
                }
                transaction.replace(R.id.layout1, messages);
                num = 0;
                setBadgeNum(num);
                break;
            case 4:

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


    @Override
    public void onResume() {
        super.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
    }



    /**
     * 检查权限
     *
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        //获取权限列表
        java.util.List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]),
                    PERMISSION_REQUESTED);
        }
    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private java.util.List<String> findDeniedPermissions(String[] permissions) {
        java.util.List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSION_REQUESTED) {
            if (!verifyPermissions(paramArrayOfInt)) {      //没有授权
                showMissingPermissionDialog();              //显示提示信息
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

