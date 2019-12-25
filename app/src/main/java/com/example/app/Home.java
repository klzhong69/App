package com.example.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.app.Adapter.FamilyHomeAdapter;
import com.example.app.Entity.Findmake;
import com.example.app.Entity.Homes;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    QMUIRadiusImageView imageViewi3;
    @BindView(R.id.textViewi9)
    TextView textViewi9;
    @BindView(R.id.textViewi10)
    TextView textViewi10;
    @BindView(R.id.imageViewi4)
    QMUIRadiusImageView imageViewi4;
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
    QMUIRadiusImageView imageViewi3s;
    @BindView(R.id.textViewi9s)
    TextView textViewi9s;
    @BindView(R.id.textViewi10s)
    TextView textViewi10s;
    @BindView(R.id.imageViewi4s)
    QMUIRadiusImageView imageViewi4s;
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
    private Unbinder unbinder;
    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画
    private boolean mIsShowBack;

    private AnimatorSet mRightOutSets; // 右入动画
    private AnimatorSet mLeftInSets; // 左出动画
    private ArrayList<Homes> mArrayList;
    private int sum = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_room, container, false);
        unbinder = ButterKnife.bind(this, view);

        title.setText("首页");
        setAnimators(); // 设置动画
        setAnimatorsd();
        setCameraDistance(); // 设置镜头距离

        initData();
        return view;

    }

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
                startActivity(intent);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
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

    private void initData(){

        mArrayList = new ArrayList<Homes>();
        for (int i = 0; i < 10; i++) {
            Homes i1 = new Homes("《点歌》生命歌手集"+i, "ID1315464", "al.粉丝团", "热门", "CV","德国","1234","https://momeak.oss-cn-shenzhen.aliyuncs.com/h4.jpg","【芭比】uud小可爱","2","","==《而福利社区点歌台》==","我做了这么多改变，只为了我心中不变");
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

        if(mArrayList.get(0).getRoomtype().equals("2")){
            textViewi12s.setText("黄金房间");
        }

    }


    private void initView(int num){
        if(num>0){
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

            if(mArrayList.get(num).getRoomtype().equals("2")){
                textViewi12.setText("黄金房间");
            }
        }


    }
    private void initViews(int num){
        if(num<mArrayList.size()){
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

            if(mArrayList.get(num).getRoomtype().equals("2")){
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
        int distance = 3200;
        float scale = getResources().getDisplayMetrics().density * distance;
        relative12.setCameraDistance(scale);
        relative13.setCameraDistance(scale);
    }

    // 翻转卡片
    public void flipCard() {
        // 正面朝上
        if (!mIsShowBack) {

            if(sum==0){
                Toast.makeText(getContext(), "已是第一个", Toast.LENGTH_SHORT).show();
            }else{

                mRightOutSet.setTarget(relative13);
                mLeftInSet.setTarget(relative12);
                mRightOutSet.start();
                mLeftInSet.start();
                mIsShowBack = true;
                sum--;
                initView(sum);

            }
        } else { // 背面朝上
            if(sum==0){
                Toast.makeText(getContext(), "已是第一个", Toast.LENGTH_SHORT).show();
            }else{

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
            if(sum==mArrayList.size()-1){
                Toast.makeText(getContext(), "已是最后一个", Toast.LENGTH_SHORT).show();
            }else{
                mRightOutSets.setTarget(relative13);
                mLeftInSets.setTarget(relative12);
                mRightOutSets.start();
                mLeftInSets.start();
                mIsShowBack = true;
                sum++;
                initView(sum);

            }
        } else { // 正面朝上
            if(sum==mArrayList.size()-1){
                Toast.makeText(getContext(), "已是最后一个", Toast.LENGTH_SHORT).show();
            }else{

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

    /**
     * 缩小动画
     *
     * @param view
     */
    public static void zoomIn(final View view, float scale, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
    }
}
