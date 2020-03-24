package com.example.hz52.app;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.MQ.MqttMessageService;
import com.example.hz52.app.Sqlentity.Conver;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.dao.mConverDao;
import com.lzf.easyfloat.EasyFloat;
import com.lzf.easyfloat.anim.AppFloatDefaultAnimator;
import com.lzf.easyfloat.anim.DefaultAnimator;
import com.lzf.easyfloat.enums.ShowPattern;
import com.lzf.easyfloat.enums.SidePattern;
import com.lzf.easyfloat.interfaces.OnInvokeView;
import com.lzf.easyfloat.interfaces.OnPermissionResult;
import com.lzf.easyfloat.permission.PermissionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    private static final String TAG = "12";
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

    private static TextBadgeItem mBadgeItem;
    private int num = 0;
    private static ImageView mIconView;
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
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.WAKE_LOCK,

    };

    private static final int PERMISSION_REQUESTED = 0;
    private static final int REQUEST_CODE = 1;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;
    public static Observer<Integer> observer;
    public static Observer<Integer> observers;
    private ArrayList<FragmentTouchListener> mFragmentTouchListeners = new ArrayList<>();
    private Map<String, String> map = new HashMap<String, String>();
    private int sum = 0;
    private String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        Initialization.setupDatabaseChat(this);
        Initialization.setupDatabaseConver(this);

        SharedPreferences sp = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        if (!userid.equals("")) {
            MqttMessageService.create(this);

        } else {
            Intent intent1 = new Intent(MainActivity.this, login.class);
            intent1.putExtra("type", 1);
            startActivity(intent1);
            overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
        }
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
                .addItem(new BottomNavigationItem(R.drawable.tab_icon_sou_normal, "首页").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.tab_icon_faxian_normal, "发现").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.tab_icon_paiming_normal, "排名榜").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.tab_icon_xiaoxi_normal, "消息").setActiveColorResource(R.color.colorAccent).setBadgeItem(mBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.tab_icon_me_normal, "我的").setActiveColorResource(R.color.colorAccent))
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


    public void registerFragmentTouchListener(FragmentTouchListener listener) {
        mFragmentTouchListeners.add(listener);
    }


    public void unRegisterFragmentTouchListener(FragmentTouchListener listener) {
        mFragmentTouchListeners.remove(listener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        for (FragmentTouchListener listener : mFragmentTouchListeners) {
            if (sum == 0) {
                listener.onTouchEvent(event);
            }

        }

        return super.dispatchTouchEvent(event);
    }

    public interface FragmentTouchListener {

        boolean onTouchEvent(MotionEvent event);
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


    protected void onDestroy() {
        super.onDestroy();
        MqttMessageService.unsubscribeToTopic("room/1001");
        MqttMessageService.destroy();
    }

    private void showAppFloat() {

        EasyFloat.with(this)
                // 设置浮窗xml布局文件，并可设置详细信息
                .setLayout(R.layout.floating, new OnInvokeView() {
                    @Override
                    public void invoke(View view) {
                        ImageView ima = view.findViewById(R.id.float_ima);
                        ImageView del = view.findViewById(R.id.float_del);

                        ima.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EasyFloat.dismissAppFloat("testFloat");
                                Intent intent = new Intent(MainActivity.this, chatroom.class);
                                startActivity(intent);
                            }
                        });

                        del.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EasyFloat.dismissAppFloat("testFloat");
                                Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                                    @Override
                                    public ObservableSource<? extends Integer> call() throws Exception {
                                        return Observable.just(0);
                                    }
                                });
                                observable.subscribe(chatroom.observer);
                            }
                        });
                    }
                })
                .setShowPattern(ShowPattern.FOREGROUND)
                .setSidePattern(SidePattern.RESULT_RIGHT)
                .setGravity(Gravity.CENTER)
                // 设置浮窗的标签，用于区分多个浮窗
                .setTag("testFloat")
                // 设置浮窗是否可拖拽
                .setDragEnable(true)
                // 系统浮窗是否包含EditText，仅针对系统浮窗，默认不包含
                .hasEditText(false)
                // 设置浮窗固定坐标，ps：设置固定坐标，Gravity属性和offset属性将无效
                //.setLocation(0, 400)
                // 设置宽高是否充满父布局，直接在xml设置match_parent属性无效
                .setMatchParent(false, false)
                // 设置Activity浮窗的出入动画，可自定义，实现相应接口即可（策略模式），无需动画直接设置为null
                .setAnimator(new DefaultAnimator())
                // 设置系统浮窗的出入动画，使用同上
                .setAppFloatAnimator(new AppFloatDefaultAnimator())
                // 设置系统浮窗的不需要显示的页面
                .setFilter(chatroom.class).show();


    }

    /**
     * 检测浮窗权限是否开启，若没有给与申请提示框（非必须，申请依旧是EasyFloat内部内保进行）
     */
    private void checkPermission() {
        if (PermissionUtils.checkPermission(this)) {
            showAppFloat();
        } else {
            requestPermission();
        }
    }

    /**
     * 判断应用是否在后台白名单
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(getPackageName());
        }
        return isIgnoring;
    }

    /**
     * 申请加入后台白名单
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 主动申请浮窗权限
     */
    private void requestPermission() {
        PermissionUtils.requestPermission(this, new OnPermissionResult() {
            @Override
            public void permissionResult(boolean b) {
                System.out.println("状态：" + EasyFloat.appFloatIsShow("testFloat"));
            }
        });
    }

    @Override
    public void onTabSelected(int position) {
        sum = position;
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
                setBadgeNum(num);
                transaction.replace(R.id.layout1, messages);

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
    public static void setBadgeNum(int num) {

        if (num == 0) {
            mBadgeItem.hide();
        } else {
            mBadgeItem.show();
            mBadgeItem.setText(String.valueOf(num));
            ObjectAnimator.ofFloat(mIconView, "translationX", 2000, 0).start();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        if(!isIgnoringBatteryOptimizations()){
            requestIgnoreBatteryOptimizations();
        }
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }
        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                if (!EasyFloat.appFloatIsShow("testFloat")) {
                    checkPermission();
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };

        observers = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

                MyApp application = ((MyApp) MainActivity.this.getApplicationContext());
                Conver conver = new Conver();
                Conver convers = new Conver();
                java.util.List<Conver> listall = mConverDao.queryAll(Long.valueOf(userid));
                if (integer == 1) {
                    if (application.getUsermess().size() > 0) {
                        if (listall.size() > 0) {
                            for (int i = 0; i < mConverDao.queryAll(Long.valueOf(userid)).size(); i++) {
                                int a = 0;
                                int b = 0;
                                for (int j = 0; j < application.getUsermess().size(); j++) {
                                    if (application.getUsermess().get(j).getSendId().equals(listall.get(i).getSendId())) {
                                        a++;
                                        conver.setSendsrc(application.getUsermess().get(j).getSendsrc());
                                        conver.setSendname(application.getUsermess().get(j).getSendname());
                                        conver.setSendId(application.getUsermess().get(j).getSendId());
                                        conver.setData(application.getUsermess().get(j).getData());
                                        conver.setSum(a);
                                        conver.setUserId(Long.valueOf(userid));
                                        conver.setTxt(application.getUsermess().get(j).getTxt());
                                        conver.setType(1);
                                    } else {
                                        b++;
                                        convers.setSendsrc(application.getUsermess().get(j).getSendsrc());
                                        convers.setSendname(application.getUsermess().get(j).getSendname());
                                        convers.setSendId(application.getUsermess().get(j).getSendId());
                                        convers.setData(application.getUsermess().get(j).getData());
                                        convers.setSum(b);
                                        convers.setUserId(Long.valueOf(userid));
                                        convers.setTxt(application.getUsermess().get(j).getTxt());
                                        convers.setType(1);
                                    }
                                }
                                if (a != 0) {
                                    Log.i(TAG, "0:" + a);
                                    mConverDao.update(conver);
                                }
                                if (b != 0) {
                                    Log.i(TAG, "1:" + b);
                                    mConverDao.insert(convers);
                                }
                            }
                        } else {
                            int c = 0;
                            for (int k = 0; k < application.getUsermess().size(); k++) {
                                c++;
                                convers.setSendsrc(application.getUsermess().get(k).getSendsrc());
                                convers.setSendname(application.getUsermess().get(k).getSendname());
                                convers.setSendId(application.getUsermess().get(k).getSendId());
                                convers.setData(application.getUsermess().get(k).getData());
                                convers.setSum(c);
                                convers.setUserId(Long.valueOf(userid));
                                convers.setTxt(application.getUsermess().get(k).getTxt());
                                convers.setType(1);
                            }
                            if (c != 0) {
                                Log.i(TAG, "2:" + c);
                                mConverDao.insert(convers);
                            }
                        }

                    }
                }
                num = application.getOfficmess().size() + application.getUsermess().size();
                Log.i(TAG, "消息数量:" + num);
                setBadgeNum(num);
                if(Messages.isFront){
                    Observable<Integer> observables = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(1);
                        }
                    });
                    observables.subscribe(Messages.observermess);
                }


            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };

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
        if (!PermissionUtils.checkPermission(this)) {
            requestPermission();
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

