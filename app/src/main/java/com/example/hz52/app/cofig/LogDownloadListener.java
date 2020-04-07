package com.example.hz52.app.cofig;

import android.content.Context;

import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Model.MusicModel;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.dao.mMusicDao;
import com.example.hz52.app.my_music;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class LogDownloadListener extends DownloadListener {

    private final QMUITipDialog tipDialog;
    private Mymusic music;

    public LogDownloadListener(Mymusic apk, Context context) {
        super("LogDownloadListener");
        music=apk;
        tipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在下载，请稍后")
                .create();
    }

    @Override
    public void onStart(Progress progress) {
        System.out.println("onStart: " + progress);
        tipDialog.show();
    }

    @Override
    public void onProgress(Progress progress) {
        //System.out.println("onProgress: " + progress);
    }

    @Override
    public void onError(Progress progress) {
        //System.out.println("onError: " + progress);
        progress.exception.printStackTrace();
    }

    @Override
    public void onFinish(File file, Progress progress) {
        Music mu = new Music();
        mu.setTime(music.getTime());
        mu.setName(music.getName());
        mu.setId(music.getId());
        mu.setFile(file.getPath());
        mMusicDao.insert(mu);

        for(int i=0;i<MusicModel.mArrayList.size();i++){
            if(MusicModel.mArrayList.get(i).getId().toString().equals(music.getId().toString())){
                MusicModel.mArrayList.get(i).setType("1");
                MusicModel.mAdapter.notifyItemChanged(i);
            }
        }
        System.out.println("onFinish: " + progress);
        tipDialog.dismiss();
    }

    @Override
    public void onRemove(Progress progress) {
        System.out.println("onRemove: " + progress);
    }
}
