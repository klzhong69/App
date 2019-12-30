package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Entity.MyApp;
import com.example.app.Model.HomePageModel;
import com.example.app.cofig.Preview;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class homepage extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView9)
    TextView textView9;
    @BindView(R.id.imageView35)
    QMUIRadiusImageView imageView35;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.relativeLayou)
    RelativeLayout relativeLayou;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.imageView17)
    QMUIRadiusImageView imageView17;
    @BindView(R.id.imageView7)
    QMUIRadiusImageView imageView7;
    @BindView(R.id.imageView8)
    QMUIRadiusImageView imageView8;
    @BindView(R.id.imageView9)
    QMUIRadiusImageView imageView9;
    @BindView(R.id.imageView10)
    QMUIRadiusImageView imageView10;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.imageView11)
    ImageView imageView11;
    @BindView(R.id.textView11)
    TextView textView11;
    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.imageView13)
    ImageView imageView13;
    @BindView(R.id.imageView14)
    QMUIRadiusImageView imageView14;
    @BindView(R.id.imageView15)
    QMUIRadiusImageView imageView15;
    @BindView(R.id.imageView16)
    QMUIRadiusImageView imageView16;
    @BindView(R.id.imageView18)
    QMUIRadiusImageView imageView18;
    @BindView(R.id.imageView19)
    QMUIRadiusImageView imageView19;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.imageView20)
    ImageView imageView20;
    @BindView(R.id.imageView22)
    QMUIRadiusImageView imageView22;
    @BindView(R.id.imageView26)
    QMUIRadiusImageView imageView26;
    @BindView(R.id.imageView27)
    QMUIRadiusImageView imageView27;
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.textView13)
    TextView textView13;
    @BindView(R.id.textView14)
    TextView textView14;
    @BindView(R.id.imageView28)
    QMUIRadiusImageView imageView28;
    @BindView(R.id.imageView24)
    QMUIRadiusImageView imageView24;
    @BindView(R.id.imageView29)
    QMUIRadiusImageView imageView29;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.textView20)
    TextView textView20;
    @BindView(R.id.textView21)
    TextView textView21;
    @BindView(R.id.textView22)
    TextView textView22;
    @BindView(R.id.textView26)
    TextView textView26;
    @BindView(R.id.textView27)
    TextView textView27;
    @BindView(R.id.textView28)
    TextView textView28;
    @BindView(R.id.textView29)
    TextView textView29;
    @BindView(R.id.textView30)
    TextView textView30;
    @BindView(R.id.relativeLayout3)
    RelativeLayout relativeLayout3;
    @BindView(R.id.imageView30)
    ImageView imageView30;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.relativeLayout4)
    RelativeLayout relativeLayout4;
    @BindView(R.id.imageView34)
    ImageView imageView34;
    @BindView(R.id.textView31)
    TextView textView31;
    @BindView(R.id.imageView36)
    ImageView imageView36;
    @BindView(R.id.textView32)
    TextView textView32;
    @BindView(R.id.relativeLayout5)
    RelativeLayout relativeLayout5;
    @BindView(R.id.largeLabel)
    RelativeLayout largeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
        title.setText("个人主页");
        subtitle.setText("修改信息");
        Context context = this;
        HomePageModel.initData();
        HomePageModel.initrecycler(context, recycler);
    }


    @OnClick({R.id.fold, R.id.subtitle,R.id.imageView34,R.id.textView31,R.id.imageView36,R.id.textView32})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.subtitle:

                break;
            case R.id.imageView34:
            case R.id.textView31:
                okgo();
                break;
            case R.id.imageView36:
            case R.id.textView32:
                Intent intent1 = new Intent(this, chat.class);
                //intent1.putExtra("sendname",mArrayList.get(position).getName());
               // intent1.putExtra("sendsrc",mArrayList.get(position).getImagesrc());
                startActivity(intent1);
                break;
        }
    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl()+"/app/user/follow?token="+application.getToken())
                .params("userId","923883237")
                .params("followId","692240405")
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if(prexiew.getCode()==0){
                            Toast.makeText(homepage.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();

                        }else if(prexiew.getCode()==40000){
                            Toast.makeText(homepage.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
