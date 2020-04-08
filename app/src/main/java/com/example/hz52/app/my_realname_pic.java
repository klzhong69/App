package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.GlideEngine;
import com.example.hz52.app.cofig.OSSSet;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class my_realname_pic extends AppCompatActivity {

    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView131)
    TextView textView131;
    @BindView(R.id.textView132)
    TextView textView132;
    @BindView(R.id.textView141)
    TextView textView141;
    @BindView(R.id.imageView51)
    ImageView imageView51;
    @BindView(R.id.imageView109)
    ImageView imageView109;
    @BindView(R.id.imageView55)
    ImageView imageView55;
    @BindView(R.id.imageView110)
    ImageView imageView110;
    @BindView(R.id.textView142)
    TextView textView142;
    @BindView(R.id.textView143)
    TextView textView143;
    @BindView(R.id.but)
    QMUIRoundButton but;

    private Boolean a = false;
    private Boolean b = false;
    private String picturePath;
    private String userid;
    private String token;
    private String phone;
    private String avatarUrl1;
    private String avatarUrl2;
    private String realname;
    private String idCardNumber;
    private int sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_realname_pic);
        ButterKnife.bind(this);
        title.setText("实名认证");
        subtitle.setText("");

        Intent intent = getIntent();
        realname = intent.getStringExtra("realname");
        idCardNumber = intent.getStringExtra("idCardNumber");

        SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        token = sp.getString("token", "");
        phone = sp.getString("phone", "");
    }

    @OnClick({R.id.fold, R.id.imageView55, R.id.imageView110, R.id.but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                break;
            case R.id.imageView55:
                a = true;
                b = false;
                PictureSelector.create(my_realname_pic.this)
                        .openGallery(PictureMimeType.ofImage())
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .withAspectRatio(16, 9)
                        .rotateEnabled(true) // 裁剪是否可旋转图片
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片
                        .selectionMode(PictureConfig.SINGLE)
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.imageView110:
                b = true;
                a = false;
                PictureSelector.create(my_realname_pic.this)
                        .openGallery(PictureMimeType.ofImage())
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .withAspectRatio(16, 9)
                        .rotateEnabled(true) // 裁剪是否可旋转图片
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片
                        .selectionMode(PictureConfig.SINGLE)
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.but:
                if (sum>1) {
                    okgo();
                    Intent intent2 = new Intent(my_realname_pic.this, my_realnames.class);
                    startActivity(intent2);

                } else {
                    Toast.makeText(my_realname_pic.this, "请上传相应照片", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调
                if (PictureSelector.obtainMultipleResult(data).get(0).isCompressed()) {
                    picturePath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
                    sum++;
                    if (a) {
                        Glide.with(this).load(picturePath).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView55);
                    } else {
                        Glide.with(this).load(picturePath).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView110);
                    }
                    okgoima();
                }
            }
        }
    }


    //上传图片到oss
    private void okgoima() {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/alioss/getUserUploadToken")
                .params("phone", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            String AccessKeyId = prexiew.getData().get("AccessKeyId").getAsString();
                            String AccessKeySecret = prexiew.getData().get("AccessKeySecret").getAsString();
                            String SecurityToken = prexiew.getData().get("SecurityToken").getAsString();
                            String region = prexiew.getData().get("region").getAsString();
                            String bucket = prexiew.getData().get("bucket").getAsString();
                            if (!AccessKeyId.equals("")) {
                                OSSSet.OSSClient(my_realname_pic.this, AccessKeyId, AccessKeySecret, SecurityToken, region, bucket);
                                if (a) {
                                    String upload = OSSSet.Upload(bucket, phone + "/" + "IDcard1.jpg", picturePath);
                                    if (upload.equals("UploadSuccess")) {
                                        avatarUrl1 = "http://hertz52-user.oss-cn-shenzhen.aliyuncs.com/" + phone + "/" + "IDcard1.jpg";
                                    }
                                } else {
                                    String upload = OSSSet.Upload(bucket, phone + "/" + "IDcard2.jpg", picturePath);
                                    if (upload.equals("UploadSuccess")) {
                                        avatarUrl2 = "http://hertz52-user.oss-cn-shenzhen.aliyuncs.com/" + phone + "/" + "IDcard2.jpg";
                                    }
                                }
                            }

                        } else {
                            Toast.makeText(my_realname_pic.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/user/authenticate?token=" + token)
                .params("userId", userid)
                .params("realname", realname)
                .params("idCardNumber", idCardNumber)
                .params("idCardFrontUrl", avatarUrl1)
                .params("idCardBackUrl", avatarUrl2)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            Toast.makeText(my_realname_pic.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                            SharedPreferences sp = getSharedPreferences("User", Context.MODE_PRIVATE);
                            sp.edit().putString("verified", "true").apply();

                        } else {
                            Toast.makeText(my_realname_pic.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
