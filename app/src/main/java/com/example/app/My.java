package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.app.cofig.DateUtil;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class My extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.imageView65)
    ImageView imageView65;
    @BindView(R.id.textView74)
    TextView textView74;
    @BindView(R.id.imageView68)
    ImageView imageView68;
    @BindView(R.id.imageView28)
    QMUIRadiusImageView imageView28;
    @BindView(R.id.imageView36)
    QMUIRadiusImageView imageView36;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.textView75)
    TextView textView75;
    @BindView(R.id.textView92)
    TextView textView92;
    @BindView(R.id.textView93)
    TextView textView93;
    @BindView(R.id.textView94)
    TextView textView94;
    @BindView(R.id.textView95)
    TextView textView95;
    @BindView(R.id.imageView37)
    QMUIRadiusImageView imageView37;
    @BindView(R.id.imageView39)
    QMUIRadiusImageView imageView39;
    @BindView(R.id.textView76)
    TextView textView76;
    @BindView(R.id.textView77)
    TextView textView77;
    @BindView(R.id.textView78)
    TextView textView78;
    @BindView(R.id.textView79)
    TextView textView79;
    @BindView(R.id.textView80)
    TextView textView80;
    @BindView(R.id.textView81)
    TextView textView81;
    @BindView(R.id.textView82)
    TextView textView82;
    @BindView(R.id.textView83)
    TextView textView83;
    @BindView(R.id.imageView69)
    ImageView imageView69;
    @BindView(R.id.imageView70)
    ImageView imageView70;
    @BindView(R.id.imageView71)
    ImageView imageView71;
    @BindView(R.id.imageView40)
    QMUIRadiusImageView imageView40;
    @BindView(R.id.imageView72)
    ImageView imageView72;
    @BindView(R.id.imageView73)
    ImageView imageView73;
    @BindView(R.id.textView84)
    TextView textView84;
    @BindView(R.id.imageView74)
    ImageView imageView74;
    @BindView(R.id.textView85)
    TextView textView85;
    @BindView(R.id.textView86)
    TextView textView86;
    @BindView(R.id.imageView41)
    QMUIRadiusImageView imageView41;
    @BindView(R.id.imageView76)
    ImageView imageView76;
    @BindView(R.id.textView87)
    TextView textView87;
    @BindView(R.id.imageView77)
    ImageView imageView77;
    @BindView(R.id.imageView43)
    QMUIRadiusImageView imageView43;
    @BindView(R.id.imageView80)
    ImageView imageView80;
    @BindView(R.id.textView89)
    TextView textView89;
    @BindView(R.id.imageView81)
    ImageView imageView81;
    @BindView(R.id.imageView44)
    QMUIRadiusImageView imageView44;
    @BindView(R.id.imageView82)
    ImageView imageView82;
    @BindView(R.id.textView90)
    TextView textView90;
    @BindView(R.id.imageView83)
    ImageView imageView83;
    @BindView(R.id.imageView45)
    QMUIRadiusImageView imageView45;
    @BindView(R.id.imageView84)
    ImageView imageView84;
    @BindView(R.id.textView91)
    TextView textView91;
    @BindView(R.id.imageView85)
    ImageView imageView85;
    @BindView(R.id.textView88)
    TextView textView88;
    @BindView(R.id.recycler18)
    RelativeLayout recycler18;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        textView88.setText("未认证");
        try {
            SharedPreferences sp = Objects.requireNonNull(getContext()).getSharedPreferences("User", Context.MODE_PRIVATE);
            long userid = sp.getLong("userid", 0);
            if (userid != 0) {
                imageView36.setVisibility(View.GONE);
                textView11.setVisibility(View.GONE);
                textView75.setVisibility(View.GONE);
                textView92.setVisibility(View.VISIBLE);
                textView93.setVisibility(View.VISIBLE);
                textView94.setVisibility(View.VISIBLE);
                textView95.setVisibility(View.VISIBLE);
                imageView37.setVisibility(View.VISIBLE);
            } else {
                imageView36.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView75.setVisibility(View.VISIBLE);
                textView92.setVisibility(View.GONE);
                textView93.setVisibility(View.GONE);
                textView94.setVisibility(View.GONE);
                textView95.setVisibility(View.GONE);
                imageView37.setVisibility(View.GONE);
            }

        } catch (Exception ignored) {
        }

        System.out.println("Date:" + DateUtil.getCurrentTimeYMDHMS());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static My newInstance() {
        Bundle args = new Bundle();
        My fragment = new My();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @OnClick({R.id.recycler18,R.id.imageView28, R.id.textView92, R.id.textView93, R.id.textView94, R.id.textView95, R.id.imageView68, R.id.imageView36, R.id.textView11, R.id.imageView37, R.id.textView76, R.id.textView77, R.id.textView78, R.id.textView79, R.id.textView80, R.id.textView81, R.id.textView82, R.id.textView83, R.id.imageView73, R.id.textView84, R.id.imageView74, R.id.textView85, R.id.imageView41, R.id.imageView76, R.id.textView87, R.id.imageView77, R.id.imageView43, R.id.imageView80, R.id.textView89, R.id.imageView81, R.id.imageView44, R.id.imageView82, R.id.textView90, R.id.imageView83, R.id.imageView45, R.id.imageView84, R.id.textView91, R.id.imageView85})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView68:
                Intent intent = new Intent(getContext(), my_set.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.imageView36:
            case R.id.textView11:
                //登陆
                Intent intent1 = new Intent(getContext(), login.class);
                startActivity(intent1);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.recycler18:
            case R.id.imageView28:
            case R.id.textView92:
            case R.id.textView93:
            case R.id.textView94:
            case R.id.textView95:
            case R.id.imageView37:
                Intent intent2 = new Intent(getContext(), modify_information.class);
                startActivity(intent2);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                //认证
                break;
            case R.id.textView76:
            case R.id.textView77:
                Intent intent3 = new Intent(getContext(), my_music.class);
                startActivity(intent3);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.textView78:
            case R.id.textView79:
                Intent intent4 = new Intent(getContext(), my_package.class);
                startActivity(intent4);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.textView80:
            case R.id.textView81:
                Intent intent5 = new Intent(getContext(), my_favorite.class);
                startActivity(intent5);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.textView82:
            case R.id.textView83:
                Intent intent6 = new Intent(getContext(), my_footprint.class);
                startActivity(intent6);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.imageView73:
            case R.id.textView84:
                Intent intent7 = new Intent(getContext(), chatroom.class);
                startActivity(intent7);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.imageView74:
            case R.id.textView85:
                Intent intent8 = new Intent(getContext(), homepage.class);
                startActivity(intent8);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.imageView41:
            case R.id.imageView76:
            case R.id.textView87:
            case R.id.imageView77:
                Intent intent9 = new Intent(getContext(), my_wallet.class);
                startActivity(intent9);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.imageView43:
            case R.id.imageView80:
            case R.id.textView89:
            case R.id.imageView81:
                Intent intent11 = new Intent(getContext(), my_music.class);
                startActivity(intent11);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.imageView44:
            case R.id.imageView82:
            case R.id.textView90:
            case R.id.imageView83:
                Intent intent12 = new Intent(getContext(), my_grade.class);
                startActivity(intent12);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
            case R.id.imageView45:
            case R.id.imageView84:
            case R.id.textView91:
            case R.id.imageView85:
                Intent intent13 = new Intent(getContext(), my_realname.class);
                startActivity(intent13);
                getActivity().overridePendingTransition(R.animator.anim_right_in, R.animator.anim_left_out);
                break;
        }
    }

}
