package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entity.Message;
import com.example.app.Model.ChatModel;
import com.example.app.Model.MessModel;
import com.example.app.Sqlentity.Chat;
import com.example.app.Sqlentity.Conver;
import com.example.app.cofig.Initialization;
import com.example.app.dao.mChatDao;
import com.example.app.dao.mConverDao;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Messages extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.imageView78)
    ImageView imageView78;
    @BindView(R.id.rect_view)
    RelativeLayout rectView;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.textView66)
    TextView textView66;
    @BindView(R.id.textView67)
    TextView textView67;
    @BindView(R.id.imageView67)
    ImageView imageView67;
    @BindView(R.id.textView68)
    TextView textView68;
    @BindView(R.id.textView96)
    TextView textView96;
    @BindView(R.id.rect_views)
    RelativeLayout rectViews;
    @BindView(R.id.recycler10)
    RecyclerView recycler10;

    private ArrayList<Message> mArrayList;
    private ArrayList<Message> mArrayLists;
    private Observer<Integer> observer;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mess_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        Context context = getContext();
        Initialization.setupDatabaseConver(getContext());
        MessModel.initData();
        MessModel.initrecycler(context, recycler10,0);

        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Messages newInstance() {
        Bundle args = new Bundle();
        Messages fragment = new Messages();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.imageView78, R.id.rect_views})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView78:
                Intent intent2 = new Intent(getContext(), mess_friends.class);
                startActivity(intent2);
                /*Conver conver = new Conver();
                Long couver = 123456L+234567L;
                conver.setConversations(couver);
                conver.setSendId(234567L);
                conver.setSendname("苗苗");
                conver.setSendsrc("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg");
                conver.setType(0);
                mConverDao.insert(conver);

                Conver conver2 = new Conver();
                Long couver2 = 123456L+345678L;
                conver2.setConversations(couver2);
                conver2.setSendId(345678L);
                conver2.setSendname("苗苗");
                conver2.setSendsrc("https://momeak.oss-cn-shenzhen.aliyuncs.com/h2.jpg");
                conver2.setType(1);
                mConverDao.insert(conver2);*/
                break;
            case R.id.rect_views:
                Intent intent3 = new Intent(getContext(), system_information.class);
                startActivity(intent3);
                break;
        }
    }
}
