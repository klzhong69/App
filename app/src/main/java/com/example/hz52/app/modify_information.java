package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.hz52.app.Adapter.ModifyViewAdapter;
import com.example.hz52.app.Entity.Modify;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.cofig.GlideEngine;
import com.example.hz52.app.cofig.OSSSet;
import com.example.hz52.app.cofig.Preview;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

;

public class modify_information extends AppCompatActivity {


    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.textView33)
    TextView textView33;
    @BindView(R.id.imageView2)
    QMUIRadiusImageView imageView2;
    @BindView(R.id.relativeLayout6)
    RelativeLayout relativeLayout6;
    @BindView(R.id.textView34)
    TextView textView34;
    @BindView(R.id.textView35)
    TextView textView35;
    @BindView(R.id.relativeLayout7)
    RelativeLayout relativeLayout7;
    @BindView(R.id.textView36)
    TextView textView36;
    @BindView(R.id.textView37)
    TextView textView37;
    @BindView(R.id.relativeLayout8)
    RelativeLayout relativeLayout8;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.recycler2)
    RecyclerView recycler2;
    private ArrayList<Modify> mData;
    private ModifyViewAdapter mAdapters;
    private GridLayoutManager mLayoutManager;
    public static Observer<Integer> observer;
    private int in;
    private String picturePath;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private SharedPreferences sp;
    private String userid;
    private String token;
    private String phone;
    private Boolean bool = false;
    private String nickname;
    private String signtureText;
    private String avatarUrl;
    private JsonArray album;
    private Context context;
    private QMUITipDialog tipDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_information);
        ButterKnife.bind(this);
        context = this;
        title.setText("修改信息");
        subtitle.setText("保存");
        sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        userid = sp.getString("userid", "");
        token = sp.getString("token", "");
        phone = sp.getString("phone", "");
        nickname = sp.getString("nickname", "");
        signtureText = sp.getString("signtureText", "签名");
        avatarUrl = sp.getString("avatarUrl", "");

        initData();

        textView35.setText(nickname);
        textView37.setText(signtureText);
        Glide.with(modify_information.this).load(avatarUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView2);

    }

    private void initData() {
        mData = new ArrayList<Modify>();
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/user/getUserAlbum?token=" + token)
                .params("userId", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        album = prexiew.getData().getAsJsonArray("album");

                        if (prexiew.getCode() == 0) {
                            for (int i = 0; i < album.size(); i++) {
                                Modify i1 = new Modify(album.get(i).getAsJsonObject().get("id").getAsInt(), album.get(i).getAsJsonObject().get("photoUrl").getAsString(), "1");
                                mData.add(i1);
                            }
                            Modify i2 = new Modify(0, "", "0");
                            mData.add(i2);
                            init();

                        } else {
                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void init() {
        //创建适配器，将数据传递给适配器
        mAdapters = new ModifyViewAdapter(context, mData);
        //设置适配器adapter
        recycler2.setAdapter(mAdapters);

        //多列布局
        mLayoutManager = new GridLayoutManager(context, 5);
        recycler2.setLayoutManager(mLayoutManager);


        recycler2.setItemAnimator(new DefaultItemAnimator());

        mAdapters.setOnItemClickListener(new ModifyViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int del = mData.size() - 1;
                if (position == del) {
                    in = 1;
                    PictureSelector.create(modify_information.this)
                            .openGallery(PictureMimeType.ofImage())
                            .isCamera(true)// 是否显示拍照按钮
                            .enableCrop(true)// 是否裁剪
                            .compress(true)// 是否压缩
                            .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                            .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                            .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                            .withAspectRatio(1,1)
                            .rotateEnabled(true) // 裁剪是否可旋转图片
                            .scaleEnabled(true)// 裁剪是否可放大缩小图片
                            .selectionMode(PictureConfig.SINGLE)
                            .loadImageEngine(GlideEngine.createGlideEngine())
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                } else {
                    Toast.makeText(modify_information.this, "第" + mData.get(position).getId(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
                showMessageNegativeDialog(position);
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler2.setItemAnimator(defaultItemAnimator);
    }

    private void showMessageNegativeDialog(int position) {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("确定要删除吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        Toast.makeText(modify_information.this, "删除成功", Toast.LENGTH_SHORT).show();
                        removePhoto(mData.get(position).getId(), position);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    @OnClick({R.id.fold, R.id.subtitle, R.id.imageView2, R.id.textView35, R.id.textView37})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                if(bool){
                    showMessagePositiveDialog();
                }else{
                    finish();
                }
                break;
            case R.id.subtitle:
                okgo();
                break;
            case R.id.imageView2:
                in = 0;
                PictureSelector.create(modify_information.this)
                        .openGallery(PictureMimeType.ofImage())
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(true)// 是否裁剪
                        .compress(true)// 是否压缩
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .circleDimmedLayer(true)// 是否圆形裁剪
                        .rotateEnabled(true) // 裁剪是否可旋转图片
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片
                        .selectionMode(PictureConfig.SINGLE)
                        .loadImageEngine(GlideEngine.createGlideEngine())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.textView35:
                showEditTextDialog();
                break;
            case R.id.textView37:
                showEditTextDialogs();
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
                    Observable.just(in)
                            .subscribe(new Observer<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(Integer integer) {
                                    switch (integer) {
                                        case 0:
                                            Glide.with(modify_information.this).load(picturePath).into(imageView2);
                                            okgoima(0);
                                            break;
                                        case 1:
                                            tipDialog = new QMUITipDialog.Builder(modify_information.this)
                                                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                                                    .setTipWord("正在加载")
                                                    .create();
                                            tipDialog.show();
                                            okgoima(1);
                                            break;
                                    }
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
    }

    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("修改未保存，确定要退出吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "确定", QMUIDialogAction.ACTION_PROP_POSITIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("标题")
                .setPlaceholder("在此输入您的昵称")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            textView35.setText(text.toString());
                            bool = true;
                            dialog.dismiss();
                        } else {
                            Toast.makeText(modify_information.this, "请填入昵称", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showEditTextDialogs() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("标题")
                .setPlaceholder("在此输入您的签名")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            textView37.setText(text);
                            bool = true;
                            dialog.dismiss();
                        } else {
                            Toast.makeText(modify_information.this, "请填入签名", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void okgo() {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/user/editUserInfo?token=" + token)
                .params("userId", userid)
                .params("nickname", textView35.getText().toString())
                .params("signtureText", textView37.getText().toString())
                .params("avatarUrl", avatarUrl)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {

                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                            sp.edit().putString("nickname", textView35.getText().toString()).apply();
                            sp.edit().putString("signtureText", textView37.getText().toString()).apply();
                            sp.edit().putString("avatarUrl", avatarUrl).apply();
                            finish();

                        } else {
                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    //上传图片到oss
    private void okgoima(int type) {
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
                            String name;
                            if (!AccessKeyId.equals("")) {
                                OSSSet.OSSClient(modify_information.this, AccessKeyId, AccessKeySecret, SecurityToken, region, bucket);
                                if (type == 0) {
                                    String upload = OSSSet.Upload(bucket, phone + "/" + "avatar.jpg", picturePath);
                                    if (upload.equals("UploadSuccess")) {
                                        avatarUrl = "http://hertz52-user.oss-cn-shenzhen.aliyuncs.com/" + phone + "/" + "avatar.jpg";
                                    }
                                    bool=true;
                                } else {
                                    name = "photo" + mData.size() + ".jpg";
                                    String upload = OSSSet.Upload(bucket, phone + "/" + name, picturePath);
                                    if (upload.equals("UploadSuccess")) {
                                        addPhoto("http://hertz52-user.oss-cn-shenzhen.aliyuncs.com/" + phone + "/" + name);
                                    }

                                }
                            }

                        } else {
                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }


                    }
                });

    }


    private void addPhoto(String url) {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/user/addPhoto?token=" + token)
                .params("userId", userid)
                .params("photoUrl", url)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            int id = prexiew.getData().get("id").getAsInt();
                            Modify modify = new Modify(id, url, "1");
                            mData.add(mData.size() - 1, modify);
                            mAdapters.notifyItemChanged(mData.size() - 1);
                            tipDialog.dismiss();
                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        } else {
                            tipDialog.dismiss();
                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    private void removePhoto(int id, int position) {
        MyApp application = ((MyApp) this.getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/user/removePhoto?token=" + token)
                .params("userId", userid)
                .params("photoId", id)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);

                        if (prexiew.getCode() == 0) {
                            mData.remove(position);
                            mAdapters.notifyItemRemoved(position);
                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(modify_information.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @Override
    public void onBackPressed() {
        if(bool){
            showMessagePositiveDialog();
        }else{
            finish();
        }
    }
}
