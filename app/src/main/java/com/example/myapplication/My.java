package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.dao.mUserDao;
import com.example.myapplication.entity.User;
import com.example.myapplication.gen.DaoMaster;
import com.example.myapplication.gen.DaoSession;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class My extends Fragment {
    Unbinder unbinder;

    private static DaoSession daoSession;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.textView36)
    TextView textView36;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.button7)
    Button button7;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        unbinder = ButterKnife.bind(this, view);
        try {
            SharedPreferences sp = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
            long userid = sp.getLong("userid", 0);
            textView38.setText("当前用户ID" + userid);
            List<User> user = mUserDao.query(userid);
            textView36.setText(user.get(0).getMemberNickname());
            Glide.with(this).load(user.get(0).getMemberIcon()).into(imageView2);
        } catch (Exception ignored) {
            textView38.setText("当前无用户,请登录");
        }
        setupDatabase();
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


    /**
     * 配置数据库
     */
    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getContext(), "User.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }


    @OnClick({R.id.button8, R.id.button9, R.id.button7})
    public void onViewClicked(View view) {
        SharedPreferences sp = getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        switch (view.getId()) {
            case R.id.button8:
                User user = new User();
                user.setState(true);
                user.setId(1L);
                user.setMemberId(134507L);
                user.setMemberIcon("https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
                user.setMemberNickname("周润发");
                user.setMemberSex(0);
                mUserDao.insert(user);
                sp.edit().putLong("userid", 1L).apply();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
                break;
            case R.id.button9:
                Intent intent1 = new Intent(getContext(), switchUser.class);
                startActivity(intent1);
                break;
            case R.id.button7:
                sp.edit().putLong("userid", 1L).apply();
                Intent intent2= new Intent(this.getContext(), MainActivity.class);
                intent2.putExtra("id", 3);
                startActivity(intent2);
                break;
        }
    }
}
