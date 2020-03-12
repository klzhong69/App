package com.example.hz52.app.cofig;

import android.app.Activity;

import io.agora.rtc.RtcEngine;

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
