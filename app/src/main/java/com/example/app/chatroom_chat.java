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

    @BindView(R.id.imageView40)
    ImageView imageView40;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.textView121)
    TextView textView121;
    @BindView(R.id.textView122)
    TextView textView122;
    @BindView(R.id.textView123)
    TextView textView123;
    @BindView(R.id.imageView99)
    ImageView imageView99;
    @BindView(R.id.textView124)
    TextView textView124;
    @BindView(R.id.imageView100)
    ImageView imageView100;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.textView125)
    TextView textView125;
    @BindView(R.id.imageView17)
    QMUIRadiusImageView imageView17;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView126)
    TextView textView126;
    @BindView(R.id.imageView108)
    ImageView imageView108;
    @BindView(R.id.gridview)
    RecyclerView gridview;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.imageView96)
    ImageView imageView96;
    @BindView(R.id.imageView97)
    ImageView imageView97;
    @BindView(R.id.textView120)
    TextView textView120;
    @BindView(R.id.imageView98)
    ImageView imageView98;
    @BindView(R.id.imageView106)
    ImageView imageView106;
    @BindView(R.id.imageView101)
    ImageView imageView101;
    @BindView(R.id.imageView102)
    ImageView imageView102;
    @BindView(R.id.imageView103)
    ImageView imageView103;
    @BindView(R.id.imageView104)
    ImageView imageView104;
    @BindView(R.id.imageView105)
    ImageView imageView105;
    @BindView(R.id.relativeLayout3)
    RelativeLayout relativeLayout3;
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

        Context context = this;
        ChatRoomChatModel.initData();
        ChatRoomChatModel.initrecycler(context,recyclerview);
        ChatRoomChatModel.initrecyclers(context,gridview);


    }





}
