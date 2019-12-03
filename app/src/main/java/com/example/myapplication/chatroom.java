package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.cofig.PathImageBit;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGADynamicEntity;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class chatroom extends AppCompatActivity {

    @BindView(R.id.imageView46)
    ImageView imageView46;
    @BindView(R.id.svgaima)
    SVGAImageView svgaima;
    SVGAParser parser;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        ButterKnife.bind(this);
        parser = new SVGAParser(this);


        File cacheDir = new File(this.getCacheDir(), "https://data.meitehudong.com/52hertz/svga/posche.svga");
        System.out.println(cacheDir);
        try {
            HttpResponseCache.install(cacheDir, 1024 * 1024 * 128);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread=new Thread(new Runnable(){
            @Override
            public void run()
            {
                bitmap = PathImageBit.fetchBitmapByUrl(chatroom.this,"https://momeak.oss-cn-shenzhen.aliyuncs.com/dear1.png");
                // TODO Auto-generated method stub
                Message message =new Message();
                message.what=1;
                mHandler.sendMessage(message);
            }
        });
        thread.start();
    }

    private void loadAnimation() {
        // new URL needs try catch.

        try {
            parser.parse(new URL("https://data.meitehudong.com/52hertz/svga/posche.svga"), new SVGAParser.ParseCompletion() {
                @Override
                public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                    SVGADynamicEntity dynamicEntity = new SVGADynamicEntity();

                    if (bitmap != null) {
                        dynamicEntity.setDynamicImage(bitmap, "99"); // Here is the KEY implementation.
                        TextPaint textPaint = new TextPaint();
                        textPaint.setColor(Color.WHITE);
                        textPaint.setTextSize(28);
                        dynamicEntity.setDynamicText("Pony send Kitty flowers.", textPaint, "banner");

                    }
                    SVGADrawable drawable = new SVGADrawable(videoItem, dynamicEntity);
                    svgaima.setImageDrawable(drawable);
                    svgaima.startAnimation();
                }

                @Override
                public void onError() {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Handler mHandler=new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case 1:
                    loadAnimation();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
