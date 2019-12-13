package com.example.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.FriendsAdapter;
import com.example.app.Adapter.MessageAdapter;
import com.example.app.Entity.Friends;
import com.example.app.Entity.Message;
import com.example.app.Model.ListModel;
import com.example.app.Model.MessFriendsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class mess_friends extends AppCompatActivity {


    @BindView(R.id.recycler12)
    RecyclerView recycler12;
    private List<Friends> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_friends);
        ButterKnife.bind(this);
        Context context = this;
        MessFriendsModel.initData();
        MessFriendsModel.initrecycler(context,recycler12,0);

    }


}
