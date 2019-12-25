package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.didichuxing.doraemonkit.util.FileUtil;
import com.example.app.Adapter.MusicViewAdapter;
import com.example.app.Entity.Mymusic;
import com.example.app.Model.ChatModel;
import com.example.app.Model.GiftModel;
import com.example.app.Model.HoldModel;
import com.example.app.Sqlentity.Chat;
import com.example.app.Sqlentity.Music;
import com.example.app.Sqlentity.User;
import com.example.app.cofig.Initialization;
import com.example.app.dao.mChatDao;
import com.example.app.dao.mMusicDao;
import com.example.app.dao.mUserDao;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopups;
import com.wildma.pictureselector.Constant;
import com.ywl5320.libenum.MuteEnum;
import com.ywl5320.libenum.SampleRateEnum;
import com.ywl5320.libmusic.WlMusic;
import com.ywl5320.listener.OnCompleteListener;
import com.ywl5320.listener.OnPreparedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
    private List<Mymusic> mArrayList;
    private int i=0;
    private WlMusic wlMusic;
    private MusicViewAdapter mAdapter;
    private Observer<Integer> observer;
    private Observable<Integer> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        ButterKnife.bind(this);
        title.setText("我的音乐");
        subtitle.setText("添加");
        initData();
        Initialization.setupDatabaseMusic(this);
        FileDownloader.setup(this);


        music();
        //适配器
         mAdapter = new MusicViewAdapter(this, mArrayList);
        //设置适配器adapter
        recycler5.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler5.setLayoutManager(layoutManager);
        recycler5.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MusicViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mArrayList.get(position).getType().equals("0")){

                    Observable.just(1)
                            .subscribe(new Observer < Integer > () {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Integer integer) {
                                    List<Music> mu = mMusicDao.queryAll();
                                    for(i=0;i<mu.size();i++){
                                        System.out.println("music"+mu.get(i).getId());
                                    }
                                    Music music = new Music();
                                    music.setMusicid(mArrayList.get(position).getId());
                                    music.setName("music"+mArrayList.get(position).getId());
                                    music.setTime(mArrayList.get(position).getTime());
                                    mMusicDao.insert(music);
                                    download(mArrayList.get(position).getUrl(),"music"+mArrayList.get(position).getId(),position);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });


                }else if(mArrayList.get(position).getType().equals("1")){
                    i=position;
                    for(int j=0;j<mArrayList.size();j++){
                        mArrayList.get(j).setType("1");
                    }
                    mArrayList.get(i).setType("2");
                    mAdapter.notifyDataSetChanged();
                    System.out.println("状态："+wlMusic.isPlaying());
                    if(wlMusic.isPlaying()){
                        wlMusic.playNext(mArrayList.get(i).getUrl());
                    }else{
                        wlMusic.start();
                    }
                    System.out.println("时长"+wlMusic.getDuration());

                }else if(mArrayList.get(position).getType().equals("2")){
                    mArrayList.get(i).setType("1");
                    mAdapter.notifyDataSetChanged();
                    wlMusic.pause();
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

                showSimpleBottomSheetList(
                        true, true, false, "操作提示",
                        3, true, false);
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler5.setItemAnimator(defaultItemAnimator);


    }

    private void initData() {
        mArrayList = new ArrayList<Mymusic>();
        for (int i = 0; i < 3; i++) {
            String type = "0";
            if(i!=0){
                 type = "1";
            }
            Mymusic i1 = new Mymusic((long) i,"星坠-天空的幻想-林晓夜", "03.00", type, "0%","https://momeak.oss-cn-shenzhen.aliyuncs.com/music.mp3");
            mArrayList.add(i1);
        }


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

    private void showSimpleBottomSheetList(boolean gravityCenter,
                                           boolean addCancelBtn,
                                           boolean withIcon,
                                           CharSequence title,
                                           int itemCount,
                                           boolean allowDragDismiss,
                                           boolean withMark) {
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(this);
        builder.setGravityCenter(gravityCenter)
                .setTitle(title)
                .setAddCancelBtn(addCancelBtn)
                .setAllowDrag(allowDragDismiss)
                .setNeedRightMark(withMark)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        Toast.makeText(my_music.this, "Item " + (position + 1), Toast.LENGTH_SHORT).show();
                    }
                });
        if (withMark) {
            builder.setCheckedIndex(40);
        }

        builder.addItem("向上移");
        builder.addItem("向下移 ");
        builder.addItem("删除");
        builder.build().show();
    }

    private void music() {
        wlMusic = WlMusic.getInstance();
        wlMusic.setSource(mArrayList.get(i).getUrl()); //设置音频源
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

            }
        });

        wlMusic.setOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete() {
                i=i+1;
                observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                    @Override
                    public ObservableSource<? extends Integer> call() throws Exception {
                        return Observable.just(1);
                    }
                });
                observable.subscribe(observer);
                if(wlMusic.isPlaying()){
                    wlMusic.playNext(mArrayList.get(i).getUrl());
                }else{
                    wlMusic.start();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                if (integer == 0) {


                }else if(integer==1){
                    for(int j=0;j<mArrayList.size();j++){
                        mArrayList.get(j).setType("1");
                    }
                    mArrayList.get(i).setType("2");

                }else if(integer==2){
                    mArrayList.get(i).setType("1");
                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }


        };


    }

    private void download(String url,String name,int postion){
        String publicPath = Objects.requireNonNull(this.getExternalCacheDir()).getPath();
        String filePath = publicPath + "/music/"+name+".mp3";
        FileDownloader.getImpl().create(url)
                .setPath(filePath)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        int pross = (int) (soFarBytes/totalBytes)*100;
                        mArrayList.get(postion).setTxt(pross+"%");
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {

                        mArrayList.get(postion).setType("1");
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                    }
                }).start();
    }

}
