package com.example.hz52.app.cofig;

import android.content.Context;

import com.example.hz52.app.Entity.Mymusic;
import com.example.hz52.app.Entity.Roomgift;
import com.example.hz52.app.Model.MusicModel;
import com.example.hz52.app.Sqlentity.Gift;
import com.example.hz52.app.Sqlentity.Music;
import com.example.hz52.app.dao.mGiftDao;
import com.example.hz52.app.dao.mMusicDao;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.io.File;

public class LogDownloadGift extends DownloadListener {

    private Roomgift gift;

    public LogDownloadGift(Roomgift apk, Context context) {
        super("LogDownloadListener");
        gift=apk;
    }

    @Override
    public void onStart(Progress progress) {
        System.out.println("onStart: " + progress);
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
        Gift mu = new Gift();
        mu.setId(Long.valueOf(gift.getId()));
        mu.setGiftName(gift.getGiftname());
        mu.setPrice(gift.getNum());
        mu.setSmallPicUrl("http://hertz52-user.oss-cn-shenzhen.aliyuncs.com/"+gift.getIma());
        mGiftDao.insert(mu);

        System.out.println("onFinish: " + progress);
    }

    @Override
    public void onRemove(Progress progress) {
        System.out.println("onRemove: " + progress);
    }
}

