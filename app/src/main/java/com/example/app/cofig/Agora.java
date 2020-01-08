package com.example.app.cofig;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.RoomheadAdapter;
import com.example.app.Adapter.RoomtxtAdapter;
import com.example.app.Entity.Roomhead;
import com.example.app.Entity.Roomtxt;
import com.example.app.Model.PaimaiModel;
import com.example.app.R;
import com.example.app.chatroom;
import com.wildma.pictureselector.Constant;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.agora.rtc.Constants;
import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;

public class Agora extends Activity {


    /**
     * 静音别人
     */
    private void AllRemoteAudioStreams(RtcEngine mRtcEngine,Boolean isChecked) {

        mRtcEngine.muteAllRemoteAudioStreams(isChecked);

    }

    /**
     * 静音自己
     */
    private void LocalAudioStream(RtcEngine mRtcEngine,Boolean isChecked) {

        mRtcEngine.muteLocalAudioStream(isChecked);

    }

    /**
     * 切换听筒还是外放
     */
    private void EnableSpeakerphone(RtcEngine mRtcEngine,Boolean isChecked) {

        mRtcEngine.setEnableSpeakerphone(isChecked);

    }


    /**
     * 播放伴奏音乐
     */
    private void AudioMixing(RtcEngine mRtcEngine,Boolean isChecked) {

        if (isChecked) {
            mRtcEngine.startAudioMixing("/assets/mixing.mp3", false, false, 1);
            // 调整伴奏音量，防止伴奏声音过大影响人声
            mRtcEngine.adjustAudioMixingVolume(15);
        } else {
            mRtcEngine.stopAudioMixing();
        }
    }



    public void onAudioMixingStateChanged() {

        /** 伴奏播放结束时，将button 置为未选中状态 **/
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
              //  mCheckBoxAudioMixing.setChecked(false);
            }
        });
    }

}
