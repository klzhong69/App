package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.wildma.pictureselector.Constant;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.agora.rtc.Constants;

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
    @BindView(R.id.view2)
    View view2;

    private String userid;
    private Context context;
    private String username;
    private String roomid;
    private String slogin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        textView88.setText("未认证");
        SharedPreferences sp = Objects.requireNonNull(getContext()).getSharedPreferences("User", Context.MODE_PRIVATE);
        username = sp.getString("nickname", "");
        textView92.setText(sp.getString("nickname", ""));
        Glide.with(My.this).load(sp.getString("avatarUrl", "")).into(imageView28);
        textView93.setText("ID " + sp.getString("userid", ""));
        textView94.setText("关注 " + sp.getString("followCount", ""));
        textView95.setText("粉丝 " + sp.getString("fansCount", ""));
        okgos();

        context = getContext();
        Window window = Objects.requireNonNull(getActivity()).getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        // ui |=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体的颜色为黑色
        ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //设置状态栏中字体颜色为白色
        decor.setSystemUiVisibility(ui);

        ViewGroup.LayoutParams para1;
        para1 = view2.getLayoutParams();
        para1.height = startup_page.height;
        view2.setLayoutParams(para1);




        try {

            userid = sp.getString("userid", "");

            if (!userid.equals("")) {
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

    private void okgos() {
        MyApp application = ((MyApp) getContext().getApplicationContext());
        SharedPreferences sp = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/user/getUserPersonal?token=" + token)
                .params("userId", userid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            try {

                                String nickname = prexiew.getData().get("nickname").getAsString();
                                String avatarUrl = prexiew.getData().get("avatarUrl").getAsString();
                                String signtureText = prexiew.getData().get("signtureText").getAsString();
                                String signtureVoiceUrl = prexiew.getData().get("signtureVoiceUrl").getAsString();
                                String followCount = prexiew.getData().get("followCount").getAsString();
                                String fansCount = prexiew.getData().get("fansCount").getAsString();
                                String musicCount = prexiew.getData().get("musicCount").getAsString();
                                String favoriteRoomCount = prexiew.getData().get("favoriteRoomCount").getAsString();
                                String roomHistoryCount = prexiew.getData().get("roomHistoryCount").getAsString();
                                String packageCount = prexiew.getData().get("packageCount").getAsString();
                                roomid = prexiew.getData().get("roomId").getAsString();

                                sp.edit().putString("followCount", followCount).apply();
                                sp.edit().putString("fansCount", fansCount).apply();

                                textView92.setText(nickname);
                                Glide.with(My.this).load(avatarUrl).into(imageView28);
                                textView94.setText("关注 " + followCount);
                                textView95.setText("粉丝 " + fansCount);
                                textView76.setText(musicCount);
                                textView78.setText(packageCount);
                                textView80.setText(favoriteRoomCount);
                                textView82.setText(roomHistoryCount);
                            } catch (Exception ignored) {
                            }

                        } else {
                            Toast.makeText(getContext(), prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }

    @OnClick({R.id.recycler18, R.id.imageView28, R.id.textView92, R.id.textView93, R.id.textView94, R.id.textView95, R.id.imageView68, R.id.imageView36, R.id.textView11, R.id.imageView37, R.id.textView76, R.id.textView77, R.id.textView78, R.id.textView79, R.id.textView80, R.id.textView81, R.id.textView82, R.id.textView83, R.id.imageView73, R.id.textView84, R.id.imageView74, R.id.textView85, R.id.imageView41, R.id.imageView76, R.id.textView87, R.id.imageView77, R.id.imageView43, R.id.imageView80, R.id.textView89, R.id.imageView81, R.id.imageView44, R.id.imageView82, R.id.textView90, R.id.imageView83, R.id.imageView45, R.id.imageView84, R.id.textView91, R.id.imageView85})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView68:
                Intent intent = new Intent(getContext(), my_set.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.scale_in_center, R.anim.scale_out_center);
                break;
            case R.id.imageView36:
            case R.id.textView11:
                //登陆
                Intent intent1 = new Intent(getContext(), login.class);
                intent1.putExtra("type", 0);
                startActivity(intent1);
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.animator.anim_bottom_in, R.animator.anim_bottom_out);
                break;
            case R.id.recycler18:
            case R.id.imageView28:
            case R.id.textView92:
            case R.id.textView93:
            case R.id.textView94:
            case R.id.textView95:
            case R.id.imageView37:
                if (!userid.equals("")) {
                    Intent intent2 = new Intent(getContext(), modify_information.class);
                    startActivity(intent2);
                    Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
                //认证
                break;
            case R.id.textView76:
            case R.id.textView77:
            case R.id.imageView43:
            case R.id.imageView80:
            case R.id.textView89:
            case R.id.imageView81:
                Intent intent3 = new Intent(getContext(), my_music.class);
                startActivity(intent3);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.textView78:
            case R.id.textView79:
                Intent intent4 = new Intent(getContext(), my_package.class);
                startActivity(intent4);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.textView80:
            case R.id.textView81:
                Intent intent5 = new Intent(getContext(), my_favorite.class);
                startActivity(intent5);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.textView82:
            case R.id.textView83:
                Intent intent6 = new Intent(getContext(), my_footprint.class);
                startActivity(intent6);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView73:
            case R.id.textView84:
                Intent intent7 = new Intent(getContext(), chatroom.class);
                intent7.putExtra(Constant.ACTION_KEY_CROLE, Constants.CLIENT_ROLE_AUDIENCE);
                intent7.putExtra(Constant.ACTION_KEY_ROOM_MODE, Constant.ChatRoomGamingHighQuality);
                intent7.putExtra(Constant.ACTION_KEY_ROOM_ID, userid);
                intent7.putExtra(Constant.ACTION_KEY_TITLE_NAME, username + "的房间");
                startActivity(intent7);
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.scale_in_center, R.anim.scale_out_center);
                break;
            case R.id.imageView74:
            case R.id.textView85:
                Intent intent8 = new Intent(getContext(), homepage.class);
                intent8.putExtra("id", Long.parseLong(userid));
                startActivity(intent8);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView41:
            case R.id.imageView76:
            case R.id.textView87:
            case R.id.imageView77:
                Intent intent9 = new Intent(getContext(), my_wallet.class);
                startActivity(intent9);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView44:
            case R.id.imageView82:
            case R.id.textView90:
            case R.id.imageView83:
                Intent intent12 = new Intent(getContext(), my_grade.class);
                startActivity(intent12);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.imageView45:
            case R.id.imageView84:
            case R.id.textView91:
            case R.id.imageView85:
                Intent intent13 = new Intent(getContext(), my_realname.class);
                startActivity(intent13);
                Objects.requireNonNull(getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }

    /*RequestOptions options = new RequestOptions()
            .placeholder(R.drawable.img_default)//图片加载出来前，显示的图片
            .fallback( R.drawable.img_blank) //url为空的时候,显示的图片
            .error(drawable.img_load_failure);//图片加载失败后，显示的图片*/

}
