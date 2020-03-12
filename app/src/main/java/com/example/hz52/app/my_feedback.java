package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.DateUtil;
import com.example.hz52.app.cofig.OSSSet;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;
import com.wildma.pictureselector.PictureSelector;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class my_feedback extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView152)
    TextView textView152;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.textView153)
    TextView textView153;
    @BindView(R.id.imageView20)
    QMUIRadiusImageView imageView20;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textView24)
    TextView textView24;
    @BindView(R.id.textView25)
    TextView textView25;
    @BindView(R.id.imageView113)
    ImageView imageView113;
    @BindView(R.id.but)
    QMUIRoundButton but;
    @BindView(R.id.imageView8)
    ImageView imageView8;
    @BindView(R.id.imageView9)
    ImageView imageView9;
    @BindView(R.id.imageView14)
    ImageView imageView14;
    @BindView(R.id.imageView15)
    ImageView imageView15;
    private String picturePath;
    private int in =0;
    private HashMap<Integer, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feedback);
        ButterKnife.bind(this);
        title.setText("反馈意见");
        subtitle.setText("");
        editText.setText("");
        editText3.setText("");
        imageView8.setVisibility(View.GONE);
        imageView9.setVisibility(View.GONE);
        imageView14.setVisibility(View.GONE);
        imageView15.setVisibility(View.GONE);
    }

    @OnClick({R.id.fold, R.id.imageView113, R.id.but, R.id.imageView8, R.id.imageView9, R.id.imageView14, R.id.imageView15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.imageView113:
                in=0;
                PictureSelector
                        .create(my_feedback.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 400, 400, 1, 1);
                break;
            case R.id.but:
                if (editText.getText().equals("")) {
                    if (editText3.getText().equals("")) {
                        Toast.makeText(my_feedback.this, "提交成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(my_feedback.this, "请输入意见", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(my_feedback.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageView8:
                in=1;
                PictureSelector
                        .create(my_feedback.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 400, 400, 1, 1);
                break;
            case R.id.imageView9:
                in=2;
                PictureSelector
                        .create(my_feedback.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 400, 400, 1, 1);
                break;
            case R.id.imageView14:
                in=3;
                PictureSelector
                        .create(my_feedback.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 400, 400, 1, 1);
                break;
            case R.id.imageView15:
                in=4;
                PictureSelector
                        .create(my_feedback.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 400, 400, 1, 1);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid","");
        String token = sp.getString("token","");

        String[] pic = {};
        for(int i=0;i<map.size();i++){
            pic[i]=map.get(i);
        }
        OkGo.<String>post(application.getUrl()+"/app/user/getWallet?token="+token)
                .params("userId",userid)
                .params("phone",editText.getText().toString())
                .params("question",editText3.getText().toString())
                .params("pictures",pic.toString())
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);


                        if(prexiew.getCode()==0){

                            Toast.makeText(my_feedback.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();

                        }else if(prexiew.getCode()==40000){
                            Toast.makeText(my_feedback.this, prexiew.getMsg()+"", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                // imageView2.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                Observable.just(in)
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Integer integer) {
                                RequestOptions requestOptions = RequestOptions
                                        .circleCropTransform()
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true);
                                switch (integer){
                                    case 0:
                                        Glide.with(my_feedback.this).load(picturePath).apply(requestOptions).into(imageView113);
                                        imageView8.setVisibility(View.VISIBLE);
                                        break;
                                    case 1:
                                        Glide.with(my_feedback.this).load(picturePath).apply(requestOptions).into(imageView8);
                                        imageView9.setVisibility(View.VISIBLE);
                                        break;
                                    case 2:
                                        Glide.with(my_feedback.this).load(picturePath).apply(requestOptions).into(imageView9);
                                        imageView14.setVisibility(View.VISIBLE);
                                        break;
                                    case 3:
                                        Glide.with(my_feedback.this).load(picturePath).apply(requestOptions).into(imageView14);
                                        imageView15.setVisibility(View.VISIBLE);
                                        break;
                                    case 4:
                                        Glide.with(my_feedback.this).load(picturePath).apply(requestOptions).into(imageView15);
                                        break;
                                }

                                okgoima();


                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        }
    }

    private void okgoima() {
        MyApp application = ((MyApp) this.getApplicationContext());
        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid","");
        String token = sp.getString("token","");
        String phone = sp.getString("phone","");
        OkGo.<String>post(application.getUrl() + "/app/alioss/getUserUploadToken")
                .params("phone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            String AccessKeyId = prexiew.getData().get("AccessKeyId").getAsString();
                            String AccessKeySecret = prexiew.getData().get("AccessKeySecret").getAsString();
                            String SecurityToken = prexiew.getData().get("SecurityToken").getAsString();
                            String region = prexiew.getData().get("region").getAsString();
                            String bucket = prexiew.getData().get("bucket").getAsString();

                            if (!AccessKeyId.equals("")) {
                                OSSSet.OSSClient(my_feedback.this, AccessKeyId, AccessKeySecret, SecurityToken, region, bucket);
                                String name = "feedback"+ DateUtil.getCurrentMillis()+".jpg";
                                OSSSet.Callback(bucket, phone + "/"+ name, picturePath, userid);
                                map.put(in,name);
                            }

                        } else if (prexiew.getCode() == 40000) {
                            Toast.makeText(my_feedback.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }
}
