package com.example.hz52.app;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Entity.Onlinepeople;
import com.example.hz52.app.Model.OnlineModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

public class room_online extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.recyclerc6)
    RecyclerView recyclerc6;

    public static Observer<Onlinepeople> observer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_online);
        ButterKnife.bind(this);

        context = this;
        title.setText("在线用户");
        subtitle.setText("");
        OnlineModel.initrecycler(context, recyclerc6);

    }

    @OnClick(R.id.fold)
    public void onViewClicked() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }



    @Override
    public void onBackPressed() {
        this.finish();overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


}
