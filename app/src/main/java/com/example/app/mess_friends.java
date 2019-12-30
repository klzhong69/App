package com.example.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entity.Friends;
import com.example.app.Model.MessFriendsModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class mess_friends extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;
    @BindView(R.id.recycler12)
    RecyclerView recycler12;
    private List<Friends> mArrayList;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_friends);
        ButterKnife.bind(this);
        context = this;
        MessFriendsModel.initData(context, 1);
        MessFriendsModel.initrecycler(context, recycler12);
        textView6.setVisibility(View.GONE);
        textView7.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView9.setVisibility(View.GONE);
    }


    @OnClick({R.id.fold,  R.id.textView6, R.id.textView, R.id.textView7, R.id.textView9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.textView6:
                MessFriendsModel.initData(context, 1);
                MessFriendsModel.initrecycler(context, recycler12);
                textView6.setVisibility(View.GONE);
                textView7.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
                textView9.setVisibility(View.GONE);
                break;
            case R.id.textView:
                MessFriendsModel.initData(context, 2);
                MessFriendsModel.initrecycler(context, recycler12);
                textView6.setVisibility(View.VISIBLE);
                textView7.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                textView9.setVisibility(View.VISIBLE);
                break;
            case R.id.textView7:
                MessFriendsModel.initData(context, 1);
                MessFriendsModel.initrecycler(context, recycler12);
                break;
            case R.id.textView9:
                MessFriendsModel.initData(context, 2);
                MessFriendsModel.initrecycler(context, recycler12);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
