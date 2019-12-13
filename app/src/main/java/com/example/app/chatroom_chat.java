package com.example.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.app.Adapter.RoomheadchatAdapter;
import com.example.app.Adapter.RoomtxtAdapter;
import com.example.app.Entity.Roomheadchat;
import com.example.app.Entity.Roomtxt;
import com.example.app.Model.ChatRoomChatModel;
import com.example.app.Model.ChatRoomModel;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class chatroom_chat extends AppCompatActivity {


    private GridLayoutManager mLayoutManager;
    private RoomheadchatAdapter mAdapters;
    private ArrayList<Roomheadchat> mData;
    private ArrayList<Roomtxt> mEntityList;
    private RoomtxtAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom_chat);
        ButterKnife.bind(this);

        /*Context context = this;
        ChatRoomChatModel.initData();
        ChatRoomChatModel.initrecycler(context,recyclerview);
        ChatRoomChatModel.initrecyclers(context,gridview);*/


    }





}
