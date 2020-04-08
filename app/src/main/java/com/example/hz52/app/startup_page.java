package com.example.hz52.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Entity.Roomgift;
import com.example.hz52.app.MQ.MqttMessageService;
import com.example.hz52.app.Sqlentity.Gift;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.cofig.LogDownloadGift;
import com.example.hz52.app.cofig.LogDownloadListener;
import com.example.hz52.app.cofig.OSSSet;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mGiftDao;
import com.example.hz52.app.dao.mMusicDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MUSIC;

public class startup_page extends AppCompatActivity {

    private static Context context;
    @BindView(R.id.imageViewi3)
    QMUIRadiusImageView imageViewi3;
    @BindView(R.id.textView188)
    TextView textView188;
    @BindView(R.id.textView189)
    TextView textView189;
    private CountDownTimer countDownTimer;
    public static int height;
    public static int cardheight;
    private String TAG = "MQTT";
    public static String intername = "";
    private String slogin;
    public static List<Roomgift> mArrayList;
    public static List<Roomgift> mArray;
    public static String buckets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_page);
        ButterKnife.bind(this);
        context = this;
        Initialization.setupDatabaseGift(this);

        countDownTimer = new CountDownTimer(3000 + 500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView189.setText(String.valueOf((millisUntilFinished / 1000)));
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(startup_page.this, MainActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_tran_in, R.anim.anim_tran_out);

            }
        }.start();

        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        height = resources.getDimensionPixelSize(resourceId);

        Rect outSize = new Rect();
        getWindowManager().getDefaultDisplay().getRectSize(outSize);
        int left = outSize.left;
        int top = outSize.top;
        int right = outSize.right;
        int bottom = outSize.bottom;
        cardheight = bottom - height - dip2px(this, 80);

        System.out.println("系统栏高度" + height);
        isConnectIsNormal();

        SharedPreferences sp = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        slogin = sp.getString("login", "");

        initData();
    }


    private void initData() {
        mArrayList = new ArrayList<Roomgift>();
        mArray = new ArrayList<Roomgift>();

        MyApp application = ((MyApp) getApplicationContext());
        OkGo.<String>post(application.getUrl() + "/app/resource/getAllGift")
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        JsonArray gifts = prexiew.getData().getAsJsonArray("gifts");

                        if (prexiew.getCode() == 0) {
                            if (gifts != null) {
                                for (int i = 0; i < gifts.size(); i++) {

                                    Roomgift i1 = new Roomgift(gifts.get(i).getAsJsonObject().get("id").getAsString(), gifts.get(i).getAsJsonObject().get("smallPicUrl").getAsString(), gifts.get(i).getAsJsonObject().get("giftName").getAsString(), gifts.get(i).getAsJsonObject().get("price").getAsString(), gifts.get(i).getAsJsonObject().get("svgaUrl").getAsString());
                                    mArrayList.add(i1);
                                }

                                List<Gift> gift = mGiftDao.queryAll();
                                for (int i = 0; i < mArrayList.size(); i++) {
                                    int a = 0;
                                    for (int j = 0; j < gift.size(); j++) {

                                        if (gift.get(j).getId().toString().equals(mArrayList.get(i).getId())) {
                                            a++;
                                        }
                                    }
                                    if (a == 0) {
                                        mArray.add(mArrayList.get(i));
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(startup_page.this, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    /**
     * 判断网络是否连接
     */
    public boolean isConnectIsNormal() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            intername = name;
            return true;
        } else {
            Toast.makeText(startup_page.this, " 无网络连接", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        System.out.println("dpi" + scale);
        return (int) (dpValue * scale + 0.5f);
    }


    @OnClick({R.id.imageViewi3, R.id.textView188, R.id.textView189})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageViewi3:
            case R.id.textView188:
            case R.id.textView189:
                countDownTimer.cancel();
                countDownTimer.onFinish();
                break;
        }
    }
}
