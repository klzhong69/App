package com.example.hz52.app.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hz52.app.Adapter.MusicViewAdapter;
import com.example.hz52.app.Entity.MyApp;
import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.cofig.LogDownloadListener;
import com.example.hz52.app.cofig.Preview;
import com.example.hz52.app.dao.mMusicDao;
import com.example.hz52.app.my_music;
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
import com.ywl5320.libmusic.WlMusic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

import static android.os.Environment.DIRECTORY_MUSIC;

public class MusicModel {

    public static List<Mymusic> mArrayList;
    public static List<Mymusic> mArray;
    public static MusicViewAdapter mAdapter;
    private static LinearLayoutManager layoutManager;
    private static int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;


    public static void initData(Context context) {

        mArrayList = new ArrayList<Mymusic>();
        mArray = new ArrayList<Mymusic>();


        MyApp application = ((MyApp) context.getApplicationContext());
        SharedPreferences sp = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        String userid = sp.getString("userid", "");
        String token = sp.getString("token", "");
        OkGo.<String>post(application.getUrl() + "/app/user/getMusicList?token=" + token)
                .params("userId", userid)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson = new Gson();
                        Preview prexiew = gson.fromJson(response.body(), Preview.class);
                        JsonArray music = prexiew.getData().getAsJsonArray("music");

                        if (prexiew.getCode() == 0) {
                            if (music != null) {
                                /*for (int i = 0; i < music.size(); i++) {

                                    Mymusic i1 = new Mymusic(music.get(i).getAsJsonObject().get("id").getAsLong(), music.get(i).getAsJsonObject().get("musicName").getAsString(), music.get(i).getAsJsonObject().get("duration").getAsString(), "1", "", music.get(i).getAsJsonObject().get("md5").getAsString());
                                    mArrayList.add(i1);
                                }*/
                                Mymusic apk1 = new Mymusic(1L,"歌曲1","03:52","1","","https://momeak.oss-cn-shenzhen.aliyuncs.com/baidu.mp3");
                                mArrayList.add(apk1);
                                Mymusic apk2 = new Mymusic(2L,"歌曲2","04.25","1","","https://momeak.oss-cn-shenzhen.aliyuncs.com/music.mp3");
                                mArrayList.add(apk2);

                                List<Music> musics = mMusicDao.queryAll();
                                System.out.println("测试"+musics.size());
                                for (int i = 0; i < mArrayList.size(); i++) {
                                    int a = 0;
                                    for (int j = 0; j < musics.size(); j++) {
                                        if (musics.get(j).getId().toString().equals(mArrayList.get(i).getId().toString())) {
                                            a++;
                                        }
                                    }
                                    if (a == 0) {
                                        mArrayList.get(i).setType("0");
                                        mArray.add(mArrayList.get(i));
                                    }else{
                                        mArrayList.get(i).setUrl("0");
                                    }
                                }

                                if (mArray.size() > 0) {
                                    showMessagePositiveDialog(context);
                                }
                            }
                        } else {
                            Toast.makeText(context, prexiew.getMsg() + "", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }

    public static void init(Context context, RecyclerView recycler) {
      /*  for (int i = 0; i < mArray.size(); i++) {
            String publicPath = Objects.requireNonNull(this.getExternalCacheDir()).getPath();
            String filePath = publicPath + "/music/" + mArray.get(i).getName();
            Mymusic i1 = new Mymusic(mArray.get(i).getId(), mArray.get(i).getName(), mArray.get(i).getTime(), mArray.get(i).getType(), mArray.get(i).getTxt(), filePath);
            mArrayList.add(i1);
        }*/

        //适配器
        mAdapter = new MusicViewAdapter(context, mArrayList);
        //设置适配器adapter
        recycler.setAdapter(mAdapter);

        /*LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLinearLayoutManager);*/

        layoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycler.setLayoutManager(layoutManager);
        recycler.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MusicViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mArrayList.get(position).getType().equals("0")) {
                    showMessagePositiveDialog(context);

                } else if (mArrayList.get(position).getType().equals("1")) {
                    Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(position);
                        }
                    });
                    observable.subscribe(my_music.observer);

                } else if (mArrayList.get(position).getType().equals("2")) {
                    mArrayList.get(position).setType("1");
                    mAdapter.notifyItemChanged(position);
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {

                showSimpleBottomSheetList(
                        true, true, false, "操作提示",
                        3, true, false,context,recycler);
            }
        });

        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(200);
        defaultItemAnimator.setRemoveDuration(200);
        recycler.setItemAnimator(defaultItemAnimator);

    }

    public static void showMessagePositiveDialog(Context context) {
        new QMUIDialog.MessageDialogBuilder(context)
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
                        startAll(context);
                        dialog.dismiss();
                    }
                })
                .create(mCurrentDialogStyle).show();
    }


    private static void startAll(Context context) {
        for (Mymusic apk : mArray) {
            GetRequest<File> request = OkGo.<File>get(apk.getUrl());
            OkDownload.request(apk.getId().toString(), request)
                    .save()
                    .folder(Objects.requireNonNull(context.getExternalFilesDir(DIRECTORY_MUSIC)).getPath())
                    .register(new LogDownloadListener())
                    .start();
            mAdapter.notifyDataSetChanged();
        }
    }


    private static void showSimpleBottomSheetList(boolean gravityCenter,
                                                  boolean addCancelBtn,
                                                  boolean withIcon,
                                                  CharSequence title,
                                                  int itemCount,
                                                  boolean allowDragDismiss,
                                                  boolean withMark,
                                                  Context context,
                                                  RecyclerView recycler5) {
        QMUIBottomSheet.BottomListSheetBuilder builder = new QMUIBottomSheet.BottomListSheetBuilder(context);
        builder.setGravityCenter(gravityCenter)
                .setTitle(title)
                .setAddCancelBtn(addCancelBtn)
                .setAllowDrag(allowDragDismiss)
                .setNeedRightMark(withMark)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        if (position == 0) {
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

                        } else if (position == 1) {

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
}
