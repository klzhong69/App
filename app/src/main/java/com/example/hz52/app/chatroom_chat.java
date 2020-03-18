package com.example.hz52.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hz52.app.Adapter.RoomheadchatAdapter;
import com.example.hz52.app.Adapter.RoomtxtAdapter;
import com.example.hz52.app.Entity.Roomheadchat;
import com.example.hz52.app.Entity.Roomtxt;

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
