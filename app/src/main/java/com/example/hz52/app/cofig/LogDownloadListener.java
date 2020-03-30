package com.example.hz52.app.cofig;

import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Model.MusicModel;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.dao.mMusicDao;
import com.example.hz52.app.my_music;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class LogDownloadListener extends DownloadListener {

    private Mymusic music;

    public LogDownloadListener() {
        super("LogDownloadListener");
    }

    @Override
    public void onStart(Progress progress) {
        System.out.println("onStart: " + progress);
    }

    @Override
    public void onProgress(Progress progress) {
        for(int i=0;i<MusicModel.mArray.size();i++){
            if(MusicModel.mArray.get(i).getId().toString().equals(progress.tag)){
                music = MusicModel.mArray.get(i);
                music.setTxt(progress.fraction*100+"%");
            }
        }
        System.out.println("onProgress: " + progress);
    }

    @Override
    public void onError(Progress progress) {
        System.out.println("onError: " + progress);
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

        System.out.println("onFinish: " + progress);
    }

    @Override
    public void onRemove(Progress progress) {
        System.out.println("onRemove: " + progress);
    }
}
