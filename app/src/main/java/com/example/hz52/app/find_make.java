package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.hz52.app.Adapter.FindmakeAdapter;
import com.example.hz52.app.Entity.Findmake;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.leefeng.lfrecyclerview.LFRecyclerView;
import me.leefeng.lfrecyclerview.OnItemClickListener;

public class find_make extends AppCompatActivity{


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.relativeLayout11)
    RelativeLayout relativeLayout11;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler15)
    LFRecyclerView recycler15;
    private ArrayList<Findmake> mArrayList = new ArrayList<Findmake>();
    private FindmakeAdapter mAdapter;
    private String userid;
    private String token;
    private String nickname;
    private String avatarUrl;
    private int a = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_make);
        ButterKnife.bind(this);
        title.setText("广播");
        subtitle.setText("");

    }


    @OnClick({R.id.fold, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.but:
                okgos();
                editText.setText("");
                break;
        }
    }


    private void okgos() {
        MyApp application = ((MyApp) this.getApplicationContext());

        OkGo.<String>post(application.getUrl() + "/app/user/sendBroadcast?token=" + token)
                .params("userId", userid)
                .params("broadcast", editText.getText().toString())
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            Toast.makeText(find_make.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();


                        } else  {
                            Toast.makeText(find_make.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

}
