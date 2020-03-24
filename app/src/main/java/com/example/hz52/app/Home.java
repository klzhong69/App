package com.example.hz52.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.hz52.app.Entity.Homes;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.wildma.pictureselector.Constant;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.agora.rtc.Constants;

public class Home extends Fragment {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.imageView149)
    ImageView imageView149;
    @BindView(R.id.relative11)
    RelativeLayout relative11;
    @BindView(R.id.imageViewi1)
    QMUIRadiusImageView imageViewi1;
    @BindView(R.id.textViewi1)
    TextView textViewi1;
    @BindView(R.id.textViewi2)
    TextView textViewi2;
    @BindView(R.id.textViewi3)
    TextView textViewi3;
    @BindView(R.id.textViewi4)
    TextView textViewi4;
    @BindView(R.id.textViewi5)
    TextView textViewi5;
    @BindView(R.id.textViewi6)
    TextView textViewi6;
    @BindView(R.id.imageViewi2)
    ImageView imageViewi2;
    @BindView(R.id.textViewi7)
    TextView textViewi7;
    @BindView(R.id.textViewi8)
    TextView textViewi8;
    @BindView(R.id.imageViewi3)
    ImageView imageViewi3;
    @BindView(R.id.imageViewi4)
    QMUIRadiusImageView imageViewi4;
    @BindView(R.id.textViewi9)
    TextView textViewi9;
    @BindView(R.id.textViewi10)
    TextView textViewi10;
    @BindView(R.id.imageViewi5)
    QMUIRadiusImageView imageViewi5;
    @BindView(R.id.textViewi11)
    TextView textViewi11;
    @BindView(R.id.imageViewi6)
    QMUIRadiusImageView imageViewi6;
    @BindView(R.id.imageViewi7)
    ImageView imageViewi7;
    @BindView(R.id.textViewi12)
    TextView textViewi12;
    @BindView(R.id.relative12)
    RelativeLayout relative12;
    @BindView(R.id.imageViewi1s)
    QMUIRadiusImageView imageViewi1s;
    @BindView(R.id.textViewi1s)
    TextView textViewi1s;
    @BindView(R.id.textViewi2s)
    TextView textViewi2s;
    @BindView(R.id.textViewi3s)
    TextView textViewi3s;
    @BindView(R.id.textViewi4s)
    TextView textViewi4s;
    @BindView(R.id.textViewi5s)
    TextView textViewi5s;
    @BindView(R.id.textViewi6s)
    TextView textViewi6s;
    @BindView(R.id.imageViewi2s)
    ImageView imageViewi2s;
    @BindView(R.id.textViewi7s)
    TextView textViewi7s;
    @BindView(R.id.textViewi8s)
    TextView textViewi8s;
    @BindView(R.id.imageViewi3s)
    ImageView imageViewi3s;
    @BindView(R.id.imageViewi4s)
    QMUIRadiusImageView imageViewi4s;
    @BindView(R.id.textViewi9s)
    TextView textViewi9s;
    @BindView(R.id.textViewi10s)
    TextView textViewi10s;
    @BindView(R.id.imageViewi5s)
    QMUIRadiusImageView imageViewi5s;
    @BindView(R.id.textViewi11s)
    TextView textViewi11s;
    @BindView(R.id.imageViewi6s)
    QMUIRadiusImageView imageViewi6s;
    @BindView(R.id.imageViewi7s)
    ImageView imageViewi7s;
    @BindView(R.id.textViewi12s)
    TextView textViewi12s;
    @BindView(R.id.relative13)
    RelativeLayout relative13;
    @BindView(R.id.imageView152)
    ImageView imageView152;
    @BindView(R.id.imageView153)
    ImageView imageView153;
    @BindView(R.id.imageView154)
    ImageView imageView154;
    @BindView(R.id.View1)
    RelativeLayout View1;
    @BindView(R.id.View2)
    RelativeLayout View2;
    @BindView(R.id.View3)
    RelativeLayout View3;
    @BindView(R.id.View1s)
    RelativeLayout View1s;
    @BindView(R.id.View2s)
    RelativeLayout View2s;
    @BindView(R.id.View3s)
    RelativeLayout View3s;
    @BindView(R.id.view2)
    View view2;
    private Unbinder unbinder;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画
    private boolean mIsShowBack;

    private AnimatorSet mRightOutSets; // 右入动画
    private AnimatorSet mLeftInSets; // 左出动画
    private ArrayList<Homes> mArrayList;
    private int sum = 0;
    private GestureDetector gd;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_room, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getContext();
        Window window = Objects.requireNonNull(getActivity()).getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);

        ViewGroup.LayoutParams para;
        para = view2.getLayoutParams();
        para.height = height;
        view2.setLayoutParams(para);

        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        //ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);

        title.setText("首页");
        setAnimators(); // 设置动画
        setAnimatorsd();
        setCameraDistance(); // 设置镜头距离

        ((MainActivity) getActivity()).registerFragmentTouchListener(fragmentTouchListener);

        WindowManager mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        float numder = (float) 700 / 1920;
        float sumder = (float) (widthPixels / 0.5625);

        ViewGroup.LayoutParams para1;
        para1 = imageViewi3.getLayoutParams();
        para1.height = (int) (heightPixels * numder);
        imageViewi3.setLayoutParams(para1);

        ViewGroup.LayoutParams para2;
        para2 = imageViewi3s.getLayoutParams();
        para2.height = (int) (heightPixels * numder);
        imageViewi3s.setLayoutParams(para2);

        initData();


        gd = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {//按下
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {//按下但是还未抬起

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {//轻按，按一下，立刻抬起

                return false;
            }

            @Override//滚动
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {//长按
            }

            @Override//拖动
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > 100) {//右滑下一张
                    flipCards();

                }
                if (e2.getX() - e1.getX() > 100) {//左滑上一张
                    flipCard();

                }
                return false;
            }
        });
        return view;

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    MainActivity.FragmentTouchListener fragmentTouchListener = new MainActivity.FragmentTouchListener() {
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return gd.onTouchEvent(event);
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static Home newInstance() {
        Bundle args = new Bundle();
        Home fragment = new Home();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @OnClick({R.id.imageView149, R.id.relative12, R.id.relative13, R.id.imageView152, R.id.imageView153, R.id.imageView154})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView149:
                Intent intent2 = new Intent(getContext(), search.class);
                startActivity(intent2);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.relative12:
            case R.id.relative13:
                Intent intent = new Intent(getContext(), chatroom.class);
                //Constants.CLIENT_ROLE_AUDIENCE  听众
                //Constants.CLIENT_ROLE_BROADCASTER 主播
                intent.putExtra(Constant.ACTION_KEY_CROLE, Constants.CLIENT_ROLE_AUDIENCE);
                intent.putExtra(Constant.ACTION_KEY_ROOM_MODE, Constant.ChatRoomGamingStandard);
                intent.putExtra(Constant.ACTION_KEY_ROOM_ID, "211509701");
                intent.putExtra(Constant.ACTION_KEY_TITLE_NAME, "测试房间");
                startActivity(intent);
                break;
            case R.id.imageView152:
                flipCard();
                break;
            case R.id.imageView153:
                break;
            case R.id.imageView154:
                flipCards();
                break;
        }
    }

    private void initData() {

        mArrayList = new ArrayList<Homes>();
        for (int i = 0; i < 10; i++) {
            Homes i1 = new Homes("《点歌》生命歌手集" + i, "ID1315464", "al.粉丝团", "热门", "CV", "德国", "1234", "https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg", "【芭比】uud小可爱", "2", "", "==《而福利社区点歌台》==", "我做了这么多改变，只为了我心中不变");
            mArrayList.add(i1);
        }

        textViewi1s.setText(mArrayList.get(0).getRoomname());
        textViewi2s.setText(mArrayList.get(0).getId());
        textViewi3s.setText(mArrayList.get(0).getFamilyname());
        textViewi4s.setText(mArrayList.get(0).getLabel1());
        textViewi5s.setText(mArrayList.get(0).getLabel2());
        textViewi6s.setText(mArrayList.get(0).getLabel3());
        textViewi7s.setText(mArrayList.get(0).getOnlinepeople());
        Glide.with(this).load(R.drawable.s1).into((imageViewi3s));
        textViewi9s.setText(mArrayList.get(0).getTitle());
        textViewi10s.setText(mArrayList.get(0).getTxt());
        Glide.with(this).load(mArrayList.get(0).getUserima()).into((imageViewi5s));
        textViewi11s.setText(mArrayList.get(0).getUsername());

        if (mArrayList.get(0).getRoomtype().equals("2")) {
            textViewi12s.setText("黄金房间");
        }

    }

    private void okgos() {
        MyApp application = ((MyApp) getContext().getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/page/getHome")
                .params("count", 2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            JsonArray broadcasts = prexiew.getData().getAsJsonArray("home");

                            if (broadcasts.size() > 0) {
                                for (int i = 0; i < broadcasts.size(); i++) {
                                    String hot = broadcasts.get(i).getAsJsonObject().get("roomInfo").getAsString();
                                    String ownerId = broadcasts.get(i).getAsJsonObject().get("ownerId").getAsString();
                                    String uniqueId = broadcasts.get(i).getAsJsonObject().get("uniqueId").getAsString();
                                    String coverUrl = broadcasts.get(i).getAsJsonObject().get("coverUrl").getAsString();
                                    String roomName = broadcasts.get(i).getAsJsonObject().get("roomName").getAsString();
                                    String welcomeText = broadcasts.get(i).getAsJsonObject().get("welcomeText").getAsString();
                                    JsonArray tag = broadcasts.get(i).getAsJsonObject().get("tag").getAsJsonArray();
                                    String ownerName = broadcasts.get(i).getAsJsonObject().get("ownerName").getAsString();
                                }

                            } else if (prexiew.getCode() == 40000) {
                                Toast.makeText(getContext(), prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

    }

    private void initView(int num) {
        if (num > 0) {
            textViewi1.setText(mArrayList.get(num).getRoomname());
            textViewi2.setText(mArrayList.get(num).getId());
            textViewi3.setText(mArrayList.get(num).getFamilyname());
            textViewi4.setText(mArrayList.get(num).getLabel1());
            textViewi5.setText(mArrayList.get(num).getLabel2());
            textViewi6.setText(mArrayList.get(num).getLabel3());
            textViewi7.setText(mArrayList.get(num).getOnlinepeople());
            Glide.with(this).load(R.drawable.s1).into((imageViewi3));
            textViewi9.setText(mArrayList.get(num).getTitle());
            textViewi10.setText(mArrayList.get(num).getTxt());
            Glide.with(this).load(mArrayList.get(num).getUserima()).into((imageViewi5));
            textViewi11.setText(mArrayList.get(num).getUsername());

            if (mArrayList.get(num).getRoomtype().equals("2")) {
                textViewi12.setText("黄金房间");
            }
        }


    }

    private void initViews(int num) {
        if (num < mArrayList.size()) {
            textViewi1s.setText(mArrayList.get(num).getRoomname());
            textViewi2s.setText(mArrayList.get(num).getId());
            textViewi3s.setText(mArrayList.get(num).getFamilyname());
            textViewi4s.setText(mArrayList.get(num).getLabel1());
            textViewi5s.setText(mArrayList.get(num).getLabel2());
            textViewi6s.setText(mArrayList.get(num).getLabel3());
            textViewi7s.setText(mArrayList.get(num).getOnlinepeople());
            Glide.with(this).load(R.drawable.s1).into((imageViewi3s));
            textViewi9s.setText(mArrayList.get(num).getTitle());
            textViewi10s.setText(mArrayList.get(num).getTxt());
            Glide.with(this).load(mArrayList.get(num).getUserima()).into((imageViewi5s));
            textViewi11s.setText(mArrayList.get(num).getUsername());

            if (mArrayList.get(num).getRoomtype().equals("2")) {
                textViewi12s.setText("黄金房间");
            }
        }


    }

    // 设置动画
    private void setAnimatorsd() {
        mRightOutSets = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_outs);
        mLeftInSets = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_ins);

        // 设置点击事件
        mRightOutSets.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                relative13.setClickable(false);
            }
        });
        mLeftInSets.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                relative12.setClickable(true);
            }
        });
    }

    // 设置动画
    private void setAnimators() {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.anim_in);

        // 设置点击事件
        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                relative13.setClickable(false);
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                relative12.setClickable(true);
            }
        });
    }

    // 改变视角距离, 贴近屏幕
    private void setCameraDistance() {
        int distance = 16000;
        float scale = getResources().getDisplayMetrics().density * distance;
        relative12.setCameraDistance(scale);
        relative13.setCameraDistance(scale);
    }

    // 翻转卡片
    public void flipCard() {
        // 正面朝上
        if (!mIsShowBack) {

            if (sum == 0) {
                Toast.makeText(getContext(), "已是第一个", Toast.LENGTH_SHORT).show();
            } else {

                mRightOutSet.setTarget(relative13);
                mLeftInSet.setTarget(relative12);
                mRightOutSet.start();
                mLeftInSet.start();
                mIsShowBack = true;
                sum--;
                initView(sum);

            }
        } else { // 背面朝上
            if (sum == 0) {
                Toast.makeText(getContext(), "已是第一个", Toast.LENGTH_SHORT).show();
            } else {

                mRightOutSet.setTarget(relative12);
                mLeftInSet.setTarget(relative13);
                mRightOutSet.start();
                mLeftInSet.start();
                mIsShowBack = false;
                sum--;
                initViews(sum);

            }

        }
    }

    // 翻转卡片
    public void flipCards() {
        // 背面朝上
        if (!mIsShowBack) {
            if (sum == mArrayList.size() - 1) {
                Toast.makeText(getContext(), "已是最后一个", Toast.LENGTH_SHORT).show();
            } else {
                mRightOutSets.setTarget(relative13);
                mLeftInSets.setTarget(relative12);
                mRightOutSets.start();
                mLeftInSets.start();
                mIsShowBack = true;
                sum++;
                initView(sum);

            }
        } else { // 正面朝上
            if (sum == mArrayList.size() - 1) {
                Toast.makeText(getContext(), "已是最后一个", Toast.LENGTH_SHORT).show();
            } else {

                mRightOutSets.setTarget(relative12);
                mLeftInSets.setTarget(relative13);
                mRightOutSets.start();
                mLeftInSets.start();
                mIsShowBack = false;
                sum++;
                initViews(sum);

            }

        }
    }

}
