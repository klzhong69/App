package com.example.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app.Adapter.RoomheadchatAdapter;
import com.example.app.Adapter.RoomtxtAdapter;
import com.example.app.Entity.Roomheadchat;
import com.example.app.Entity.Roomtxt;

import java.util.ArrayList;

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
