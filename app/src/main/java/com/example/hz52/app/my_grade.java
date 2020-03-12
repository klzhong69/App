package com.example.hz52.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.Gradesum;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class my_grade extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView17)
    QMUIRadiusImageView imageView17;
    @BindView(R.id.imageView59)
    ImageView imageView59;
    @BindView(R.id.textView51)
    TextView textView51;
    @BindView(R.id.imageView60)
    ImageView imageView60;
    @BindView(R.id.textView52)
    TextView textView52;
    @BindView(R.id.circleProgressBar)
    QMUIProgressBar circleProgressBar;
    @BindView(R.id.textView55)
    TextView textView55;
    @BindView(R.id.textView56)
    TextView textView56;
    @BindView(R.id.imageView57)
    QMUIRadiusImageView imageView57;
    @BindView(R.id.imageView61)
    ImageView imageView61;
    @BindView(R.id.textView58)
    TextView textView58;
    @BindView(R.id.imageView62)
    ImageView imageView62;
    @BindView(R.id.textView59)
    TextView textView59;
    @BindView(R.id.circleProgressBar2)
    QMUIProgressBar circleProgressBar2;
    @BindView(R.id.textView61)
    TextView textView61;
    @BindView(R.id.textView62)
    TextView textView62;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grade);
        ButterKnife.bind(this);
        title.setText("我的等级");
        subtitle.setText("");
        circleProgressBar.setMaxValue(100);
        circleProgressBar2.setMaxValue(100);

        okgo();

        circleProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });

        circleProgressBar2.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
            @Override
            public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                return 100 * value / maxValue + "%";
            }
        });


    }


    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid","");
        String token = sp.getString("token","");
        OkGo.<String>post(application.getUrl()+"/app/user/getLevel?token="+token)
                .params("userId",userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if(prexiew.getCode()==0){

                            String gold = prexiew.getData().get("gold").getAsString();
                            String diamond = prexiew.getData().get("diamond").getAsString();
                            String rankGold = prexiew.getData().get("rankGold").getAsString();
                            String rankDiamond = prexiew.getData().get("rankDiamond").getAsString();

                            textView55.setText("财富 "+gold);
                            textView61.setText("魅力 "+diamond);
                            textView52.setText(rankGold);
                            textView59.setText(rankDiamond);

                           int goldn = (int) Gradesum.grades(Integer.parseInt(gold)).get("n");
                            int goldf = (int) Gradesum.grades(Integer.parseInt(gold)).get("f");
                            int diamondn = (int) Gradesum.diamonds(Integer.parseInt(diamond)).get("n");
                            int diamondf = (int) Gradesum.diamonds(Integer.parseInt(diamond)).get("f");


                            int goldnum = goldf -Integer.parseInt(gold);
                            int diamondnum = diamondf -Integer.parseInt(diamond);

                            textView56.setText("距离升级还需要消耗"+goldnum+"金币");
                            textView62.setText("距离升级还需要获得"+diamondnum+"钻石");

                            int goldsum = (int) ((double) Integer.parseInt(gold) / (double) goldf * 100);

                            int diamondsum = (int) ((double) Integer.parseInt(diamond) / (double) diamondf * 100);

                            circleProgressBar.setProgress(goldsum);
                            circleProgressBar2.setProgress(diamondsum);

                        }else if(prexiew.getCode()==40000){
                            Toast.makeText(my_grade.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @OnClick({R.id.fold, R.id.title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.title:

                break;

        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }
}
