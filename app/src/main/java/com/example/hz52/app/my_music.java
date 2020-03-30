package com.example.hz52.app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.MusicViewAdapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Model.MusicModel;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.cofig.Initialization;
import com.example.hz52.app.cofig.LogDownloadListener;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mMusicDao;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.ywl5320.libenum.MuteEnum;
import com.ywl5320.libmusic.WlMusic;
import com.ywl5320.listener.OnCompleteListener;
import com.ywl5320.listener.OnPreparedListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static android.os.Environment.DIRECTORY_MUSIC;

public class my_music extends AppCompatActivity {


    @BindView(R.id.recycler5)
    RecyclerView recycler5;
    @BindView(R.id.fold)
    ImageView fold;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.imageView141)
    ImageView imageView141;
    private WlMusic wlMusic;
    public static Observer<Integer> observer;
    private Observer<String> observers;
    private static final int REQUEST_PERMISSION_STORAGE = 0x01;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        ButterKnife.bind(this);
        title.setText("我的音乐");
        subtitle.setText("添加");
        context = this;
        Initialization.setupDatabaseMusic(this);

        MusicModel.initData(context);
        MusicModel.init(context,recycler5);

        if(isGrantExternalRW(this)){
            MusicModel.showMessagePositiveDialog(context);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                for (int j = 0; j < MusicModel.mArrayList.size(); j++) {
                    MusicModel.mArrayList.get(j).setType("1");
                }
                MusicModel.mArrayList.get(integer).setType("2");
                MusicModel.mAdapter.notifyItemChanged(integer);
                music(integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }

        };
    }


    @OnClick({R.id.fold, R.id.subtitle, R.id.imageView141})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fold:
                this.finish();
                overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
                break;
            case R.id.subtitle:
                Intent intent1 = new Intent(my_music.this, scan_code.class);
                startActivity(intent1);
                break;
            case R.id.imageView141:
                Intent intent2 = new Intent(my_music.this, my_music_search.class);
                startActivity(intent2);
                break;
        }
    }


    /**
     * 检查SD卡权限
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
            return false;
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //获取权限
                Toast.makeText(my_music.this, "获取权限", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(my_music.this, "权限被禁止，无法下载文件！", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void music(int postion) {
        wlMusic = WlMusic.getInstance();
        wlMusic.setSource(MusicModel.mArrayList.get(postion).getUrl()); //设置音频源
        wlMusic.setCallBackPcmData(false);//是否返回音频PCM数据
        wlMusic.setShowPCMDB(false);//是否返回音频分贝大小
        wlMusic.setPlayCircle(false); //设置不间断循环播放音频
        wlMusic.setVolume(65); //设置音量 65%
        wlMusic.setPlaySpeed(1.0f); //设置播放速度 (1.0正常) 范围：0.25---4.0f
        wlMusic.setPlayPitch(1.0f); //设置播放速度 (1.0正常) 范围：0.25---4.0f
        wlMusic.setMute(MuteEnum.MUTE_CENTER); //设置立体声（左声道、右声道和立体声）
        wlMusic.setConvertSampleRate(null);//设定恒定采样率（null为取消）
        wlMusic.prePared();

        wlMusic.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {
                wlMusic.start();
            }
        });

        wlMusic.setOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                System.out.println("完成");
                Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> call() throws Exception {
                        return Observable.just(postion + 1);
                    }
                });
                observable.subscribe(observer);
            }
        });

        System.out.println("状态：" + wlMusic.isPlaying());
        if (wlMusic.isPlaying()) {
            wlMusic.playNext(MusicModel.mArrayList.get(postion).getUrl());
        }
        System.out.println("时长" + wlMusic.getDuration());

    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }

}
