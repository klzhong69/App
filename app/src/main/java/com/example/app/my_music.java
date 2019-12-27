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

import androidx.annotation.NonNull;
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
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
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
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
    private List<Long> mArrayLists;
    private WlMusic wlMusic;
    private MusicViewAdapter mAdapter;
    private Observer<Integer> observer;
    private Observable<Integer> observable;
    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    private Observer<Integer> observers;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        ButterKnife.bind(this);
        title.setText("我的音乐");
        subtitle.setText("添加");

        Initialization.setupDatabaseMusic(this);
        FileDownloader.setup(this);


        initData();
        List<Music> music = mMusicDao.queryAll();
        mArrayLists = new ArrayList<Long>();
        for (int i = 0; i < mArrayList.size(); i++) {

            int a = 0;
            for (int j = 0; j < music.size(); j++) {

                if (music.get(j).getId().equals(mArrayList.get(i).getId())) {
                    a++;
                }
            }
            if (a == 0) {
                mArrayLists.add(mArrayList.get(i).getId());

            }
        }

        if(mArrayLists.size()>0){
            showMessagePositiveDialog();
        }

        observers = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Music music = new Music();
                music.setTime(mArrayList.get(integer).getTime());
                music.setName(mArrayList.get(integer).getName());
                music.setId(mArrayList.get(integer).getId());
                mMusicDao.insert(music);
                download(mArrayList.get(integer).getUrl(), "music" + mArrayList.get(integer).getId(), integer);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
            }

        };

        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                for (int j = 0; j < mArrayList.size(); j++) {
                    mArrayList.get(j).setType("1");
                }
                mArrayList.get(integer).setType("2");
                mAdapter.notifyDataSetChanged();
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

    private void initData() {

        mArrayList = new ArrayList<Mymusic>();
        for (int i = 0; i < 3; i++) {
            String publicPath = Objects.requireNonNull(this.getExternalCacheDir()).getPath();
            String filePath = publicPath + "/music/music" + i + ".mp3";
            Mymusic i1 = new Mymusic((long) i, "星坠-天空的幻想-林晓夜"+i, "03.00", "1", "100%", filePath);
            mArrayList.add(i1);
        }


        //适配器
        mAdapter = new MusicViewAdapter(this, mArrayList);
        //设置适配器adapter
        recycler5.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

         layoutManager = new LinearLayoutManager(this) {
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
                if (mArrayList.get(position).getType().equals("0")) {
                    showMessagePositiveDialog();

                } else if (mArrayList.get(position).getType().equals("1")) {
                    Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(position);
                        }
                    });
                    observable.subscribe(observer);

                } else if (mArrayList.get(position).getType().equals("2")) {
                    mArrayList.get(position).setType("1");
                    mAdapter.notifyItemChanged(position);
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
                        if(position==0){
                            int firstItem = layoutManager.findFirstVisibleItemPosition();
                            int lastItem = layoutManager.findLastVisibleItemPosition();
                            if (0 <= firstItem) {
                                recycler5.scrollToPosition(0);
                            } else if (0 <= lastItem) {
                                int top = recycler5.getChildAt(0 - firstItem).getTop();
                                recycler5.scrollBy(0, top);
                            } else {
                                recycler5.scrollToPosition(0);
                            }

                        }else if(position==1){

                        }
                    }
                });
        if (withMark) {
            builder.setCheckedIndex(40);
        }

        builder.addItem("置顶");
        builder.addItem("删除");
        builder.build().show();
    }

    private void showMessagePositiveDialog() {
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("提示")
                .setMessage("是否开始同步？")
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
                        for (int i = 0; i < mArrayLists.size(); i++) {
                            int finalI = i;
                            Observable<Integer> observables = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                                @Override
                                public ObservableSource<? extends Integer> call() throws Exception {
                                    return Observable.just(finalI);
                                }
                            });
                            observables.subscribe(observers);
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void music(int postion) {
        wlMusic = WlMusic.getInstance();
        wlMusic.setSource(mArrayList.get(postion).getUrl()); //设置音频源
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
            wlMusic.playNext(mArrayList.get(postion).getUrl());
        }
        System.out.println("时长" + wlMusic.getDuration());

    }


    private void download(String url, String name, int posetion) {
        String publicPath = Objects.requireNonNull(this.getExternalCacheDir()).getPath();
        String filePath = publicPath + "/music/" + name + ".mp3";
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
                        int percent = (int) ((double) soFarBytes / (double) totalBytes * 100);
                        mArrayList.get(posetion).setTxt(percent + "%");
                        mAdapter.notifyItemChanged(posetion);

                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {

                        mAdapter.refresh(posetion);

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        while (task.getSmallFileSoFarBytes() != task.getSmallFileTotalBytes()) {
                            int percent = (int) ((double) task.getSmallFileSoFarBytes() / (double) task.getSmallFileTotalBytes() * 100);
                            mArrayList.get(posetion).setTxt(percent + "%");
                            mAdapter.notifyItemChanged(posetion);
                        }
                    }
                }).start();

    }

    @Override
    public void onBackPressed() {
        this.finish();
        overridePendingTransition(R.animator.anim_left_in, R.animator.anim_right_out);
    }

}
